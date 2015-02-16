package com.chriskdon.signal;

final class BaseDispatcher implements Dispatcher {
  private SignalToDetectorMapSet signalMap;
  private DetectorFactory detectorFactory;
  private SignalExecutor signalExecutor;

  public BaseDispatcher(SignalToDetectorMapSet signalMap) {
    this.signalMap = signalMap;
    this.detectorFactory = new DetectorFactory();
    this.signalExecutor = new SignalExecutor(this.signalMap, this.detectorFactory);
  }

  @Override
  public <TSignal extends Signal> Reference<TSignal> signal(TSignal signal) {
    return signalExecutor.signal(signal);
  }
}
