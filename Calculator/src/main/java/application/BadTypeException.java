package uk.ac.rhul.cs2800;

/**
 * The Exception thrown when getting a value not used in an Entry instance.
 *
 *@author Danyal Shah
 */
@SuppressWarnings("serial")
public class BadTypeException extends Exception {

  public BadTypeException(String message) {
    super(message);
  }

}
