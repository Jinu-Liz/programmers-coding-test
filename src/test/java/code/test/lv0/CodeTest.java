package code.test.lv0;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

  /**
   * 과일 장수
   */
  @Test
  void fruitSeller() {
    int k = 3;
    int m = 4;
    int[] score = {1, 2, 3, 1, 2, 3, 1};
    int boxCnt = Math.abs(score.length / m);

    Arrays.sort(score);
    List<Integer> scoreList = Arrays.stream(score)
      .boxed()
      .collect(Collectors.toList());

    int answer = 0;
    for (int i = 1; i <= boxCnt; i++) {
      int lastIndex = scoreList.size();
      int index = lastIndex - (i * m);
      answer += scoreList.get(index) * m;
    }

    assertThat(answer).isEqualTo(8);
  }

  /**
   * 정렬
   */
  @Test
  void sort() {
    int[] arr = {8, 5, 3, 1, 6, 2, 4, 7};

    for (int j=0; j < arr.length; j++) {
      for (int i=0; i < arr.length - 1; i++) {
        int a = Math.max(arr[i], arr[i + 1]);
        int b = arr[i + 1];

        if (a > b) {
          arr[i] = b;
          arr[i + 1] = a;
        }
      }
    }

    System.out.println(Arrays.toString(arr));
  }

  @Test
  void discount() {
    int price = 150000;
    double result = 0;

    if (price > 500000) {
      result = price * 0.8;
    } else if (price > 300000) {
      result = price * 0.9;
    } else {
      result = price * 0.95;
    }

    System.out.println("result = " + result);
  }
}
