package pl.koszela.jan.UserAuthentication;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserAuthenticationApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(UserAuthenticationApplication.class);
  }

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(UserAuthenticationApplication.class, args);

    System.out.println("Inspect the beans provided by Spring Boot: ");
    String[] beanNames = context.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String bean : beanNames) {
      System.out.println(bean);
    }

  }
}
