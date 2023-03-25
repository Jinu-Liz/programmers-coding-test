package code.test.lv1.kakao;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class PrivacyPolicyTest {

  @Test
  void privacyPolicy() {
    String today = "2022.05.19";
    String[] terms = {"A 6", "B 12", "C 3"};
    String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    LocalDate toDt = LocalDate.parse(today, formatter);

    Map<String, Integer> termMap = Arrays.stream(terms)
      .collect(
        Collectors.toMap(
          term -> term.split(" ")[0],
          term -> Integer.parseInt(term.split(" ")[1])
        )
      );

    List<Integer> result = new ArrayList<>();
    AtomicInteger index = new AtomicInteger(1);

    Arrays.stream(privacies)
      .forEach(privacy -> {
        int number = index.getAndIncrement();
        String day = privacy.split(" ")[0];
        String term = privacy.split(" ")[1];
        LocalDate targetDay = LocalDate.parse(day, formatter);
        LocalDate finishDay = targetDay.plusMonths(termMap.get(term));

        if (!toDt.isBefore(finishDay)) result.add(number);
      });

    int[] answer = result.stream().mapToInt(Integer::intValue).toArray();

    assertThat(answer).isEqualTo(new int[] {1, 3});
  }
}
