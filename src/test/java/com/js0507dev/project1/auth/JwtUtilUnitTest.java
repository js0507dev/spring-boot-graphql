package com.js0507dev.project1.auth;

import com.github.javafaker.Faker;
import com.js0507dev.project1.auth.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilUnitTest {
  private JwtUtil jwtUtil = new JwtUtil();
  private final Faker faker = new Faker();

  @BeforeEach
  public void setup() {
    // TODO: application.yaml 파일에서 읽어오도록 변경
    ReflectionTestUtils.setField(jwtUtil, "secretKey",
        "R4GJibxmN7x0pvKZjNVA8CxQELk0YLuPrGMahj7iZy0LmEPnbgUUg68FugpHChTE");
    ReflectionTestUtils.setField(jwtUtil, "jwtExpiration", 86400000);
    ReflectionTestUtils.setField(jwtUtil, "refreshExpiration", 604800000);
  }

  @DisplayName("Bearer 토큰이 포함된 문자열을 입력받으면 true를 리턴한다.")
  @Test
  public void givenBearerToken_whenHasJwt_thenReturnTrue() {
    String bearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
        ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ" +
        ".SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    Boolean result = jwtUtil.hasJwt(bearerToken);

    assertTrue(result);
  }

  @DisplayName("Bearer 토큰이 포함되지 않은 문자열을 입력받으면 false를 리턴한다.")
  @Test
  public void givenBasicToken_whenHasJwt_thenReturnFalse() {
    String basicToken = "Basic eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
        ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ" +
        ".SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    Boolean result = jwtUtil.hasJwt(basicToken);

    assertFalse(result);
  }

  @DisplayName("Bearer 토큰이 포함된 문자열을 입력받으면 jwt를 리턴한다.")
  @Test
  public void givenBearerToken_whenExtractJwt_thenReturnJwt() {
    String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    String bearerToken = "Bearer " + jwt;

    String foundJwt = jwtUtil.extractJwt(bearerToken);

    assertEquals(jwt, foundJwt);
  }

  @DisplayName("Bearer 토큰이 포함되지 않은 문자열을 입력받으면 잘못된 토큰을 리턴한다.")
  @Test
  public void givenBasicToken_whenExtractJwt_thenReturnInvalidJwt() {
    String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    String basicToken = "Basic " + jwt;

    String foundJwt = jwtUtil.extractJwt(basicToken);

    assertNotEquals(jwt, foundJwt);
  }

  @DisplayName("jwt 토큰에서 email을 추출한다.")
  @Test
  public void givenGeneratedJwt_whenExtractEmail_thenReturnEmailString() {
    String email = faker.internet().emailAddress();
    String jwt = jwtUtil.generateJwt(email);

    String foundEmail = jwtUtil.extractEmail(jwt);

    assertEquals(email, foundEmail);
  }

  @DisplayName("만료된 jwt를 입력하면 true를 리턴한다.")
  @Test
  public void givenExpiredJwt_whenIsExpired_thenReturnTrue() throws InterruptedException {
    String email = faker.internet().emailAddress();
    // 만료시간 0으로 설정
    ReflectionTestUtils.setField(jwtUtil, "jwtExpiration", 1);
    String jwt = jwtUtil.generateJwt(email);
    Thread.sleep(5);

    Boolean expired = jwtUtil.isExpired(jwt);

    assertTrue(expired);
  }

  @DisplayName("jwt 생성을 요청하면 정상적인 jwt를 리턴한다.")
  @Test
  public void givenRandomEmail_whenGenerateJwt_thenReturnJwt() {
    String email = faker.internet().emailAddress();

    String generated = jwtUtil.generateJwt(email);

    assertNotNull(generated);
  }
}
