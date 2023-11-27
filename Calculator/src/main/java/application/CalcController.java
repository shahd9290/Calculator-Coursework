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
    
  }

  private void handleTypeChange(OpType op) {
    if (op == OpType.STANDARD) {
      isInFix = true;
    }
    isInFix = false;
  }

  CalcController(CalcModel model, ViewInterface view) {
    view.addCalculateObserver(this::handleCalculation);
    view.addTypeObserver(this::handleTypeChange);
  }

  CalcController() {}
}
