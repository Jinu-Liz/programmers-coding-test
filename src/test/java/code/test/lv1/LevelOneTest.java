package code.test.lv1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    Map<String, Integer> mapping = new HashMap<>();
    for (int i = 0; i < keymap.length; i++) {
      String nowStr = keymap[i];
      char[] charArray = nowStr.toCharArray();
      for (int j = 0; j < charArray.length; j++) {
        int count = j + 1;
        String str = String.valueOf(charArray[j]);
        if (!mapping.containsKey(str)) {
          mapping.put(str, count);
        } else {
          if (mapping.get(str) > count) mapping.put(str, count);
        }
      }
    }

    int[] result = new int[targets.length];
    for (int i = 0; i < targets.length; i++) {
      int count = 0;
      String nowStr = targets[i];
      char[] charArray = nowStr.toCharArray();
      for (int j = 0; j < charArray.length; j++) {
        String str = String.valueOf(charArray[j]);
        if (!mapping.containsKey(str)) {
          count = -1;
          break;
        } else {
          count += mapping.get(str);
        }
      }
      result[i] = count;
    }

    System.out.println(Arrays.toString(result));
  }
}
