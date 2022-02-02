package sample.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.stereotype.Service;
import sample.config.CryptoConfig;

@Service
public class CryptoService {
  private final CryptoConfig cryptoConfig;
  private final BytesEncryptor encryptor;

  private final Encoder encoder = Base64.getUrlEncoder().withoutPadding();
  private final Decoder decoder = Base64.getUrlDecoder();

  public CryptoService(CryptoConfig cryptoConfig) {
    this.cryptoConfig = cryptoConfig;
    this.encryptor = cryptoConfig.bytesEncryptor();
  }

  public String encodeText(String originalText) {
    byte[] originalBytes = originalText.getBytes();

    byte[] encBytes = encryptor.encrypt(originalBytes);
    String encText = encoder.encodeToString(encBytes);
    return encText;
  }

  public String decodeText(String encText) {
    byte[] encBytes = decoder.decode(encText);
    byte[] originalBytes = encryptor.decrypt(encBytes);
    return new String(originalBytes);
  }

  public String encodeText(String originalText, String password) {
    byte[] originalBytes = originalText.getBytes();

    BytesEncryptor encryptor = cryptoConfig.bytesEncryptor(password);

    byte[] encBytes = encryptor.encrypt(originalBytes);
    String encText = encoder.encodeToString(encBytes);
    return encText;
  }

  public String decodeText(String encText, String password) {
    byte[] encBytes = decoder.decode(encText);

    BytesEncryptor encryptor = cryptoConfig.bytesEncryptor(password);

    byte[] originalBytes = encryptor.decrypt(encBytes);
    return new String(originalBytes);
  }
}
