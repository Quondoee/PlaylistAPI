package com.music.repository;


import com.music.model.Playlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {

    
    List<Playlist> findByNameContainingIgnoreCase(String name);

    List<Playlist> findByUserId(Long userId);
}
