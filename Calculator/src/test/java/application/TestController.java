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
    assertNotNull(mock.handleCalculate, "Is the view set up?");
    assertNotNull(mock.typeChange);
  }

  @Test
  void testEvaluateOne() {
    String exp = String.valueOf(5.0);
    mock.setExpression(exp);
    mock.startView();
    assertEquals(exp, mock.getAnswer(), "Evaluate one number?");
  }
  
  @Test
  void testEvaluateTwo() {
    mock.setExpression("1 2 +");
    mock.startView();
    assertEquals("3.0", mock.getAnswer(), "Evaluate Simple Expression in Rev Polish?");
    
    mock.setExpression("6 8 2 / +");
    mock.startView();
    assertEquals("10.0", mock.getAnswer(),"Evaluate Complex Expression in Rev Polish?");
  }

  @Test
  void testEvaluateThree() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("1 + 2");
    mock.startView();
    assertEquals("3.0", mock.getAnswer(),"Evaluate Standard Expression in Rev Polish?");
  }
  
  @Test
  void testEvaluateFour() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("( 2 + ( 25 / 5 ) ) * ( 3 + ( 3 + 4 ) )");
    mock.startView();
    assertEquals("70.0", mock.getAnswer(),"Evaluate Complex Expression in Rev Polish?");
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
    assertEquals("12.0", mock.getAnswer(),"Evaluate Complex Expression in Rev Polish after Standard?");
  }
  
  @Test
  void testEvaluateSix() {
    mock.setExpression("2 6 + +");
    mock.startView();
    assertEquals(RevPolishCalc.INVALID_MSG, mock.getAnswer(),"Evaluate Invalid Expression Thrown? (Not enough numbers)");
  }
  
  @Test
  void testEvaluateSeven() {
    mock.setExpression("4 6 2 +");
    mock.startView();
    assertEquals(RevPolishCalc.INVALID_MSG, mock.getAnswer(), "Evaluate Invalid Expression Thrown? (Not Enough Operators)");
    
    mock.setExpression("2 2 +");
    mock.startView();
    assertEquals("4.0", mock.getAnswer(), "Evaluate Calculation after Exception");
  }
  
  @Test
  void testEvalauteEight() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("( 2 - 3 )");
    mock.startView();
    assertEquals("-1.0", mock.getAnswer(), "Evaluate calculation inside single brackets");
  }
  
  @Test
  void testEvaluateNine() {
    mock.typeChange.accept(OpType.STANDARD);
    mock.setExpression("( 2 + 3");
    mock.startView();
    assertEquals(RevPolishCalc.INVALID_MSG, mock.getAnswer(), "Evaluate calculation inside unclosed brackets?");
    
    mock.setExpression("2 + 3 )");
    mock.startView();
    assertEquals(RevPolishCalc.INVALID_MSG, mock.getAnswer(), "Evaluate calculation inside unopened brackets?");
  }
  
  @Test
  void testEvaluateTen() {
    mock.setExpression("1000000000000000000000000000000000000000000000000000000000000 1 +");
    mock.startView();
    assertEquals(RevPolishCalc.OVERFLOW_MSG, mock.getAnswer(), "Evaluate overflow?");
  }
  
  @Test
  void testEvaluateElevenn() {
    mock.setExpression("-1000000000000000000000000000000000000000000000000000000000000 1 +");
    mock.startView();
    assertEquals(RevPolishCalc.OVERFLOW_MSG, mock.getAnswer(), "Evaluate negative overflow?");
  }
}
