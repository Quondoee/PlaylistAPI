package com.music.service;

import com.music.model.Playlist;
import com.music.model.User;
import com.music.repository.PlaylistRepository;
import com.music.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserService userService;

    public Playlist createPlaylist(Long id , Playlist playlist) {
        User user = userService.getUserById(id);
        playlist.setUser(user);
        return playlistRepository.save(playlist);
    }

    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist not found with id " + id));
    }

    public List<Playlist> getPlaylistsByUserId(Long userId) {
        return playlistRepository.findByUserId(userId);
    }

    public List<Playlist> searchPlaylistsByName(String name) {
        return playlistRepository.findByNameContainingIgnoreCase(name);
    }

    public Iterable<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public void deletePlaylist(Long id) {
        if (!playlistRepository.existsById(id)) {
            throw new ResourceNotFoundException("Playlist not found with id " + id);
        }
        playlistRepository.deleteById(id);
    }

    public Playlist updatePlaylist(Long id, Playlist playlist) {

        Playlist existingPlaylist = playlistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Playlist not found with id " + id));

        existingPlaylist.setName(playlist.getName());

        if (playlist.getUser() != null) {
            existingPlaylist.setUser(playlist.getUser());
        }

        if (playlist.getSongs() != null) {
            existingPlaylist.setSongs(playlist.getSongs());
        }
        return playlistRepository.save(existingPlaylist);
    }


    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }
}
