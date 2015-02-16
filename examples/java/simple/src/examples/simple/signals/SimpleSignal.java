package examples.simple.signals;

import com.chriskdon.signal.Signal;

public class SimpleSignal implements Signal {
  private final String message;

  public SimpleSignal(String msg) {
    this.message = msg;
  }

  public String getMessage() {
    return this.message;
  }
}
