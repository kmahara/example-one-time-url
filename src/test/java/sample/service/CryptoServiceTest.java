package sample.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CryptoServiceTest {
  private final String filename = "daily/2022/02/01/user000001.pdf";

  @Autowired private CryptoService cryptoService;

  // パスワード指定なしで暗号化・復号化を行う。
  @Test
  void test1() {
    // 現在時刻
    long currentMillis = System.currentTimeMillis();
    String originalText = filename + "," + currentMillis;

    String encText = cryptoService.encodeText(originalText);
    String decText = cryptoService.decodeText(encText);
    assertEquals(originalText, decText);
  }

  // パスワード指定ありで暗号化・復号化を行う。
  @Test
  void test2() {
    String filePassword = "abcedef";

    // 現在時刻
    long currentMillis = System.currentTimeMillis();
    String originalText = filename + "," + currentMillis;

    String encText = cryptoService.encodeText(originalText, filePassword);
    String decText = cryptoService.decodeText(encText, filePassword);
    assertEquals(originalText, decText);
  }

  // 複合時にパスワードが異なるとエラーとなる。
  @Test
  void test3() {
    String filePassword = "abcedef";
    String filePassword2 = "12345";

    // 現在時刻
    long currentMillis = System.currentTimeMillis();
    String originalText = filename + "," + currentMillis;

    String encText = cryptoService.encodeText(originalText, filePassword);

    assertThrows(
        IllegalStateException.class, () -> cryptoService.decodeText(encText, filePassword2));
  }
}
