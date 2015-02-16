package com.chriskdon.signal;

import com.chriskdon.signal.exceptions.ArgumentNullException;

import java.util.Collection;

class SignalReference<TSignal extends Signal> extends Reference<TSignal> {
  private boolean allDetectorsComplete = false; // Set by the dispatcher when all detectors have completed their work.
  private Object allDetectorsCompleteSync = new Object();

  private Collection<Detector<TSignal>> detectors; // Detector instances for this reference
  private SignalExecutor signalExecutor;

  public SignalReference(TSignal signal, SignalExecutor signalExecutor) {
    super(signal);

    if(signalExecutor == null) {
      throw new ArgumentNullException("signalExecutor");
    }

    this.signalExecutor = signalExecutor;
  }

  public void setDetectors(Collection<Detector<TSignal>> detectors) {
    if(detectors == null) {
      throw new ArgumentNullException("detectors");
    }

    this.detectors = detectors;
  }

  @Override
  public <TSignal extends Signal> Reference signal(TSignal signal) {
    return signalExecutor.signal(signal);
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
