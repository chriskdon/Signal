package com.chriskdon.signal.tests.units;

import com.chriskdon.signal.tests.mocks.MockDetectorModule;
import org.junit.*;

public class DetectorModuleTests {
  @Test
  public void RegisterTest() {
    MockDetectorModule detectorModule = new MockDetectorModule();
    detectorModule.registerDetectors();
  }

  @Test
  public void GetDetectorsTest() {
    MockDetectorModule detectorModule = new MockDetectorModule();
    detectorModule.registerDetectors();

    Assert.assertNotNull(detectorModule.getDetectors());
  }
}
