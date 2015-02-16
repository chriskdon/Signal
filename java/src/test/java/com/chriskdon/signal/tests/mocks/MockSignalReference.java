package com.chriskdon.signal.tests.mocks;

import com.chriskdon.signal.Reference;
import com.chriskdon.signal.Signal;

public class MockSignalReference extends Reference<MockSignal> {
  public MockSignalReference(MockSignal signal) {
    super(signal);
  }

  @Override
  public void complete() {

  }

  @Override
  public <TSignal extends Signal> Reference signal(TSignal signal) {
    return null;
  }
}
