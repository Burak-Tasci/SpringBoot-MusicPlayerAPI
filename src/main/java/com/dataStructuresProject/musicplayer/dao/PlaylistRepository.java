package com.dataStructuresProject.musicplayer.dao;

import java.util.List;

import com.dataStructuresProject.musicplayer.model.Playlist;
import com.dataStructuresProject.musicplayer.model.SongPlaylistConnection;

public interface PlaylistRepository {

	void create(String name);
	void delete(Long id);
	List<Playlist> findPlaylists();
	Playlist findByID(Long id);
	Playlist findByName(String name);
	Playlist update(Playlist playlist);
	void addSong(SongPlaylistConnection songPlaylistConnection);
	void deleteSong(SongPlaylistConnection songPlaylistConnection);
	
}
