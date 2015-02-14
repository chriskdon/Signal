package com.chriskdon.signal;

/**
 * Link signals to detectors.
 */
public abstract class DetectorModule {
  private SignalToDetectorMapSet signalToDetectorsMapSet;

  public DetectorModule() {
    this.signalToDetectorsMapSet = new SignalToDetectorMapSet();
  }

  /**
   * Register a signal to be handled by a detector.
   *
   * @param <TSignal>
   * @param <TDetector>
   */
  protected final <TSignal extends Signal,
      TDetector extends Detector<TSignal>> void register(Class<TSignal> signalClass, Class<TDetector> detectorClass) {

    signalToDetectorsMapSet.add(signalClass, detectorClass);
  }

  /**
   * All the registered detectors
   *
   * @return
   */
  public final SignalToDetectorMapSet getDetectors() {
    return signalToDetectorsMapSet;
  }
}
