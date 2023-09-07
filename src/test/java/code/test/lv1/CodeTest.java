package code.test.lv1;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeTest {

  @Test
  void secretCodeOnlyForTwo() {
    String s = "aukks";
    String skip = "wbqd";
    int index = 5;

    List<String> alphabet = IntStream.range('a', 'z' + 1)
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

  /**
   * 가운데 글자 가져오기
   */
  @Test
  void centerLetter()  {
    String s = "abcde";
    int length = s.length();
    int centerLength = length / 2;
    System.out.println("length % 2 = " + length % 2);
    String a = ((length % 2) == 1) ? s.substring(centerLength, centerLength + 1) : s.substring(centerLength - 1, centerLength + 1);
    System.out.println("a = " + a);
  }

  @Test
  void divisor() {
    int[] arr = { 5, 9, 7, 10 };
    int divisor = 5;

    int[] answer = Arrays.stream(arr)
      .filter(num -> num % divisor == 0)
      .sorted()
      .toArray();

    int[] arr2 = answer.length != 0 ? answer : new int[] {-1};
    assertThat(arr2).isEqualTo(new int[] {5, 10});
  }

  /**
   * 두 정수 사이의 합
   */
  @Test
  void plusNumbers() {
    int a = -1, b = -10000000;
    int min = Math.min(a, b);
    int max = Math.max(a, b);

    long sum = LongStream.range(min, max + 1).sum();
    System.out.println("sum = " + sum);
//    long answer = a != b ? sum : a;
//    assertThat(answer).isEqualTo(12);
  }

  @Test
  void stringSorting() {
    String[] arr = {"sun", "bed", "car"};
    List<String> collect = Arrays.stream(arr)
      .sorted()
      .collect(Collectors.toList());
    System.out.println("collect = " + collect);
  }
}
