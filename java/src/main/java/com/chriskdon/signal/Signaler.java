package com.chriskdon.signal;

/**
 * Any types that can emit signals.
 */
public interface Signaler {
  /**
   * Send a new signal.
   * @param signal    The signal to send.
   * @param <TSignal> The type of the signal.
   * @return A reference to related data as a result of the signal.
   */
  public <TSignal extends Signal> Reference signal(TSignal signal);
}
