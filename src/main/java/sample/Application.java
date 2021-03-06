/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  public static void run(String[] args, Class<? extends ApplicationRunner> clazz) throws Exception {
    SpringApplication application = new SpringApplication(Application.class);
    application.setWebApplicationType(WebApplicationType.NONE);

    try (ConfigurableApplicationContext ctx = application.run(args)) {
      ApplicationRunner bean = ctx.getBean(clazz);
      bean.run();
    };
  }
}
