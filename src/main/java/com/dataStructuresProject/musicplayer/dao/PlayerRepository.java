package com.dataStructuresProject.musicplayer.dao;

import java.util.List;

import com.dataStructuresProject.musicplayer.model.Song;

public interface PlayerRepository {
	
	Song previousSong(Long playlistId, Long songId);
	Song nextSong(Long playlistId, Long songId);
	List<Song> playRandom(Long playlistId);
	

}
