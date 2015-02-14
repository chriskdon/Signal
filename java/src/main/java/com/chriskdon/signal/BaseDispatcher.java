package com.chriskdon.signal;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class BaseDispatcher implements Dispatcher {
  private SignalToDetectorMapSet signalMap;
  private DetectorFactory detectorFactory;
  private ExecutorService threadPool;

  public BaseDispatcher(SignalToDetectorMapSet signalMap) {
    this.signalMap = signalMap;
    this.detectorFactory = new DetectorFactory();
    this.threadPool = Executors.newFixedThreadPool(getOptimalThreadCount());
  }

  @Override
  public <TSignal extends Signal> Reference<TSignal> signal(final TSignal signal) {
    SignalReference<TSignal> reference = new SignalReference(signal);

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
