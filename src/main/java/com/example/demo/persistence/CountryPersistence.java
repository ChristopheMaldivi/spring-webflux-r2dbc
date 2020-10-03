package com.example.demo.persistence;

import com.example.demo.CountriesDTO;
import com.example.demo.CountryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryPersistence {

  Mono<Void> persistCountryInfo(Mono<CountriesDTO> countriesDTO);

  Flux<CountryDTO> getAllCountries();
}
