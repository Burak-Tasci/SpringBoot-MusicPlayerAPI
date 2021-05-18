package com.dataStructuresProject.musicplayer.model;

public class SongPlaylistConnection {
	
	private Long song_id;
	private Long playlist_id;
	
	
	public Long getSong_id() {
		return song_id;
	}
	public void setSong_id(Long song_id) {
		this.song_id = song_id;
	}
	public Long getPlaylist_id() {
		return playlist_id;
	}
	public void setPlaylist_id(Long playlist_id) {
		this.playlist_id = playlist_id;
	}
	@Override
	public String toString() {
		return "SongPlaylistConnection [song_id=" + song_id + ", playlist_id=" + playlist_id + "]";
	}
	
	
	
	
}
