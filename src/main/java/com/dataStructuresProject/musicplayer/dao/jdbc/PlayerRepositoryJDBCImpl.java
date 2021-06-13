package com.dataStructuresProject.musicplayer.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dataStructuresProject.musicplayer.dao.PlayerRepository;
import com.dataStructuresProject.musicplayer.model.DoublyLinkedList;
import com.dataStructuresProject.musicplayer.model.Randomizing;
import com.dataStructuresProject.musicplayer.model.Song;

@Repository
public class PlayerRepositoryJDBCImpl implements PlayerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
		
		String sql = "SELECT s.id, s.genre, s.artist_name, s.track_name, s.duration_ms, s.popularity "
				+ "FROM T_SONGPLAYLIST AS sp INNER JOIN T_SONGS AS s "
				+ "ON s.id = sp.song_id "
				+ "WHERE sp.playlist_id = ? ";

		List<Song> songs = jdbcTemplate.query(sql,rowMapperSong,playlistId);
		
		DoublyLinkedList d = new DoublyLinkedList(songs);
		return (d.previousSong(songId) != null) ? d.previousSong(songId) : null;
	}

	@Override
	public Song nextSong(Long playlistId, Long songId) {
		String sql = "SELECT s.id, s.genre, s.artist_name, s.track_name, s.duration_ms, s.popularity "
				+ "FROM T_SONGPLAYLIST AS sp INNER JOIN T_SONGS AS s "
				+ "ON s.id = sp.song_id "
				+ "WHERE sp.playlist_id = ? ";

		List<Song> songs = jdbcTemplate.query(sql,rowMapperSong,playlistId);
		
		DoublyLinkedList d = new DoublyLinkedList(songs);
		return (d.nextSong(songId) != null) ? d.nextSong(songId) : null;
		
	}

	@Override
	public List<Song> playRandom(Long playlistId) {
		String sql = "SELECT s.id, s.genre, s.artist_name, s.track_name, s.duration_ms, s.popularity "
				+ "FROM T_SONGPLAYLIST AS sp INNER JOIN T_SONGS AS s "
				+ "ON s.id = sp.song_id "
				+ "WHERE sp.playlist_id = ? ";
		List<Song> songs = jdbcTemplate.query(sql,rowMapperSong,playlistId);
		
		Randomizing r = new Randomizing(songs);
		
		return r.randomize(songs.size());
		

	}

	
public int gettingIt(List<Song> songs, Long songId) {
		
		DoublyLinkedList d = new DoublyLinkedList(songs);
		d.display();
		int index = -1;
		for(int i = 0; i<songs.size();i++)
		{
			if(songId.toString().equals(d.takeNodebyIndex(i).getdata().getId().toString())) {
				System.out.println(d.takeNodebyIndex(i).getdata().getId());
				index = i+1;
			}
		}
			
		return index;
	}
}
