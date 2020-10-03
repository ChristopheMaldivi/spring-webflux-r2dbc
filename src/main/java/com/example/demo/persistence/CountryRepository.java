package com.example.demo.persistence;

import com.example.demo.CountriesDTO;
import com.example.demo.CountryDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface CountryRepository extends ReactiveCrudRepository<CountryEntity, Long>, CountryPersistence {

  @Override
  default Mono<Void> persistCountryInfo(Mono<CountriesDTO> countriesDTO) {
    return countriesDTO
            // map transform data synchronously (no I/O involved here)
            .map(this::retrieveOneCountryEntity)
            .filter(Optional::isPresent)
            // flatMap perform some task asynchronously, CHAIN ASYNC (I/O with the database here)
            .flatMap(entityOptional -> save(entityOptional.get()))
            // then is the equivalent to 'return;', but in a CHAIN ASYNC way
            .then();
  }

  default Optional<CountryEntity> retrieveOneCountryEntity(CountriesDTO countryDTOS) {
    return countryDTOS.stream()
            .findFirst()
            // map transform data synchronously (no I/O involved here)
            .map(dto -> new CountryEntity(dto.name, dto.capital));
  }

  default Flux<CountryDTO> getAllCountries() {
    return findAll()
            // map transform data synchronously (no I/O involved here)
            .map(entity -> CountryDTO.with(entity.name, entity.capital));
  }
}
