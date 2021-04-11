package com.code.assessment.exception;

public class InvalidTradeException extends RuntimeException {

  private final String id;

  public InvalidTradeException(final String id) {
    super("Order is Invalid : " + id);
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
