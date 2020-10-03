package com.example.demo;

public class CountryDTO {
  public String name;
  public String capital;

  public static CountryDTO with(String name, String capital) {
    CountryDTO dto = new CountryDTO();
    dto.name = name;
    dto.capital = capital;
    return dto;
  }
}
