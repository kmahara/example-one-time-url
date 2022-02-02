package sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "crypto")
@Getter
@Setter
public class CryptoConfig {
  // システム内部のパスワード。hex 値
  private String salt;

  @Bean
  public BytesEncryptor bytesEncryptor() {
    return Encryptors.standard(salt, salt);
  }

  public BytesEncryptor bytesEncryptor(String password) {
    return Encryptors.standard(password, salt);
  }
}
