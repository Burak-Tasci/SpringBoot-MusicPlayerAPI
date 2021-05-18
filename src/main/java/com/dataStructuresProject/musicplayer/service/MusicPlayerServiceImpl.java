package com.dataStructuresProject.musicplayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataStructuresProject.musicplayer.dao.PlayerRepository;
import com.dataStructuresProject.musicplayer.dao.PlaylistRepository;
import com.dataStructuresProject.musicplayer.dao.SongRepository;
import com.dataStructuresProject.musicplayer.exception.PlaylistNotFoundException;
import com.dataStructuresProject.musicplayer.exception.SongNotFoundException;
import com.dataStructuresProject.musicplayer.model.Playlist;
import com.dataStructuresProject.musicplayer.model.Song;
import com.dataStructuresProject.musicplayer.model.SongPlaylistConnection;

@Service
public class MusicPlayerServiceImpl implements MusicPlayerService {

	private PlaylistRepository playlistRepository;
	private SongRepository songRepository;
	private PlayerRepository playerRepository;
	
	
	@Autowired
	public void setPlayerRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Autowired
	public void setPlaylistRepository(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
	
	@Autowired
	public void setSongRepository(SongRepository songRepository) {
		this.songRepository = songRepository;
	}

	@Override
	public void createPlaylist(String name) {
		playlistRepository.create(name);
		
	}

	@Override
	public void deletePlaylist(Long id) throws PlaylistNotFoundException {
		
		Playlist playlist = playlistRepository.findByID(id);
		if(playlist == null) {
			throw new PlaylistNotFoundException("There is no playlist with id "+id);
		}
		else {
			playlistRepository.delete(id);
		}
	}

	@Override
	public List<Playlist> findPlaylists() {
	
		return playlistRepository.findPlaylists();
	}

	@Override
	public Playlist findPlaylistByID(Long id) throws PlaylistNotFoundException {
		Playlist playlist = playlistRepository.findByID(id);
		if(playlist == null) throw new PlaylistNotFoundException("There is no playlist with id "+id);
		return playlist;
	}

	@Override
	public Playlist findPlaylistByName(String name) throws PlaylistNotFoundException {
		Playlist playlist = playlistRepository.findByName(name);
		if(playlist == null) throw new PlaylistNotFoundException("There is no playlist with name "+name);
		return playlist;
	}

	@Override
	public void updatePlaylist(Playlist playlist) {
		playlistRepository.update(playlist);
	}

	@Override
	public List<Song> findSongs() {
		return songRepository.findSongs();
	}

	@Override
	public List<Song> findSongByTrackName(String trackName) throws SongNotFoundException {
		
		List<Song> song = songRepository.findByTrackName(trackName);
		if(song == null) throw new SongNotFoundException("There is no song with name "+trackName);
		return song;
	}

	@Override
	public List<Song> findSongsByArtist(String artist_name) throws SongNotFoundException {
		
		List<Song> songs = songRepository.findByArtist(artist_name);
		if(songs == null) throw new SongNotFoundException("There is no songs for artist "+artist_name);
		return songs;
	}

	@Override
	public List<Song> findSongsByGenre(String genre) throws SongNotFoundException {
		List<Song> songs = songRepository.findByGenre(genre);
		if(songs == null) throw new SongNotFoundException("There is no songs for genre "+genre);
		return songs;
	}

	@Override
	public void addSong(SongPlaylistConnection songPlaylistConnection) {
		playlistRepository.addSong(songPlaylistConnection);
		
	}

	@Override
	public List<Song> findSongByPlaylist(Long id) {
		return songRepository.findSongsByPlaylist(id);
	}

	@Override
	public Song previousSong(Long playlistId, Long songId) {
		return playerRepository.previousSong(playlistId, songId);
	}

	@Override
	public Song nextSong(Long playlistId, Long songId) {
		return playerRepository.nextSong(playlistId, songId);
	}

	@Override
	public List<Song> playRandom(Long playlistID) {
		return playerRepository.playRandom(playlistID);
	}

	@Override
	public Song findSongsById(Long id) {
		return songRepository.findSongById(id);
	}

	
	
}
