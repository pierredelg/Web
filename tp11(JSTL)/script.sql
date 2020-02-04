drop table if exists personne;
create table personne(
    id serial,
    nom varchar(50),
    login varchar(50),
    mdp varchar(50),
    constraint pk_Personne primary key (id)
);
insert into personne values (1,'Pierre','pierre','pierre');

