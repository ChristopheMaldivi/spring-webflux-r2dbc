package com.example.demo;

import com.example.demo.persistence.CountryPersistence;
import com.example.demo.persistence.CountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/countries")
public class Controller {

  private final CountryClient countryClient = new CountryClient();
  private final CountryPersistence countryPersistence;

  public Controller(CountryRepository countryPersistence) {
    this.countryPersistence = countryPersistence;
  }

  @GetMapping("/{name}")
  private Mono<CountriesDTO> getCountryByName(@PathVariable String name) {
    // Call ASYNC 'countries REST API'
    Mono<CountriesDTO> countriesDTO = countryClient.getByName(name);

    // Then CHAIN ASYNC 'Persist in database'
    Mono<Void> persistDone = countryPersistence.persistCountryInfo(countriesDTO);

    // Then CHAIN ASYNC -> return countries data transfer objects
    return persistDone
            .then(countriesDTO)
            // Async stuff logs (whole CHAIN), useful to understand what is going on
            .log();
  }

  @GetMapping("/all")
  private Flux<CountryDTO> getCountries() {
    return countryPersistence
            .getAllCountries()
            // Async stuff logs (whole CHAIN), useful to understand what is going on
            .log();
  }
}
