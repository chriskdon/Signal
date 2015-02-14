package com.chriskdon.signal.tests.mocks;

import com.chriskdon.signal.Reference;

public class MockSignalReference extends Reference<MockSignal> {
  public MockSignalReference(MockSignal signal) {
    super(signal);
  }

  @Override
  public void complete() {

  }
}
