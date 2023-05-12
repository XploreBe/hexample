package be.xplore.hexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SpringBootStarter {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootStarter.class);
  }
}
