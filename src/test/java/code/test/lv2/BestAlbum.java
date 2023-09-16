package code.test.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BestAlbum {

  @Test
  void best() {
    String[] genres = {"classic", "pop", "classic", "classic", "pop"};
    int[] plays = {500, 600, 150, 800, 2500};

    Map<String, List<Album>> albumMap = new HashMap<>();
    Map<String, Integer> genreCountMap = new HashMap<>();

    for (int i = 0; i < genres.length; i++) {
      String curGenre = genres[i];
      int curPlay = plays[i];
      Album album = new Album(i, genres[i], plays[i]);

      if (albumMap.containsKey(curGenre)) {
        List<Album> curAlbumList = albumMap.get(curGenre);
        curAlbumList.add(album);
        albumMap.put(curGenre, curAlbumList);

      } else {
        List<Album> albumList = new ArrayList<>();
        albumList.add(album);
        albumMap.put(curGenre, albumList);
      }

      genreCountMap.put(curGenre, genreCountMap.getOrDefault(curGenre, 0) + curPlay);
    }

    List<Genre> genreList = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : genreCountMap.entrySet()) {
      Genre genre = new Genre(entry.getKey(), entry.getValue());
      genreList.add(genre);
    }

    genreList.sort(sortByTotal());

    List<Album> resultList = new ArrayList<>();
    for (Genre genre : genreList) {
      List<Album> albums = albumMap.get(genre.getName());
      albums.sort(sortByPlay());

      resultList.add(albums.get(0));
      if (albums.size() >= 2) resultList.add(albums.get(1));
    }

    int[] result = resultList.stream()
      .mapToInt(Album::getId)
      .toArray();

    Assertions.assertThat(result).isEqualTo(new int[]{4, 1, 3, 0});
  }

  class Album {

    private int id;

    private String genres;

    private int plays;

    public Album(int id, String genres, int plays) {
      this.id = id;
      this.genres = genres;
      this.plays = plays;
    }

    public int getId() {
      return this.id;
    }

    public String getGenres() {
      return this.genres;
    }

    public int getPlays() {
      return this.plays;
    }
  }

  class Genre {

    public Genre(String name, int total) {
      this.name = name;
      this.totalPlays = total;
    }

    private String name;

    private int totalPlays;

    public String getName() {
      return name;
    }

    public int getTotalPlays() {
      return totalPlays;
    }
  }

  private Comparator<Album> sortByPlay() {
    return (album1, album2) -> album2.getPlays() - album1.getPlays();
  }

  private Comparator<Genre> sortByTotal() {
    return (genre1, genre2) -> genre2.getTotalPlays() - genre1.getTotalPlays();
  }
}
