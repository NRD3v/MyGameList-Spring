package com.nrd3v.mygamelist.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "giantbomb_id")
    private String platformGiantbombId;
    @Column(name = "name")
    private String platformName;
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "media_id")
    private Media media;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<GameRelease> gameReleases;

    public Platform() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatformGiantbombId() {
        return platformGiantbombId;
    }

    public void setPlatformGiantbombId(String platformGiantbombId) {
        this.platformGiantbombId = platformGiantbombId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<GameRelease> getGameReleases() {
        return gameReleases;
    }

    public void setGameReleases(List<GameRelease> gameReleases) {
        this.gameReleases = gameReleases;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", platformGiantbombId='" + platformGiantbombId + '\'' +
                ", platformName='" + platformName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
