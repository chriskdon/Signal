package examples.simple;

import com.chriskdon.signal.Dispatcher;
import com.chriskdon.signal.DispatcherFactory;
import com.chriskdon.signal.Reference;
import examples.simple.modules.BaseModule;
import examples.simple.signals.SimpleSignal;

public class Main {

    public static void main(String[] args) {
      DispatcherFactory dispatcherFactory = new DispatcherFactory();
      dispatcherFactory.registerDetectorModule(new BaseModule());

      Dispatcher dispatcher = dispatcherFactory.build();

      Reference ref = dispatcher.signal(new SimpleSignal("Hello World"));
      dispatcher.signal(new SimpleSignal("Other"));
    }
}
