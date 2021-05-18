package com.dataStructuresProject.musicplayer.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dataStructuresProject.musicplayer.dao.SongRepository;
import com.dataStructuresProject.musicplayer.model.Song;

@Repository
public class SongRepositoryJDBCImpl implements SongRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Song> rowMapper = new RowMapper<Song>() {
		
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
	
	@Override
	public List<Song> findSongs() {
		
		String sql = "select * from t_songs";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Song> findByTrackName(String trackName) {
		
		String sql = "select * from t_songs where track_name like ?";
		return jdbcTemplate.query(sql, rowMapper,"%"+trackName+"%");
	}

	@Override
	public List<Song> findByArtist(String artist_name) {
		
		String sql = "select * from t_songs where artist_name like ?";
		return jdbcTemplate.query(sql, rowMapper,"%"+artist_name+"%");
	}

	@Override
	public List<Song> findByGenre(String genre) {
		String sql = "select * from t_songs where genre = ?";
		return jdbcTemplate.query(sql, rowMapper,genre);
	}

	@Override
	public List<Song> findSongsByPlaylist(Long id) {
		String sql = "SELECT S.ID,S.GENRE, S.ARTIST_NAME, S.TRACK_NAME, S.DURATION_MS, S.POPULARITY FROM T_SONGS S INNER JOIN T_SONGPLAYLIST C ON  "
				+ "S.ID = C.SONG_ID WHERE PLAYLIST_ID = "+id;
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Song findSongById(Long id) {
		String sql = "SELECT * FROM T_SONGS WHERE id = ?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql,rowMapper, id));
	}

}
