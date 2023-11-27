package application;

import java.util.function.Consumer;

public class MockView implements ViewInterface {
  public Runnable handleCalculate = null;
  public Consumer<OpType> typeChange = null;

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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setAnswer(String a) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void startView() {
    // TODO Auto-generated method stub
    
  }

}
