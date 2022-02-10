drop database if exists simpleMedia;
create database simpleMedia;
use simpleMedia;

create table g_geschlecht(
	g_id int primary key,
    g_kuerzel varchar(20),
    g_bez varchar(20),
	g_anrede varchar(20)
);

create table r_rolle(
	r_Nr int primary key,
    r_Rolle varchar(35),
    r_Beschreibung Text
);

create table u_user(
	u_id int primary key auto_increment,
	u_nutzername varchar(35),
    u_passwort varchar(35),
    u_mail varchar(320),
    u_g_geschlecht int,
    u_r_rolle int,
    u_profilbild Text,
	u_erstanmeldung Date,
    u_letztanmeldung Date,
    u_aktiv boolean default false,
    foreign key (u_r_rolle) references r_rolle(r_Nr),
	foreign key (u_g_geschlecht) references g_geschlecht(g_id)
);

create table b_beitrag(
	b_id int primary key auto_increment,
	b_u_id int,
    b_ueberschrift varchar(50),
    b_text Text,
    b_img Text,
    b_erstellungsdatum Date,
    b_likes int,
    
    foreign key (b_u_id) references u_user(u_id)
);

create table a_authentificationrequest(
	a_id int primary key auto_increment,
	a_key int,
    a_u_id int,
    
    foreign key (a_u_id) references u_user(u_id)
);

create table l_liked(
	l_id int primary key auto_increment,
    l_u_id int,
    l_b_id int,
    foreign key (l_b_id) references b_beitrag(b_id),
    foreign key (l_u_id) references u_user(u_id)
);

create table shownBeitraege(
	s_b_id int , 
    s_u_user int,
    primary key(s_b_id, s_u_user),
    foreign key (s_b_id) references b_beitrag(b_id),
    foreign key (s_u_user) references u_user(u_id)
);

 insert into g_geschlecht
 value(0,'m','Mänlich','herr')
 ,(1,'w','Weiblich','frau')
 ,(2,'nicht definiert','nicht definiert','nicht definiert');
 
 insert into r_rolle
 value(0,'User','normaler eingeloggter Nutzer kann Posts liken und erstellen')
 ,(1,'Admin','Nutzer mit allen rechten kann alles was ein User kann nur für alle');
 
 insert into shownbeitraege() values(1,1);
 
select * from b_beitrag as b2 Where (select (s_u_user is  null) from shownbeitraege inner join u_user on shownbeitraege.s_u_user = u_user.u_id Where s_u_user = 2 and s_b_id = b2.b_id) Order by rand();
;














