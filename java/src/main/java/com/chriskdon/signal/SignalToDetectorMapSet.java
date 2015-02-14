package com.chriskdon.signal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Store the mappings from signals to detectors that will handle the signals.
 */
final class SignalToDetectorMapSet {
  private Map<Class, Set<Class>> signalToDetectorMapSet; // Concurrent access to members

  public SignalToDetectorMapSet() {
    this.signalToDetectorMapSet = new ConcurrentHashMap();
  }

  /**
   * Add a new mapping from a detector to a signal
   * @param <TSignal>
   * @param <TDetector>
   */
  public synchronized <TSignal extends Signal, TDetector extends Detector<TSignal>> void add(Class<TSignal> signalClass,
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
  public synchronized void add(SignalToDetectorMapSet signalToDetectorMapSet) {
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
  public synchronized <TSignal extends Signal> Collection<Class> getDetectorTypesFor(Class<TSignal> signalClass) {
    Set<Class> detectorList = signalToDetectorMapSet.get(signalClass);

    if (detectorList == null) {
      return Collections.EMPTY_LIST;
    }

    return detectorList;
  }

  /**
   * Get a deep copy of the map set so no list references are the same. Modifying
   * the new map set will not affect the old one.
   *
   * @return The deep copy.
   */
  public synchronized SignalToDetectorMapSet getDeepCopy() {
    SignalToDetectorMapSet newSet = new SignalToDetectorMapSet();
    newSet.add(this);
    return newSet;
  }

  private Set<Class> newClassSet() {
    return Collections.synchronizedSet(new HashSet());
  }
}
