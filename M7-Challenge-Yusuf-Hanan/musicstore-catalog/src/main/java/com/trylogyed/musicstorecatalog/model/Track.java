package com.trylogyed.musicstorecatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "track")
public class Track {

    @Id
    @Column(name = "track_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackId;

    @Column(name = "album_id")
    private Integer albumId;

    private String title;

    @Column(name = "run_time")
    private Integer runtime;

    public Track(Integer trackId, Integer albumId, String title, Integer runtime) {
        this.trackId = trackId;
        this.albumId = albumId;
        this.title = title;
        this.runtime = runtime;
    }

    public Track(Integer albumId, String title, Integer runtime) {
        this.albumId = albumId;
        this.title = title;
        this.runtime = runtime;
    }

    public Track() {
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(trackId, track.trackId) && Objects.equals(albumId, track.albumId) && Objects.equals(title, track.title) && Objects.equals(runtime, track.runtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackId, albumId, title, runtime);
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", runtime=" + runtime +
                '}';
    }
}
