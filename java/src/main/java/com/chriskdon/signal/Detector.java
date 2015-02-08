package com.chriskdon.signal;

import com.sun.istack.internal.NotNull;

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
  public final void setReference(@NotNull Reference reference) {
    if(reference == null) {
      throw new IllegalArgumentException("`reference` can't be null.");
    }

    this.reference = reference;
  }

  public abstract void handleSignal(TSignal signal);
}
