package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.BaseStream;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  /* useful to create tables through the sql schema file */
  @Bean
  public ApplicationRunner seeder(DatabaseClient client) {
    return args -> getSchema()
            .flatMap(sql -> executeSql(client, sql))
            .subscribe(count -> System.out.println("Schema created"));
  }


  private Mono<String> getSchema() throws IOException {
    InputStream is = new ClassPathResource("schema.sql").getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(is, UTF_8));
    return Flux
            .using(reader::lines, Flux::fromStream, BaseStream::close)
            .reduce((line1, line2) -> line1 + "\n" + line2);
  }

  private Mono<Integer> executeSql(DatabaseClient client, String sql) {
    return client.execute(sql).fetch().rowsUpdated();
  }
}
