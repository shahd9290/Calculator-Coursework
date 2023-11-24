package application;

import java.util.Scanner;

/**
 * This class will be used to evaluate a calculation using the Reverse Polish Notation (PostFix).
 * Each Operator will affect any two preceding Operands, e.g. 1 5 + -> 6.
 * 
 * @author Danyal Shah
 */
public class RevPolishCalc {

  NumStack numStack = new NumStack();
  
  public float evaluate(String string) {
    Scanner scan = new Scanner(string);
    while (scan.hasNextInt()) {
      numStack.push(scan.nextInt());
    }
    scan.next();
    try {
      return numStack.pop() + numStack.pop();
    } catch (EmptyStackException e) {
      return 0;
    }
  }

}
