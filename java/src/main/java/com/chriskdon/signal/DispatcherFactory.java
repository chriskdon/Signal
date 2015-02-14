package com.chriskdon.signal;

import com.chriskdon.signal.exceptions.ArgumentNullException;

/**
 * Create dispatchers for use.
 */
public class DispatcherFactory {
  private SignalToDetectorMapSet signalMap;

  public DispatcherFactory() {
    this.signalMap = new SignalToDetectorMapSet();
  }

  /**
   * Create a new instance of a Dispatcher.
   *
   * @return The Dispatcher.
   */
  public Dispatcher build() {
    return new BaseDispatcher(signalMap.getDeepCopy());
  }

  /**
   * Register a module containing linkages for signals and detectors.
   *
   * @param detectorModule The module to register.
   */
  public void registerDetectorModule(DetectorModule detectorModule) {
    if (detectorModule == null) {
      throw new ArgumentNullException("detectorModule");
    }

    signalMap.add(detectorModule.getDetectors());
  }
}
