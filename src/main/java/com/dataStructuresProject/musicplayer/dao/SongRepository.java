package com.dataStructuresProject.musicplayer.dao;

import java.util.List;

import com.dataStructuresProject.musicplayer.model.Song;

public interface SongRepository {

	List<Song> findSongs();
	List<Song> findByTrackName(String trackName);
	List<Song> findByArtist(String artist_name);
	List<Song> findByGenre(String genre);
	List<Song> findSongsByPlaylist(Long id);
	Song findSongById(Long id);
	
	
	
}
