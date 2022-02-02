package sample.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.Application;
import sample.ApplicationRunner;
import sample.service.CryptoService;

@Component
public class CreateUrl implements ApplicationRunner {
  @Autowired
  private CryptoService cryptoService;

  @Override
  public void run() throws Exception {
    String filename = "daily/2022/02/01/user000001.pdf";
    long currentMillis = System.currentTimeMillis();

    String originalText = filename + "," + currentMillis;

    {
      String encText2 = cryptoService.encodeText(originalText);
      System.out.println("http://localhost:8080/decode/" + encText2);
      System.out.println("decode: " + cryptoService.decodeText(encText2));
    }
  }

  public static void main(String[] args) throws Exception {
    Application.run(args, CreateUrl.class);
  }
}
