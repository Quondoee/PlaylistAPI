package com.music.controller;

import com.music.model.Song;
import com.music.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongService songService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Song createSong(@PathVariable Long id , @RequestBody Song song) {
        logger.info("Creating new song with title: {}", song.getTitle());
        return songService.createSong(id ,song);
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) {
        logger.info("Fetching song with ID: {}", id);
        return songService.getSongById(id);
    }

    @GetMapping("/artist/search")
    public ResponseEntity <Iterable <Song>>  searchSongsbyartist(@RequestParam String artist) {
        logger.info("Searching for songs with query: {}", artist);
        Iterable<Song> songs =  songService.searchSongsbyartist(artist);
        return new ResponseEntity<>(songs , HttpStatus.OK);
    }

    @GetMapping("/title/search")
    public ResponseEntity <Iterable <Song>>  searchSongsbytitle(@RequestParam String title) {
        logger.info("Searching for songs with query: {}", title);
       Iterable<Song> songs =  songService.searchSongsbytitle(title);
        return new ResponseEntity<>(songs , HttpStatus.OK);
    }
    @GetMapping
    public Iterable<Song> getAllSongs() {
        logger.info("Fetching all songs");
        return songService.getAllSongs();
    }
    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        logger.info("Updating song with ID: {}", id);
        return songService.updateSong(id, song);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSong(@PathVariable Long id) {
        logger.info("Deleting song with ID: {}", id);
        songService.deleteSong(id);
    }
}
