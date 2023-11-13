package com.js0507dev.order.common.exception;

import com.js0507dev.order.common.exception.enums.ErrorCode;
import org.springframework.graphql.execution.ErrorType;

public class UnauthorizedException extends CustomBaseException {
  public UnauthorizedException(ErrorCode code, String message) {
    super(code, message, ErrorType.UNAUTHORIZED);
  }
}
