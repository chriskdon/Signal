package com.chriskdon.signal.tests.units.base;

import com.chriskdon.signal.Detector;
import com.chriskdon.signal.Signal;
import com.chriskdon.signal.base.SignalToDetectorMapSet;
import com.chriskdon.signal.tests.mocks.MockSignal;
import com.chriskdon.signal.tests.mocks.MockSignalDetector;
import javafx.print.Collation;
import org.junit.*;

import java.util.Collection;

public class SignalToDetectorMapSetTests {
  @Test
  public void addAndGetTest() {
    SignalToDetectorMapSet ms = new SignalToDetectorMapSet();

    ms.add(MockSignal.class, MockSignalDetector.class);
    ms.add(MockSignal.class, MockSignalDetector.class); // Should be ignored

    Collection<Class> detectors = ms.getDetectorTypesFor(MockSignal.class);

    Assert.assertEquals(1, detectors.size());
  }

  @Test
  public void addAggregationTest() {
    SignalToDetectorMapSet msBase = new SignalToDetectorMapSet();
    SignalToDetectorMapSet msOther = new SignalToDetectorMapSet();

    msBase.add(MockSignal.class, MockSignalDetector.class);

    msOther.add(MockSignal.class, MockSignalDetector.class);
    msOther.add(MockSignalOther.class, MockSignalOtherDetector.class);

    msBase.add(msOther);

    Assert.assertEquals(1, msBase.getDetectorTypesFor(MockSignal.class).size());
    Assert.assertEquals(1, msBase.getDetectorTypesFor(MockSignalOther.class).size());
  }

  private static class MockSignalOther implements Signal {
  }

  private static class MockSignalOtherDetector extends Detector<MockSignalOther> {
    @Override
    public void handleSignal(MockSignalOther signal) {
      throw new UnsupportedOperationException();
    }
  }
}
