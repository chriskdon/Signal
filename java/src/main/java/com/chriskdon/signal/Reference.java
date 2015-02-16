package com.chriskdon.signal;

import com.chriskdon.signal.exceptions.ArgumentNullException;

/**
 * Handles information related to a signal.
 */
public abstract class Reference<TSignal extends Signal> implements Signaler {
  private TSignal signal; // The signal this reference is for.

  public Reference(TSignal signal) {
    if(signal == null) {
      throw new ArgumentNullException("signal");
    }

    this.signal = signal;
  }

  /**
   * @return The signal this reference is for.
   */
  public TSignal getSignal() {
    return signal;
  }

  /**
   * Wait for all detectors to complete their actions on a signal.
   */
  public abstract void complete();
}
