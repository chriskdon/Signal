package com.chriskdon.signal.base;

import com.chriskdon.signal.*;

import java.util.Collection;


public final class BaseDispatcher implements Dispatcher {
  private SignalToDetectorMapSet signalMap;
  private DetectorFactory detectorFactory;

  public BaseDispatcher() {
    this.signalMap = new SignalToDetectorMapSet();
    this.detectorFactory = new DetectorFactory();
  }

  @Override
  public <TSignal extends Signal> Reference signal(final TSignal signal) {
    Reference reference = new SignalReference();
    Class<TSignal> signalClass = (Class<TSignal>)signal.getClass();
    Collection<Class> detectorClasses = signalMap.getDetectorTypesFor(signalClass);

    try {
      Collection<Detector<TSignal>> detectors = detectorFactory.buildFrom(detectorClasses, reference);
      detectors.forEach((d) -> d.handleSignal(signal));
      return reference;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void registerDetectorModule(DetectorModule detectorModule) {
    signalMap.add(detectorModule.getDetectors());
  }
}
