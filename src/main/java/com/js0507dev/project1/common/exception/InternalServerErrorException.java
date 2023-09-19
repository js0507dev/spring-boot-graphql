package com.js0507dev.project1.common.exception;

import com.js0507dev.project1.common.exception.enums.ErrorCode;
import org.springframework.graphql.execution.ErrorType;

public class InternalServerErrorException extends CustomBaseException {
  public InternalServerErrorException(ErrorCode code, String message) {
    super(code, message, ErrorType.INTERNAL_ERROR);
  }
}
