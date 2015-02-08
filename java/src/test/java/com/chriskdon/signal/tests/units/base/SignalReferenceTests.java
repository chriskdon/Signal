package com.chriskdon.signal.tests.units.base;

import com.chriskdon.signal.base.SignalReference;
import com.chriskdon.signal.tests.mocks.MockSignal;
import org.junit.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class SignalReferenceTests {
  @Test
  public void completeTest() {
    SignalReference<MockSignal> ref = new SignalReference(new MockSignal());

    AtomicBoolean completed = new AtomicBoolean(false);
    new Thread(() -> {
      try {
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
      }

      completed.set(true);
      ref.setAllDetectorsComplete();
    }).start();

    ref.complete();

    if(!completed.get()) {
      Assert.fail("Did not wait for threads to complete.");
    }
  }
}
