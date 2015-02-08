package com.chriskdon.signal;

import com.chriskdon.signal.exceptions.ArgumentNullException;

/**
 * Handles signals.
 * @param <TSignal> The type of signal handled by this detector.
 */
public abstract class Detector<TSignal extends Signal> {
  private Reference reference;

  /**
   * Set the reference value.
   *
   * @param reference The reference to set to.
   */
  public final void setReference(Reference reference) {
    if(reference == null) {
      throw new ArgumentNullException("reference");
    }

    this.reference = reference;
  }

  /**
   * Handle a signal sent to the detector.
   * @param signal
   */
  public abstract void handleSignal(TSignal signal);
}
