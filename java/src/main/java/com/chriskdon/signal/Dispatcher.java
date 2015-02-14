package com.chriskdon.signal;

/**
 * Main dispatcher for signals.
 */
public interface Dispatcher {
  /**
   * Send a new signal.
   * @param signal    The signal to send.
   * @param <TSignal> The type of the signal.
   * @return A reference to related data as a result of the signal.
   */
  public <TSignal extends Signal> Reference signal(TSignal signal);
}
