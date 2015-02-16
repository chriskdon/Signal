package com.chriskdon.signal;

import com.chriskdon.signal.exceptions.ArgumentNullException;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SignalExecutor implements Signaler {
  private SignalToDetectorMapSet signalMap;
  private DetectorFactory detectorFactory;
  private ExecutorService threadPool;

  public SignalExecutor(SignalToDetectorMapSet signalMap, DetectorFactory detectorFactory) {
    if(signalMap == null) {
      throw new ArgumentNullException("signalMap");
    }

    if(detectorFactory == null) {
      throw new ArgumentNullException("detectorFactory");
    }

    this.signalMap = signalMap;
    this.detectorFactory = detectorFactory;
    this.threadPool = Executors.newFixedThreadPool(getOptimalThreadCount());
  }

  @Override
  public <TSignal extends Signal> Reference signal(TSignal signal) {
    SignalReference<TSignal> reference = new SignalReference(signal, this);

    threadPool.execute(() -> {
      Class<TSignal> signalClass = (Class<TSignal>) signal.getClass();
      Collection<Class> detectorClasses = signalMap.getDetectorTypesFor(signalClass);

      try {
        Collection<Detector<TSignal>> detectors = detectorFactory.buildFrom(detectorClasses, reference);
        detectors.forEach((d) -> d.handleSignal(signal));
        reference.setAllDetectorsComplete();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    });

    return reference;
  }

  /**
   * Determine the optimal number of threads for this machine.
   *
   * @return The optimal number of threads.
   */
  private int getOptimalThreadCount() {
    return 8; // TODO: Calculation
  }
}
