package code.test.lv1;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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

  /**
   * 2016년
   */
  @Test
  void dayOf2016() {
    int a = 5;
    int b = 24;
    LocalDate day = LocalDate.of(2016, a, b);
    DayOfWeek dayOfWeek = day.getDayOfWeek();
    String answer = dayOfWeek.toString().substring(0, 3);

    assertThat(answer).isEqualTo("TUE");
  }

  /**
   * 문자열 내 p와 y의 개수
   */
  @Test
  void pAndYCount() {
    String s = "pPoooyY";
    String[] stringArr = s.toLowerCase().split("");
    AtomicInteger pCount = new AtomicInteger();
    AtomicInteger yCount = new AtomicInteger();
    Arrays.stream(stringArr)
      .forEach(string -> {
        if ("p".equals(string)) pCount.getAndIncrement();
        if ("y".equals(string)) yCount.getAndIncrement();
      });

    boolean answer = pCount.get() == yCount.get();
    assertThat(answer).isTrue();
  }

  /**
   * 같은 숫자는 싫어
   */
  @Test
  void hateSameNumber() {
    int[] arr = {1, 1, 3, 3, 0, 1, 1};
    List<Integer> result = new ArrayList<>();
    for (int i = 1; i < arr.length; i++) {
      int now = arr[i];
      int next = arr[i - 1];

      if (now != next) result.add(next);
    }
    result.add(arr[arr.length - 1]);

    int[] answer = result.stream()
      .mapToInt(Integer::intValue)
      .toArray();

    assertThat(answer).isEqualTo(new int[] {1, 3, 0, 1});
  }
}
