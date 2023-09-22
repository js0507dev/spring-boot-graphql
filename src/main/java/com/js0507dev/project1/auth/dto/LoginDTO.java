package com.js0507dev.project1.auth.dto;

import lombok.Data;

@Data
public class LoginDTO {
  private String email;
  private String password;
  private String encryptedPassword;
}
