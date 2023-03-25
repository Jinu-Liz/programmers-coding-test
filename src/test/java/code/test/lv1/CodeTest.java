package code.test.lv1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeTest {

  @Test
  void secretCodeOnlyForTwo() {
    String s = "aukks";
    String skip = "wbqd";
    int index = 5;

    List<String> alphabet = IntStream.range(97, 123)
      .filter(ascii -> skip.chars().allMatch(ch -> ascii != ch))
      .mapToObj(ascii -> Character.toString((char) ascii))
      .collect(Collectors.toList());

    String answer = Arrays.stream(s.split(""))
      .map(string -> {
        int nextIdx = alphabet.indexOf(string) + index;
        int lastIdx = alphabet.size();
        int remainder = nextIdx % lastIdx;

        return alphabet.get(remainder);
      })
      .collect(Collectors.joining());

    assertThat(answer).isEqualTo("happy");
  }

}
