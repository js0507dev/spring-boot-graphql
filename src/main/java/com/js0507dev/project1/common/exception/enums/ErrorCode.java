package com.js0507dev.project1.common.exception.enums;

public enum ErrorCode {
  // member
  NOT_FOUND_MEMBER,

  // wallet
  NOT_FOUND_WALLET,
  CREATE_WALLET_FAILED,

  // auth
  INVALID_JWT,
  EXPIRED,
  UNKNOWN_AUTHORIZATION_ERROR;
}
