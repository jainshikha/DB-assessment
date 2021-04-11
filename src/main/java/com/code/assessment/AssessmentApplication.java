package com.code.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class AssessmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssessmentApplication.class, args);
  }
}
