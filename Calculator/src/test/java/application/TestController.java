package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestController {

  CalcModel model;
  MockView mock;
  CalcController testController;

  @BeforeEach
  void createMock() {
    model = new CalcModel();
    mock = new MockView();
    testController = new CalcController(model, mock);
  }

  @Test
  void testController() {
    assertNotNull(mock.handleCalculate);
    assertNotNull(mock.typeChange);
  }

  @Test
  void testEvaluateOne() {
    String exp = String.valueOf(5.0);
    mock.setExpression(exp);
    mock.startView();
    assertEquals(exp, mock.getAnswer());
  }
  
  @Test
  void testEvaluateTwo() {
    mock.setExpression("1 2 +");
    mock.startView();
    assertEquals("3.0", mock.getAnswer());
    
    mock.setExpression("6 8 2 / +");
    mock.startView();
    assertEquals("10.0", mock.getAnswer());
  }

  @Test
  void testEvaluateThree() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("1 + 2");
    mock.startView();
    assertEquals("3.0", mock.getAnswer());
  }
  
  @Test
  void testEvaluateFour() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("( 2 + ( 25 / 5 ) ) * ( 3 + ( 3 + 4 ) )");
    mock.startView();
    assertEquals("70.0", mock.getAnswer());
  }
  
  @Test
  void testEvaluateFive() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("2 + 5");
    mock.startView();
    assertEquals("7.0", mock.getAnswer());
    
    mock.typeChange.accept(OpType.REV_POLISH);
    mock.setExpression("5 7 +");
    mock.startView();
    assertEquals("12.0", mock.getAnswer());
  }
  
  @Test
  void testEvaluateSix() {
    mock.setExpression("2 6 + +");
    mock.startView();
    assertEquals(RevPolishCalc.INVALID_MSG, mock.getAnswer());
  }
  
  @Test
  void testEvaluateSeven() {
    mock.setExpression("4 6 2 +");
    mock.startView();
    assertEquals(RevPolishCalc.INVALID_MSG, mock.getAnswer());
    
    mock.setExpression("2 2 +");
    mock.startView();
    assertEquals("4.0", mock.getAnswer());
  }
  
  @Test
  void testEvalauteEight() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("( 2 - 3 )");
    mock.startView();
    assertEquals("-1.0", mock.getAnswer());
  }
  
  @Test
  void testEvaluateNine() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("( 2 + 3");
    mock.startView();
    assertEquals(RevPolishCalc.INVALID_MSG, mock.getAnswer());
  }
}
