package sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;
import sample.service.CryptoService;

@RequiredArgsConstructor
@RestController
public class SampleController {
  private static final long URL_TIMEOUT_MILLIS = 1000L * 60;

  private final CryptoService cryptoService;

  @GetMapping("/encode/{path}")
  public String encode(@PathVariable("path") String path) {
    long currentMillis = System.currentTimeMillis();

    String originalText = path + "," + currentMillis;

    String encText = cryptoService.encodeText(originalText);

    return "http://localhost:8080/decode/" + encText + "\n";
  }

  @GetMapping("/decode/{token}")
  public String getFile(@PathVariable("token") String token) {
    String text = cryptoService.decodeText(token);

    String[] splits = text.split(",");
    long now = System.currentTimeMillis();
    long createMillis = Long.parseLong(splits[1]);

    if (now - createMillis > URL_TIMEOUT_MILLIS) {
      throw new ResponseStatusException(HttpStatus.GONE);
    }

    return splits[0]+ "\n";
  }
}
