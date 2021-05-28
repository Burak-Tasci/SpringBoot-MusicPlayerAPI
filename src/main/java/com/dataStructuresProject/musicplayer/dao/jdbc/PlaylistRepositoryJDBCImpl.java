package com.dataStructuresProject.musicplayer.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dataStructuresProject.musicplayer.dao.PlaylistRepository;
import com.dataStructuresProject.musicplayer.model.Playlist;
import com.dataStructuresProject.musicplayer.model.SongPlaylistConnection;

@Repository
public class PlaylistRepositoryJDBCImpl implements PlaylistRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Playlist> rowMapper = new RowMapper<Playlist>() {
		
		@Override
		public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
			Playlist playlist = new Playlist();
			playlist.setId(rs.getLong("id"));
			playlist.setName(rs.getString("name"));
			return playlist;
		}
	};
	
	@Override
	public void create(String name) {
		String sql = "insert into t_playlists(name) values(?)";
		
		JSONObject object = new JSONObject(name);  
		jdbcTemplate.update(sql, object.getString("name"));
		

	}

	@Override
	public void delete(Long id) {
		String sql = "delete from t_playlists where id = ?";
		jdbcTemplate.update(sql, id);

	}

	@Override
	public List<Playlist> findPlaylists() {
		String sql = "select * from t_playlists";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Playlist findByID(Long id) {
		String sql = "select * from t_playlists where id = ?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper,id));
	}

	@Override
	public Playlist findByName(String name) {
		String sql = "select * from t_playlists where name = ?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper,name));
	}

	@Override
	public Playlist update(Playlist playlist) {
		String sql = "update  t_playlists set name="+playlist.getName()+" where id = "+playlist.getId();
		jdbcTemplate.update(sql);
		return playlist;
	}

	@Override
	public void addSong(SongPlaylistConnection songPlaylistConnection) {
		String sql = "insert into t_songplaylist(song_id, playlist_id) values(?,?)";
		jdbcTemplate.update(sql,songPlaylistConnection.getSong_id(),songPlaylistConnection.getPlaylist_id());
		
	}

	@Override
	public void deleteSong(SongPlaylistConnection songPlaylistConnection) {
		String sql = "delete from t_SongPlaylist where song_id =  ? and playlist_id = ?";
		jdbcTemplate.update(sql,songPlaylistConnection.getSong_id(),songPlaylistConnection.getPlaylist_id());
		
	}
}
