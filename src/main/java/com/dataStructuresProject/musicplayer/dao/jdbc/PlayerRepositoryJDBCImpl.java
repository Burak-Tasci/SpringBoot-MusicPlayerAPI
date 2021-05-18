package com.dataStructuresProject.musicplayer.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dataStructuresProject.musicplayer.dao.PlayerRepository;
import com.dataStructuresProject.musicplayer.exception.SongNotFoundException;
import com.dataStructuresProject.musicplayer.model.DoublyLinkedList;
import com.dataStructuresProject.musicplayer.model.DoublyLinkedList.Node;
import com.dataStructuresProject.musicplayer.model.Playlist;
import com.dataStructuresProject.musicplayer.model.Song;

@Repository
public class PlayerRepositoryJDBCImpl implements PlayerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Playlist> rowMapperPlaylist = new RowMapper<Playlist>() {
		
		@Override
		public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Playlist playlist = new Playlist();
			playlist.setId(rs.getLong("id"));
			playlist.setName(rs.getString("name"));
			return playlist;
		}
	};
	
	private RowMapper<Song> rowMapperSong = new RowMapper<Song>() {
		
		@Override
		public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
			Song song = new Song();
			song.setId(rs.getLong("id"));
			song.setGenre(rs.getString("genre"));
			song.setArtist_name(rs.getString("artist_name"));
			song.setTrack_name(rs.getString("track_name"));
			song.setDuration_ms(rs.getInt("duration_ms"));
			song.setPopularity(rs.getInt("popularity"));
			return song;
		}
	};
	
	
	// HERE
	@Override
	public Song previousSong(Long playlistId, Long songId) {
		
		String sql = "SELECT song_id FROM T_SONGPLAYLIST WHERE playlist_id = ?";
		List<Long> song_ids = jdbcTemplate.queryForList(sql,Long.class, playlistId);
		for(int i = 0 ; i<song_ids.size();i++) {
			if (song_ids.get(i) == songId) {
				
				sql = "SELECT * FROM T_SONGS WHERE id = ?";
				try {
					return DataAccessUtils.singleResult(jdbcTemplate.query(sql,rowMapperSong, song_ids.get(i-1)));
				}
				catch(IndexOutOfBoundsException ex) {
					return null;
				}
				
			}
		}
		throw new SongNotFoundException(String.format("No such a song with id = %d",songId));
	}

	@Override
	public Song nextSong(Long playlistId, Long songId) {
		String sql = "SELECT s.id, s.genre, s.artist_name, s.track_name, s.duration_ms, s.popularity "
				+ "FROM T_SONGPLAYLIST AS sp INNER JOIN T_SONGS AS s "
				+ "ON s.id = sp.song_id "
				+ "WHERE sp.playlist_id = ? ";
		List<Song> songs = jdbcTemplate.query(sql,rowMapperSong,playlistId);
		DoublyLinkedList doublyLinkedList = new DoublyLinkedList(songs);
		doublyLinkedList.display();
		return doublyLinkedList.nextSong(songId);
		
	}

	@Override
	public List<Song> playRandom(Long playlistId) {
		String sql = "SELECT s.id, s.genre, s.artist_name, s.track_name, s.duration_ms, s.popularity "
				+ "FROM T_SONGPLAYLIST AS sp INNER JOIN T_SONGS AS s "
				+ "ON s.id = sp.song_id "
				+ "WHERE sp.playlist_id = ? ";
		List<Song> songs = jdbcTemplate.query(sql,rowMapperSong,playlistId);
		Collections.shuffle(songs);
		return songs;
		
		

		
		
		
		
	}

}
