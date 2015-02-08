package com.chriskdon.signal.base;

import com.chriskdon.signal.Reference;
import com.chriskdon.signal.Signal;

public class SignalReference<TSignal extends Signal> extends Reference<TSignal> {
  private boolean allDetectorsComplete = false; // Set by the dispatcher when all detectors have completed their work.
  private Object allDetectorsCompleteSync = new Object();

  public SignalReference(TSignal signal) {
    super(signal);
  }

  public void setAllDetectorsComplete() {
    synchronized (allDetectorsCompleteSync) {
      allDetectorsComplete = true;
      allDetectorsCompleteSync.notify();
    }
  }

  @Override
  public void complete() {
   synchronized (allDetectorsCompleteSync) {
      if (allDetectorsComplete) {
        return;
      } else {
        try {
          allDetectorsCompleteSync.wait();
        } catch (InterruptedException ex) {
          throw new RuntimeException(ex);
        }
      }
    }
  }
}
