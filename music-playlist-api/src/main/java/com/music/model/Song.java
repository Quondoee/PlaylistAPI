package com.music.model;


import jakarta.persistence.*;

@Entity
public class Song {

    @ManyToOne
    private Playlist playlist;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}