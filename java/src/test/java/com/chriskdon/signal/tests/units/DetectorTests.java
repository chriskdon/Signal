package com.chriskdon.signal.tests.units;

import com.chriskdon.signal.Detector;
import com.chriskdon.signal.exceptions.ArgumentNullException;
import com.chriskdon.signal.tests.mocks.MockSignal;
import com.chriskdon.signal.tests.mocks.MockSignalDetector;
import com.chriskdon.signal.tests.mocks.MockSignalReference;
import junit.framework.Assert;
import org.junit.Test;

public class DetectorTests {
  @Test
  public void setGetReferenceTest() {
    Detector<MockSignal> detector = new MockSignalDetector();
    detector.setReference(new MockSignalReference(new MockSignal()));
    Assert.assertNotNull(detector.getReference());
  }

  @Test(expected = ArgumentNullException.class)
  public void setReferenceNullTest() {
    Detector<MockSignal> detector = new MockSignalDetector();
    detector.setReference(null);
  }

  @Test(expected = NullPointerException.class)
  public void getNullReferenceTest() {
    Detector<MockSignal> detector = new MockSignalDetector();
    detector.getReference();
  }
}
