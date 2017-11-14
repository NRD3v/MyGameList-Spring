package com.nrd3v.mygamelist.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "giantbomb_id")
    private String developerGiantbombId;
    @Column(name = "name")
    private String developerName;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "game_developer", joinColumns = @JoinColumn(name = "developer_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> games;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "media_id")
    private Media media;

    public Developer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeveloperGiantbombId() {
        return developerGiantbombId;
    }

    public void setDeveloperGiantbombId(String developerGiantbombId) {
        this.developerGiantbombId = developerGiantbombId;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        if (games == null) {
            games = new ArrayList<>();
        }
        games.add(game);
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", developerGiantbombId='" + developerGiantbombId + '\'' +
                ", developerName='" + developerName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
