package application;

/**
 * The controller that sits between the calculator model that does actual evaluation and the view
 * that is the part the user interfaces with.
 */
public class CalcController {
  private CalcModel myModel;
  private ViewInterface myView;
  private boolean isInFix;

  private void handleCalculation() {
    try {
      Float result = myModel.evaluate(myView.getExpression(), isInFix);
      myView.setAnswer(String.valueOf(result));
    } catch (InvalidExpression invalid) {
      myView.setAnswer(invalid.getMessage());
    }
  }

  private void handleTypeChange(OpType op) {
    isInFix = (op == OpType.STANDARD);
  }

  CalcController(CalcModel model, ViewInterface view) {
    myView = view;
    myModel = model;
    view.addCalculateObserver(this::handleCalculation);
    view.addTypeObserver(this::handleTypeChange);
  }

  CalcController() {}
}
