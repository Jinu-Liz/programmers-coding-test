package code.test.lv3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Incentive {

  @Test
  void incentive() {
    int result = 1;
    int[][] scores = {{2,2},{1,4}, {3,2},{3,2},{2,1}};
    int[] wanho = scores[0];
    int wanhoScore = wanho[0] + wanho[1];

    // 완호보다 모두 높은 사람 수
    long loserCount = Arrays.stream(scores)
      .filter(score -> score[0] > wanho[0])
      .filter(score -> score[1] > wanho[1])
      .count();

    // 모두 높은 사람이 존재할 경우, 인센티브를 받지 못하므로 return
    if (loserCount > 0) {
      result = -1;
      System.out.println("result = " + result);
      return;
    }

    // 완호보다 점수 합계가 높은 사람들
    List<int[]> higherThanWanhoList =
      Arrays.stream(scores)
        .filter(score -> (score[0] + score[1]) > wanhoScore)
        .sorted(sortByScore())
        .collect(Collectors.toList());

    int[] maxScore = !higherThanWanhoList.isEmpty() ? higherThanWanhoList.get(0) : wanho;
    int[] nextMaxScore = maxScore;
    int noIncentive = 0;

    // 그 중에서 인센티브를 받지 못하는 사람 구하기
    for (int[] score : higherThanWanhoList) {
      if (score[0] != maxScore[0]) {
        if (nextMaxScore[0] != score[0]) {
          if (nextMaxScore[1] > maxScore[1]) maxScore = nextMaxScore;
          nextMaxScore = score;
        }

        if (score[1] < maxScore[1]) noIncentive++;
      }
    }

    // 완호 등수 : 완호보다 높은 사람 - 인센받지 못하는 사람의 다음
    int wanhoRank = higherThanWanhoList.size() - noIncentive + 1;

    result = wanhoRank;
    System.out.println("result = " + result);
  }

  private Comparator<int[]> sortByScore() {
    return (score1, score2) -> (score1[0] != score2[0])
      ? (score2[0] - score1[0])
      : (score2[1] - score1[1]);
  }
}
