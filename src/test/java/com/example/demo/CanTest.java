package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class CanTest {

  @Test
  @DisplayName("can test")
  void can_test() {
    then(true).isTrue();
  }

}
