drop table if exists login;
create table login(nom varchar(50), mdp varchar(50), dat timestamp, ip varchar(50));
insert into login values ('test','test','01/01/1901','127.0.0.1');