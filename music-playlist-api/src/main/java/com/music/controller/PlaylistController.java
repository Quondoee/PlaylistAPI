package com.music.controller;

import com.music.model.Playlist;
import com.music.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist createPlaylist(@PathVariable Long id , @RequestBody Playlist playlist) {
        logger.info("Creating playlist with name: {}", playlist.getName());
        return playlistService.createPlaylist(id , playlist);
    }

    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable Long id) {
        logger.info("Fetching playlist with ID: {}", id);
        return playlistService.getPlaylistById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Playlist> getPlaylistsByUserId(@PathVariable Long userId) {
        logger.info("Fetching playlists for user with ID: {}", userId);
        return playlistService.getPlaylistsByUserId(userId);
    }

    @GetMapping("/search")
    public List<Playlist> searchPlaylistsByName(@RequestParam String name) {
        logger.info("Searching playlists with name: {}", name);
        return playlistService.searchPlaylistsByName(name);
    }

    @GetMapping
    public Iterable<Playlist> getAllPlaylists() {
        logger.info("Fetching all playlists");
        return playlistService.getAllPlaylists();
    }
    @PutMapping("/{id}")
    public Playlist updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        logger.info("Updating playlist with ID: {}", id);
        return playlistService.updatePlaylist(id, playlist);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaylist(@PathVariable Long id) {
        logger.info("Deleting playlist with ID: {}", id);
        playlistService.deletePlaylist(id);
    }
}
