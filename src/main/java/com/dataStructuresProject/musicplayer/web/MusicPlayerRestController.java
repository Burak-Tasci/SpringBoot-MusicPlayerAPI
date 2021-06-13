package com.dataStructuresProject.musicplayer.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dataStructuresProject.musicplayer.exception.InternalServerException;
import com.dataStructuresProject.musicplayer.exception.PlaylistNotFoundException;
import com.dataStructuresProject.musicplayer.model.Playlist;
import com.dataStructuresProject.musicplayer.model.Song;
import com.dataStructuresProject.musicplayer.model.SongPlaylistConnection;
import com.dataStructuresProject.musicplayer.service.MusicPlayerService;



@RestController
@RequestMapping("/api")
public class MusicPlayerRestController {
	
	@Autowired
	private MusicPlayerService musicPlayerService;
	
	@RequestMapping(method=RequestMethod.POST, value="/newPlaylist")
	public ResponseEntity createPlaylist(@RequestBody String name){
		
		System.out.println(name);
		musicPlayerService.createPlaylist(name);
		return ResponseEntity.ok().build();
		
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/playlist/{id}")
	public ResponseEntity<Playlist> deletePlaylist(@PathVariable Long id) {
		
		try {
			
			musicPlayerService.deletePlaylist(id);
			return ResponseEntity.ok().build();
		}
		catch(PlaylistNotFoundException ex) {
			throw ex;
		}
		catch(Exception ex) {
			throw new InternalServerException(ex);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/playlists")
	public ResponseEntity<List<Playlist>> findPlaylists(){
		
		List<Playlist> playlists = musicPlayerService.findPlaylists();
		return ResponseEntity.ok(playlists);
		
	}
	@RequestMapping(method=RequestMethod.GET, value="/playlist/{id}")
	public ResponseEntity<Playlist> findPlaylistByID(@PathVariable Long id){
		
		Playlist playlist = musicPlayerService.findPlaylistByID(id);
		return ResponseEntity.ok(playlist);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/playlist/name={name}")
	public ResponseEntity<Playlist> findPlaylistByName(@PathVariable String name) {
		
		try {
			
			musicPlayerService.findPlaylistByName(name);
			return ResponseEntity.ok().build();
		}
		catch(PlaylistNotFoundException ex) {
			throw ex;
		}
		catch(Exception ex) {
			throw new InternalServerException(ex);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/playlist/{id}")
	public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") Long id,
			@RequestBody Playlist playlistRequest){
		
		try {
			Playlist playlist = musicPlayerService.findPlaylistByID(id);
			playlist.setName(playlistRequest.getName());
			musicPlayerService.updatePlaylist(playlistRequest);
			return ResponseEntity.ok().build();

		}
		catch(PlaylistNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(500).build();
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/songs")
	public ResponseEntity<List<Song>> findSongs(){
		
		List<Song> songs = musicPlayerService.findSongs();
		return ResponseEntity.ok(songs);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/song/track={track_name}")
	public ResponseEntity<List<Song>> findSongByTrackName(@PathVariable("track_name") String track_name){
		
		List<Song> song = musicPlayerService.findSongByTrackName(track_name);
		return ResponseEntity.ok(song);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/song/artist={artist_name}")
	public ResponseEntity<List<Song>> findSongsByArtist (@PathVariable("artist_name") String artist_name){
		
		List<Song> song = musicPlayerService.findSongsByArtist(artist_name);
		return ResponseEntity.ok(song);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/song/genre={genre}")
	public ResponseEntity<List<Song>> findSongsByGenre (@PathVariable("genre") String genre){
		
		List<Song> songs = musicPlayerService.findSongsByGenre(genre);
		return ResponseEntity.ok(songs);
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/addSong")
	public ResponseEntity addSong(@RequestBody SongPlaylistConnection songPlaylistConnection) {
		
		musicPlayerService.addSong(songPlaylistConnection);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/SongsOfPlaylist/{id}")
	public ResponseEntity<List<Song>> findSongByPlaylist(@PathVariable("id") Long id){
		List<Song> songs = musicPlayerService.findSongByPlaylist(id);
		return ResponseEntity.ok(songs);
	}

	
	@RequestMapping(method=RequestMethod.GET, value ="/previousSong/{playlistId}-{songId}")
	public ResponseEntity<Song> previousSong(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") Long songId){
		Song song = musicPlayerService.previousSong(playlistId, songId);
		return ResponseEntity.ok(song);
	}
	@RequestMapping(method=RequestMethod.GET, value ="/nextSong/{playlistId}-{songId}")
	public ResponseEntity<Song> nextSong(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") Long songId){
		Song song = musicPlayerService.nextSong(playlistId, songId);
		return ResponseEntity.ok(song);
	}
	@RequestMapping(method=RequestMethod.GET, value ="/playRandom/{playlistId}")
	public ResponseEntity<List<Song>> playRandom(@PathVariable Long playlistId){
		List<Song> songs = musicPlayerService.playRandom(playlistId);
		return ResponseEntity.ok(songs);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/deleteSong")
	public ResponseEntity deleteSong(@RequestBody SongPlaylistConnection songPlaylistConnection) {

		System.out.println(songPlaylistConnection.toString());
		musicPlayerService.deleteSong(songPlaylistConnection);
		return ResponseEntity.ok().build();
		
	}
	
	
	
}
