package application;

import java.util.function.Consumer;

public class MockView implements ViewInterface {
  public Runnable handleCalculate = null;
  public Consumer<OpType> typeChange = null;
  private String answer;
  private String expression;

  @Override
  public void addCalculateObserver(Runnable f) { //
    handleCalculate = f;
  }

  @Override
  public void addTypeObserver(Consumer<OpType> c) {
    typeChange = c;
  }

  @Override
  public String getExpression() {
    return expression;
  }

  @Override
  public void setAnswer(String a) {
    answer = a;
  }
  
  public void setExpression(String a) {
    expression = a;
  }

  public String getAnswer() {
    return answer;
  }

  @Override
  public void startView() {
    handleCalculate.run();
  }

}
