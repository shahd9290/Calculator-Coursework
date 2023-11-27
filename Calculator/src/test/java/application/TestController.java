package application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class TestController {

  @Test
  void testController() {
    CalcModel model = new CalcModel();
    MockView mock = new MockView();
    CalcController testController = new CalcController(model, mock);
    assertNotNull(mock.handleCalculate);
    assertNotNull(mock.typeChange);
  }

}
