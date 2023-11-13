package com.js0507dev.wallet.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {
  private String email;
  private String password;
  private String encryptedPassword;
}
