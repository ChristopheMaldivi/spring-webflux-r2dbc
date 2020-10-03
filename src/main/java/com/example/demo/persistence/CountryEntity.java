package com.example.demo.persistence;

import org.springframework.data.annotation.Id;

public class CountryEntity {
  @Id
  public Long id;

  public String name;
  public String capital;

  public CountryEntity(String name, String capital) {
    this.name = name;
    this.capital = capital;
  }
}
