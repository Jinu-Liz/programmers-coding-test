package code.test.lv2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Clothes {

  @Test
  void clothesCombination() {
    String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

    // 의상을 그룹별로 나눈다.
    Map<String, List<String[]>> collect = Arrays.stream(clothes)
      .collect(
        groupingBy(cloth -> cloth[1])
      );

    int count = 1;
    for (List<String[]> value : collect.values()) {
      // 해당 의상을 입지 않았을 경우를 포함하여 가짓 수 + 1. 그 후, 모든 경우의 수를 구한다.
      count *= (value.size() + 1);
    }

    // 모두 입지 않았을 경우를 제외해야 하여 -1을 한다.
    System.out.println(count - 1);
  }


  // 다른 답
  @Test
  void combi() {
    String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

    int i = Arrays.stream(clothes)
      .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
      .values()
      .stream()
      .reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
  }
}
