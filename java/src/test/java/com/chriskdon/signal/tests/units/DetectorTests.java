package com.chriskdon.signal.tests.units;

import com.chriskdon.signal.Detector;
import com.chriskdon.signal.base.SignalReference;
import com.chriskdon.signal.exceptions.ArgumentNullException;
import com.chriskdon.signal.tests.mocks.MockSignal;
import com.chriskdon.signal.tests.mocks.MockSignalDetector;
import org.junit.Test;

public class DetectorTests {
  @Test
  public void setReferenceTest() {
    Detector<MockSignal> detector = new MockSignalDetector();
    detector.setReference(new SignalReference(new MockSignal()));
  }

  @Test(expected = ArgumentNullException.class)
  public void setReferenceNullTest() {
    Detector<MockSignal> detector = new MockSignalDetector();
    detector.setReference(null);
  }
}
