package com.dataStructuresProject.musicplayer.service;

import java.util.List;

import com.dataStructuresProject.musicplayer.exception.PlaylistNotFoundException;
import com.dataStructuresProject.musicplayer.exception.SongNotFoundException;
import com.dataStructuresProject.musicplayer.model.Playlist;
import com.dataStructuresProject.musicplayer.model.Song;
import com.dataStructuresProject.musicplayer.model.SongPlaylistConnection;



public interface MusicPlayerService {
	
	void createPlaylist(String name);
	void deletePlaylist(Long id) throws PlaylistNotFoundException;
	List<Playlist> findPlaylists();
	Playlist findPlaylistByID(Long id) throws PlaylistNotFoundException;
	Playlist findPlaylistByName(String name) throws PlaylistNotFoundException;
	void updatePlaylist(Playlist playlist);
	void addSong(SongPlaylistConnection songPlaylistConnection);
	void deleteSong(SongPlaylistConnection songPlaylistConnection);
	
	List<Song> findSongs();
	List<Song> findSongByTrackName(String trackName) throws SongNotFoundException;
	List<Song> findSongsByArtist(String artist_name) throws SongNotFoundException;
	List<Song> findSongsByGenre(String genre) throws SongNotFoundException;
	List<Song> findSongByPlaylist(Long id);
	Song findSongsById(Long id);
	
	Song previousSong(Long playlistId, Long songId);
	Song nextSong(Long playlistId, Long songId);
	List<Song> playRandom(Long playlistId);
}
