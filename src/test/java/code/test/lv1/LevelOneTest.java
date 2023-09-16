package code.test.lv1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LevelOneTest {

  @Test
  void memoryScore() {
    String[] name = {"may", "kein", "kain", "radi"};
    int[] yearning = {5, 10, 1, 3};
    String[][] photo = {{"may", "kein", "kain", "radi"},{"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}};

    Map<String, Integer> scoreMap = new HashMap<>();
    for (int i = 0; i < name.length; i++) {
      scoreMap.put(name[i], yearning[i]);
    }

    int[] array = Arrays.stream(photo)
      .mapToInt(
        score -> Arrays.stream(score)
          .map(member -> {
            if (scoreMap.containsKey(member)) return scoreMap.get(member);
            return 0;
          }).reduce(Integer::sum).get()
      ).toArray();

    System.out.println(Arrays.toString(array));
  }

  @Test
  void wierdKeyboard() {
    String[] keymap = {"ABACD", "BCEFD"};
    String[] targets = {"ABCD","AABB"};

    Map<Character, Integer> mapping = new HashMap<>();
    for (String nowStr : keymap) {
      char[] charArray = nowStr.toCharArray();
      for (int j = 0; j < charArray.length; j++) {
        int count = j + 1;
        char str = charArray[j];
        if (!mapping.containsKey(str)) {
          mapping.put(str, count);
        } else {
          mapping.put(str, Math.min(mapping.get(str), count));
        }
      }
    }

    int[] result = new int[targets.length];
    for (int i = 0; i < targets.length; i++) {
      int count = 0;
      String nowStr = targets[i];
      char[] charArray = nowStr.toCharArray();
      for (int j = 0; j < charArray.length; j++) {
        char str = charArray[j];
        if (!mapping.containsKey(str)) {
          count = -1;
          break;
        } else {
          count += mapping.get(str);
        }
      }
      result[i] = count;
    }

    assertThat(result).isEqualTo(new int[]{9, 4});
  }

  @Test
  void smallSizeString() {
    String t = "3141592";
    String p = "271";

    int pSize = p.length();
    int result = 0;
    for (int i = 0; i < t.length(); i++) {
      int lastNum = pSize + i;
      if (lastNum > t.length()) break;

      long tNumber = Long.parseLong(t.substring(i, lastNum));
      long pNumber = Long.parseLong(p);

      if (tNumber <= pNumber) result++;
    }

    assertThat(result).isEqualTo(2);
  }

  @Test
  void running() {
    String[] players = {"mumu", "soe", "poe", "kai", "mine"};
    String[] callings = {"kai", "kai", "mine", "mine"};

    Map<String, Integer> playerRankMap = new HashMap<>();
    for (int i = 0; i < players.length; i++) {
      playerRankMap.put(players[i], i);
    }

    for (int i = 0; i < callings.length; i++) {
      String calledPlayer = callings[i];
      Integer calledIdx = playerRankMap.get(calledPlayer);
      Integer bfIdx = calledIdx - 1;
      String bfPlayer = players[bfIdx];
      players[bfIdx] = calledPlayer;
      players[calledIdx] = bfPlayer;

      playerRankMap.put(calledPlayer, bfIdx);
      playerRankMap.put(bfPlayer, calledIdx);
    }

    assertThat(players).isEqualTo(new String[]{"mumu", "kai", "mine", "soe", "poe"});
  }

}
