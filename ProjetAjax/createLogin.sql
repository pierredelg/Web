drop table if exists personne;
create table personne(id serial,login varchar(50), nom varchar(50), mdp varchar(50), dat timestamp);
insert into personne values (1,'test','test','test','01/01/1901');
