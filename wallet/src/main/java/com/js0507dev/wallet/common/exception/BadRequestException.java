package com.js0507dev.wallet.common.exception;

import com.js0507dev.wallet.common.exception.enums.ErrorCode;
import org.springframework.graphql.execution.ErrorType;

public class BadRequestException extends CustomBaseException {
  public BadRequestException(ErrorCode code, String message) {
    super(code, message, ErrorType.BAD_REQUEST);
  }
}
