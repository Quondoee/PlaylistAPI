package com.music.service;

import com.music.model.Playlist;
import com.music.model.Song;
import com.music.repository.SongRepository;
import com.music.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistService playlistService;

    public Song createSong(Long playlistID, Song song) {

        Playlist playlist = playlistService.getPlaylistById(playlistID);
        if (playlist == null) {
            throw new ResourceNotFoundException("Playlist with id " + playlistID + " not found.");
        }
        playlist.addSong(song);

        playlistService.save(playlist);

        return song;
    }

    public Song getSongById(Long id) throws ResourceNotFoundException {
        return songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found with id " + id));
    }

    public List<Song> searchSongsbytitle(String title) {
        return songRepository.findByTitle(title);
    }
    public List<Song> searchSongsbyartist(String artist) {
        return songRepository.findByArtist(artist);
    }

    public Iterable<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public void deleteSong(Long id) throws ResourceNotFoundException {
        if (!songRepository.existsById(id)) {
            throw new ResourceNotFoundException("Song not found with id " + id);
        }
        songRepository.deleteById(id);
    }

    public Song updateSong(Long id, Song song) {

        Song existingSong = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found with id " + id));

        existingSong.setTitle(song.getTitle());
        existingSong.setArtist(song.getArtist());

        return songRepository.save(existingSong);
    }

}
