package com.chriskdon.signal.base;

import com.chriskdon.signal.*;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public final class BaseDispatcher implements Dispatcher {
  private SignalToDetectorMapSet signalMap;
  private DetectorFactory detectorFactory;
  private ExecutorService threadPool;

  public BaseDispatcher() {
    this.signalMap = new SignalToDetectorMapSet();
    this.detectorFactory = new DetectorFactory();
    this.threadPool = Executors.newFixedThreadPool(getOptimalThreadCount());
  }

  @Override
  public <TSignal extends Signal> Reference<TSignal> signal(final TSignal signal) {
    SignalReference<TSignal> reference = new SignalReference(signal);

    threadPool.execute(() -> {
      Class<TSignal> signalClass = (Class<TSignal>)signal.getClass();
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

  @Override
  public void registerDetectorModule(DetectorModule detectorModule) {
    signalMap.add(detectorModule.getDetectors());
  }

  /**
   * Determine the optimal number of threads for this machine.
   * @return The optimal number of threads.
   */
  private int getOptimalThreadCount() {
    return 8; // TODO: Calculation
  }
}
