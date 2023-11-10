package uk.ac.rhul.cs2800;

/**
 * A custom Exception class that is thrown when trying to remove or view an entry in an empty stack.
 */
public class EmptyStackException extends Exception {
  public EmptyStackException(String message) {
    super(message);
  }
}
