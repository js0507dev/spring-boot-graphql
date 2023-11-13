package com.js0507dev.order.common.exception;

import com.js0507dev.order.common.exception.enums.ErrorCode;
import lombok.Getter;
import org.springframework.graphql.execution.ErrorType;

@Getter
public class CustomBaseException extends RuntimeException {
  protected ErrorCode code;
  protected ErrorType errorType;

  public CustomBaseException(ErrorCode code, String message, ErrorType errorType) {
    super(message);
    this.code = code;
    this.errorType = errorType;
  }
}
