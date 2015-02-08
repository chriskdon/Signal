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

  /**
   * Register a module containing linkages for signals and detectors.
   * @param detectorModule The module to register.
   */
  public void registerDetectorModule(DetectorModule detectorModule);
}
