package com.chriskdon.signal.tests.units;

import com.chriskdon.signal.*;
import com.chriskdon.signal.tests.mocks.MockSignal;
import org.junit.*;

import java.util.concurrent.atomic.AtomicInteger;

public class DispatcherTests {
  @Test
  public void SendSignalTest() {
    for(int i = 0; i < 100; i++) { // Find possible threading issues
      DispatcherFactory factory = new DispatcherFactory();

      // No Detectors
      Dispatcher dispatcher = factory.build();
      dispatcher.signal(new MockSignal());

      // Detectors
      factory.registerDetectorModule(new MockDetectorModule());
      dispatcher = factory.build();

      MockSignalDetectorOther.StaticHandleCallCount.set(0);
      Reference ref = dispatcher.signal(new MockSignal());
      Reference ref2 = dispatcher.signal(new MockSignal());

      ref.complete();
      ref2.complete();

      Assert.assertEquals(2, MockSignalDetectorOther.StaticHandleCallCount.get());
    }
  }

  private class MockDetectorModule extends DetectorModule {
    public MockDetectorModule() {
      register(MockSignal.class, MockSignalDetectorOther.class);
    }
  }

  public static class MockSignalDetectorOther extends Detector<MockSignal> {
    public static AtomicInteger StaticHandleCallCount = new AtomicInteger(0);

    @Override
    public void handleSignal(MockSignal signal) {
      StaticHandleCallCount.incrementAndGet();
    }
  }
}
