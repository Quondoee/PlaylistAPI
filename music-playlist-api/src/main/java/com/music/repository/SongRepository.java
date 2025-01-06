package com.music.repository;

import com.music.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findByTitle(@Param("title")String title);

    List<Song> findByArtist(@Param("artist")String artist);
}
