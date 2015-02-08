package com.chriskdon.signal.exceptions;

public class ArgumentNullException extends IllegalArgumentException {
  public ArgumentNullException(String argumentName) {
    super("`" + argumentName + "` can't be null.");
  }
}
