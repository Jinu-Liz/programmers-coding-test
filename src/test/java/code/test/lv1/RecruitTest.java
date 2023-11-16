package code.test.lv1;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class RecruitTest {

  @Test
  void change() {
    String line = "1010101010";
    int k = 2;
    String answer = line;

    for (int j = 0; j < k; j++) {
      String[] arr = line.split("");
      String bfStr = null;
      String curStr = null;
      StringBuilder curAnswer = new StringBuilder();

      StringBuilder result = new StringBuilder();
      for (int i = 0; i < arr.length; i++) {
        curStr = arr[i];
        if (bfStr == null) {
          bfStr = curStr;
          result.append(curStr);
          continue;

        } else if (bfStr.equals(curStr)) {
          result.append(curStr);
          continue;

        } else {
          String binaryString = Integer.toBinaryString(result.length());
          curAnswer.append(binaryString);
        }

        bfStr = curStr;
        result = new StringBuilder();
        result.append(curStr);
      }

      curAnswer.append(Integer.toBinaryString(result.length()));
      answer = curAnswer.toString();
    }

    System.out.println("answer = " + answer);
  }

  @Test
  void change2() {
    String line = "01";
    int k = 3;

    for (int i = 0; i < k; i++) {
      line = toBinaryStr(line);
    }

    System.out.println("line = " + line);
  }

  private static String toBinaryStr(String line) {
    StringBuilder answer = new StringBuilder();
    String[] strArr = line.split("");
    int arrLen = strArr.length;
    int lastIdx = arrLen - 1;

    int sameCnt = 1;
    for (int i = 0; i < arrLen; i++) {
      int nextIdx = i + 1;
      String curChar = strArr[i];
      String nextChar;
      if (lastIdx >= nextIdx) {
        nextChar = strArr[nextIdx];

        if (nextChar.equals(curChar)) {
          sameCnt++;
        } else {
          answer.append(Integer.toBinaryString(sameCnt));
          sameCnt = 1;
        }

      } else {
        answer.append(Integer.toBinaryString(sameCnt));
        sameCnt = 1;
      }
    }

    return answer.toString();
  }

  @Test
  void aNumber() {
    int[] arr = {0, 1, 2, 5, 3, 7};
    List<Integer> list = Arrays.stream(arr)
      .boxed()
      .collect(Collectors.toList());

    int result = 0;
    while (!list.isEmpty()) {
      result += getResult(list);
      list.remove(0);
    }

    System.out.println("result = " + result);
  }

  private static int getResult(List<Integer> list) {
    int lastIdx = list.size() - 1;
    int result = 0;
    for (int i = 0 ; i < lastIdx; i++) {
      System.out.println("커브 전 숫자 : " + list.get(i));
      int next = i + 1;
      System.out.println("커브 전 다음 숫자 : " + list.get(next));
      if (list.get(i) <= list.get(next)) continue;

      int curvePoint = next;
      for (int j = curvePoint; j < lastIdx; j++) {
        System.out.println("커브 후 숫자 : " + list.get(j));
        System.out.println("커브 후 다음 숫자 : " + list.get(j + 1));
        if (list.get(j) > list.get(j + 1)) break;
        result++;
      }
    }

    return result;
  }

  @Test
  void aNumber2() {
    int[] arr = {0,1,2,5,3,7};
    int arrLen = arr.length;
    int lastIdx = arrLen - 1;

    int answerCnt = 0;
    for (int i = 0; i < arrLen; i++) {
      boolean up = false;
      boolean isPeak = false;

      int curIdx = i;
      while (!isPeak) {
        int nextIdx = curIdx + 1;
        if (nextIdx > lastIdx) break;
        int tempCurNum = arr[curIdx];
        int tempNextNum = arr[nextIdx];

        if (tempCurNum > tempNextNum) {
          isPeak = true;
        } else if (tempCurNum < tempNextNum) {
          up = true;
        }

        if (isPeak) {
          for (int j = curIdx; j < lastIdx; j++) {
            if (arr[j] <= arr[j + 1]) break;
            if (up) answerCnt++;
          }
        }

        curIdx++;
      }
    }

    System.out.println("answerCnt = " + answerCnt);
  }

  /**
   * 루트가 정해지지 않은 트리가 있습니다. 당신은 이 트리를 다음과 같은 방식으로 탐색하려고 합니다.
   * 1. 현재 트리의 리프 노드들 중에서 번호가 제일 작은 노드를 탐색합니다.
   * 2. 1번 과정에서 탐색한 노드를 트리로부터 제거합니다.
   * 3. 트리에 노드가 남아있다면, 다시 1번 과정으로 돌아갑니다.
   *
   * 여기서 리프 노드란, 트리의 유일한 노드이거나, 또는 차수가 1인 노드를 의미합니다.
   * 처음 트리의 간선들의 정보를 담고 있는 2차원 배열 edges가 매개변수로 주어집니다.
   * 주어진 트리를 위와 같은 방식으로 순회했을 때, 순회하는 노드들의 번호를 차례대로 배열에 담아
   * return하도록 solution 함수를 완성해주세요.
   *
   * 제한사항
   *  1 <= edges의 길이 < 200,000
   *  edges의 각 행은 [v1, v2] 2개의 정수로 이루어져 있으며, 이는 v1번 정점과 v2번 정점 사이에 간선이 있음을 의미합니다.
   *  1 <= v1 <= edges의 길이 + 1
   *  1 <= v2 <= edges의 길이 + 1
   *  v1 != v2
   *  edges가 의미하는 그래프가 트리가 아닌 경우는 주어지지 않습니다.
   *
   *  입출력 예
   *  edges : [[1,4],[4,5],[5,2],[2,3],[2,6]]   result : [1,3,4,5,2,6]
   *  edges : [[2,1],[1,3]]   result : [2,1,3]
   */
  @Test
  void findLeaf() {
    int[][] edges = {{1,4}, {4,5}, {5,2}, {2,3}, {2,6}};
    int edgesLen = edges.length;
    List<Integer> answer = new ArrayList<>();
    answer.add(edges[0][0]);
    edges[0][0] = 0;

    for (int i = 0; i < edgesLen - 1; i++) {
      int[] edge = edges[i];
      int[] nextEdge = edges[i + 1];
      int fir = edge[0];
      int sec = edge[1];
      int nextFir = nextEdge[0];
      int nextSec = nextEdge[1];

      int curIdx = i;
      if (fir == 0) {
        while (sec != nextFir) {

        }
      }
    }
  }
}
