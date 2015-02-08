package com.chriskdon.signal.tests.units.base;

import com.chriskdon.signal.Detector;
import com.chriskdon.signal.DetectorModule;
import com.chriskdon.signal.base.BaseDispatcher;
import com.chriskdon.signal.tests.mocks.MockSignal;
import org.junit.*;

import java.util.concurrent.atomic.AtomicInteger;

public class BaseDispatcherTests {
  @Test
  public void SendSignalTest() {
    BaseDispatcher dispatcher = new BaseDispatcher();

    dispatcher.signal(new MockSignal()); // No Detectors

    MockSignalDetectorOther.StaticHandleCallCount.set(0);
    dispatcher.registerDetectorModule(new MockDetectorModule()); // Detectors
    dispatcher.signal(new MockSignal());
    dispatcher.signal(new MockSignal());

    Assert.assertEquals(2, MockSignalDetectorOther.StaticHandleCallCount.get());
  }

  @Test
  public void RegisterDetectorModule() {
    BaseDispatcher dispatcher = new BaseDispatcher();
    dispatcher.registerDetectorModule(new MockDetectorModule());
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
