package com.chriskdon.signal.tests.mocks;

import com.chriskdon.signal.Detector;

public class MockSignalDetector extends Detector<MockSignal> {
  private int handleCallCount = 0;

  @Override
  public void handleSignal(MockSignal signal) {
    handleCallCount++;
  }

  public int getHandleCallCount() {
    return  handleCallCount;
  }
}
