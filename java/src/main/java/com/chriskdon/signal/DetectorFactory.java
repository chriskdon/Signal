package com.chriskdon.signal;

import com.chriskdon.signal.Detector;
import com.chriskdon.signal.Reference;
import com.chriskdon.signal.Signal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Creates instances of the detectors.
 */
class DetectorFactory {

  /**
   * Return instances of the detectors for a signal based on the classes.
   * @param detectorClasses
   * @param reference
   * @param <TSignal>
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public <TSignal extends Signal> Collection<Detector<TSignal>> buildFrom(Collection<Class> detectorClasses,
                                                                          SignalReference reference)
      throws InstantiationException, IllegalAccessException {

    List<Detector<TSignal>> detectorInstances = new ArrayList<Detector<TSignal>>(detectorClasses.size());

    for (Class detectorClass : detectorClasses) {
      Detector<TSignal> detector = constructDetectorFromClass(detectorClass, reference);
      detectorInstances.add(detector);
    }

    reference.setDetectors(detectorInstances);

    return detectorInstances;
  }

  /**
   * Create an instance of the detector from the class.
   * @param detectorClass
   * @param reference
   * @param <TSignal>
   * @return
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  private <TSignal extends Signal> Detector<TSignal> constructDetectorFromClass(Class detectorClass,
                                                                                Reference reference)
      throws InstantiationException, IllegalAccessException {

    Detector<TSignal> detector = (Detector<TSignal>) detectorClass.newInstance();
    detector.setReference(reference);
    return detector;
  }
}
