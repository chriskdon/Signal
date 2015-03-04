package examples.simple.detectors;

import com.chriskdon.signal.Detector;
import examples.simple.signals.SimpleSignal;

public class SimpleSignalDetector extends Detector<SimpleSignal> {
  @Override
  public void handleSignal(SimpleSignal simpleSignal) {
    System.out.println("Simple Signal Detected: " + simpleSignal.getMessage());
  }
}
