package application;

/**
 * Evaluates an expression - the evaluation can be Standard (infix) or reverse polish.
 */
public class CalcModel implements Calculator {
  
  private RevPolishCalc revPolish = new RevPolishCalc();
  private StandardCalc standard = new StandardCalc();
  
  @Override
  public float evaluate(String expression, Boolean infix) throws InvalidExpression {
    if (infix) {
      return standard.evaluate(expression);
    }
    return revPolish.evaluate(expression);
  }
}
