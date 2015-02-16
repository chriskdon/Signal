package examples.simple.modules;

import com.chriskdon.signal.DetectorModule;
import examples.simple.detectors.SimpleSignalDetector;
import examples.simple.signals.SimpleSignal;

public class BaseModule extends DetectorModule {
  public BaseModule() {
    register(SimpleSignal.class, SimpleSignalDetector.class);
  }
}
