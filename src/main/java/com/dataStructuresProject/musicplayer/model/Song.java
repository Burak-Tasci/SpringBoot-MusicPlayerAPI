package com.dataStructuresProject.musicplayer.model;

public class Song {
	
	//id,genre,artist_name,track_name,duration_ms,popularity
	private Long id;
	private String genre;
	private String artist_name;
	private String track_name;
	private int duration_ms;
	private int popularity;

	public Song() {
		
	}

	public Song(Long id, String genre,
			String artist_name, String track_name,
			int duration_ms, int popularity) {
		super();
		this.id = id;
		this.genre = genre;
		this.artist_name = artist_name;
		this.track_name = track_name;
		this.duration_ms = duration_ms;
		this.popularity = popularity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public String getTrack_name() {
		return track_name;
	}

	public void setTrack_name(String track_name) {
		this.track_name = track_name;
	}

	public int getDuration_ms() {
		return duration_ms;
	}

	public void setDuration_ms(int duration_ms) {
		this.duration_ms = duration_ms;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}



	@Override
	public String toString() {
		return "Song [id=" + id + ", genre=" + genre + ", artist_name=" + artist_name + ", track_name=" + track_name
				+ ", duration_ms=" + duration_ms + ", popularity=" + popularity + "]";
	}

    
	
	
	
	
}
