package com.js0507dev.wallet.common.exception;

import com.js0507dev.wallet.common.exception.enums.ErrorCode;
import org.springframework.graphql.execution.ErrorType;

public class NotFoundException extends CustomBaseException {
  public NotFoundException(ErrorCode code, String message) {
    super(code, message, ErrorType.NOT_FOUND);
  }
}
