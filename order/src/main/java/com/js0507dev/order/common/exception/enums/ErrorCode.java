package com.js0507dev.order.common.exception.enums;

public enum ErrorCode {
  // member
  NOT_FOUND_MEMBER,

  // wallet
  NOT_FOUND_WALLET,
  ALREADY_CREATED_WALLET,
  CREATE_WALLET_FAILED,

  // auth
  INVALID_JWT,
  EXPIRED,
  INVALID_LOGIN_INFO,
  UNKNOWN_AUTHORIZATION_ERROR;
}
