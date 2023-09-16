package code.test.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTest {

  @Test
  void lottoRank() {
    int[] lottos = {44, 1, 0, 0, 31, 25};
    int[] win_nums = {31, 10, 45, 1, 6, 19};

    Set<Integer> winNumSet = Arrays.stream(win_nums)
      .boxed()
      .collect(Collectors.toSet());

    Map<Integer, Integer> rankMap = new HashMap<>();
    rankMap.put(2, 5);
    rankMap.put(3, 4);
    rankMap.put(4, 3);
    rankMap.put(5, 2);
    rankMap.put(6, 1);

    int wildCard = 0;
    int winCnt = 0;
    for (int number : lottos) {
      if (number == 0) wildCard++;
      if (winNumSet.contains(number)) winCnt++;
    }

    int rank = 0;
    int[] result;
    int highRank = winCnt + wildCard;
    if (!rankMap.containsKey(highRank)) {
      result = new int[]{6, 6};
    }

    highRank = rankMap.get(highRank);
    if (!rankMap.containsKey(winCnt)) {
      result = new int[]{highRank, 0};
    }

    int lowRank = rankMap.get(winCnt);
    result = new int[]{highRank, lowRank};

    Assertions.assertThat(result).isEqualTo(new int[]{3, 5});
  }
}
