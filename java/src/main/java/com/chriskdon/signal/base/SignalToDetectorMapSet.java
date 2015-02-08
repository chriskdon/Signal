package com.chriskdon.signal.base;

import com.chriskdon.signal.Detector;
import com.chriskdon.signal.Signal;

import java.util.*;

/**
 * Store the mappings from signals to detectors that will handle the signals.
 */
public final class SignalToDetectorMapSet {
  private Map<Class, Set<Class>> signalToDetectorMapSet;

  public SignalToDetectorMapSet() {
    this.signalToDetectorMapSet = new HashMap<Class, Set<Class>>();
  }

  /**
   * Add a new mapping from a detector to a signal
   * @param <TSignal>
   * @param <TDetector>
   */
  public <TSignal extends Signal, TDetector extends Detector<TSignal>> void add(Class<TSignal> signalClass,
                                                                                Class<TDetector> detectorClass) {

    Set<Class> detectorSet = signalToDetectorMapSet.get(signalClass);

    if (detectorSet == null) {
      detectorSet = newClassSet();
      signalToDetectorMapSet.put(signalClass, detectorSet);
    }

    detectorSet.add(detectorClass);
  }

  /**
   * Aggregate two SignalToDetectorMapSets together.
   * @param signalToDetectorMapSet
   */
  public void add(SignalToDetectorMapSet signalToDetectorMapSet) {
    Map<Class, Set<Class>> paramMapSet = signalToDetectorMapSet.signalToDetectorMapSet;

    for(Class signalClass : paramMapSet.keySet()) {
      Set<Class> detectorSet = this.signalToDetectorMapSet.get(signalClass);

      if (detectorSet == null) {
        detectorSet = newClassSet();
        this.signalToDetectorMapSet.put(signalClass, detectorSet);
      }

      for(Class detectorClass : paramMapSet.get(signalClass)) {
        detectorSet.add(detectorClass);
      }
    }
  }

  /**
   * Get all the types for a detector.
   * @param signalClass
   * @param <TSignal>
   * @return
   */
  public <TSignal extends Signal> Collection<Class> getDetectorTypesFor(Class<TSignal> signalClass) {
    Set<Class> detectorList = signalToDetectorMapSet.get(signalClass);

    if (detectorList == null) {
      return Collections.EMPTY_LIST;
    }

    return detectorList;
  }

  private Set<Class> newClassSet() {
    return new HashSet<Class>();
  }
}
