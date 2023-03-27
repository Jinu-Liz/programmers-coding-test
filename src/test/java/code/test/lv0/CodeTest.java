package code.test.lv0;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeTest {

  /**
   * 특정 문자 제거하기
   */
  @Test
  void deleteString() {
    String myString = "BCBdbe";
    String letter = "B";
    String result = myString.replaceAll(letter, "");

    assertThat(result).isEqualTo("Cdbe");

  }

}
