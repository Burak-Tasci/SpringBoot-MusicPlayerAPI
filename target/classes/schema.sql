create table IF NOT EXISTS public.t_playlists(

	id bigint not null auto_increment,
	name varchar(255)
);
create table public.t_songs(
	
	id bigint not null auto_increment,
	genre varchar(50),
	artist_name varchar(255),
	track_name varchar(255),
	duration_ms int,
	popularity int
);
create table IF NOT EXISTS public.t_SongPlaylist(
	song_id bigint not null,
	playlist_id bigint not null
);

alter table public.t_playlists add constraint public.constraint_1 primary key(id);

alter table public.t_songs add constraint public.constraint_2 primary key(id);

alter table public.t_SongPlaylist add constraint public.constraint_3 primary key(song_id, playlist_id);

create sequence public.musicplayer_sequence start with 100;




