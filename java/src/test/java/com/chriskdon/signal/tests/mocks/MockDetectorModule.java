package com.chriskdon.signal.tests.mocks;

import com.chriskdon.signal.DetectorModule;

public class MockDetectorModule extends DetectorModule {
  public void registerDetectors() {
    register(MockSignal.class, MockSignalDetector.class);
    register(MockSignal.class, MockSignalDetector.class); // Should be ignored
  }

  public int getRegisteredCount() { return 1; }
}
