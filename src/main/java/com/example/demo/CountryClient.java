package com.example.demo;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class CountryClient {
  private final WebClient client = WebClient.create("http://restcountries.eu/rest/v2/name");

  public Mono<CountriesDTO> getByName(String name) {
    Mono<CountriesDTO> countries = client.get()
            .uri("/{name}", name)
            .retrieve()
            .bodyToMono(CountriesDTO.class);

    return countries;
  }
}
