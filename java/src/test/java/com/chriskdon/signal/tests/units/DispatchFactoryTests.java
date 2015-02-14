package com.chriskdon.signal.tests.units;

import com.chriskdon.signal.DispatcherFactory;
import com.chriskdon.signal.exceptions.ArgumentNullException;
import com.chriskdon.signal.tests.mocks.MockDetectorModule;
import org.junit.*;

public class DispatchFactoryTests {
  @Test
  public void buildTest() {
    DispatcherFactory factory = new DispatcherFactory();

    Assert.assertNotNull(factory.build());

    factory.registerDetectorModule(new MockDetectorModule());
    Assert.assertNotNull(factory.build());
  }

  @Test(expected = ArgumentNullException.class)
  public void registerDetectorModuleWithNullTest() {
    DispatcherFactory factory = new DispatcherFactory();
    factory.registerDetectorModule(null);
  }

  @Test
  public void registerDetectorModuleTest() {
    DispatcherFactory factory = new DispatcherFactory();
    factory.registerDetectorModule(new MockDetectorModule());
  }
}
