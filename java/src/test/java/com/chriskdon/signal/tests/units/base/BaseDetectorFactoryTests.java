package com.chriskdon.signal.tests.units.base;

import com.chriskdon.signal.Detector;
import com.chriskdon.signal.base.DetectorFactory;
import com.chriskdon.signal.tests.mocks.MockReference;
import com.chriskdon.signal.tests.mocks.MockSignal;
import com.chriskdon.signal.tests.mocks.MockSignalDetector;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BaseDetectorFactoryTests {

  @Test
  public void BuildFromTypesTest() {
    DetectorFactory factory = new DetectorFactory();
    List<Class> detectorTypes = new ArrayList<Class>();

    Collection<Detector<MockSignal>> detectors;

    try {
      detectors = factory.buildFrom(detectorTypes, new MockReference());
      Assert.assertTrue(detectors.isEmpty());
    } catch (Exception ex) {
      Assert.fail();
    }

    try {
      detectorTypes.add(MockSignalDetector.class);
      detectors = factory.buildFrom(detectorTypes, new MockReference());
      Assert.assertEquals(1, detectors.size());
    } catch (Exception ex) {
      Assert.fail();
    }
  }
}