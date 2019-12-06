drop table if exists annuaire;
create table annuaire(
    num serial,
    nom varchar(50),
    prenom varchar(50),
    sexe varchar(10),
    tel varchar (50),
    fonction varchar (50),
    constraint pk_Annuaire primary key (num)
);
insert into annuaire values (1,'Delgrange','pierre','H','0123456789','etudiant');
