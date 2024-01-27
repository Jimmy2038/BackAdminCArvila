create database vente_voiture;
\c vente_voiture;

create table marque(
    id_marque serial primary key,
    nom_marque text 
);

create table model(
    id_model serial primary key,
    nom_model text, 
    marque_id int,
    foreign key (marque_id) references marque(id_marque)
);

create table energie(
    id_energie serial primary key,
    nom_energie text
);

create table transmission(
    id_transmission serial primary key,
    nom_transmission text
);

create table utilisateur(
    id_utilisateur serial primary key,
    email text,
    mdp text,
    pseudo text
);

create table annonce(
    id_annonce serial primary key,
    id_utilisateur int,
    daty timestamp,
    id_model int,
    id_energie int,
    id_transmission int,
    kilometrage int,
    annee int,
    descri text,
    prix double precision,
    etat int,
    date_validation timestamp,
    date_vente timestamp,
    foreign key (id_utilisateur) references utilisateur(id_utilisateur),
    foreign key (id_model) references model(id_model),
    foreign key (id_energie) references energie(id_energie),
    foreign key (id_transmission) references transmission(id_transmission)
);

create table favoris(
    id serial primary key,
    id_utilisateur int,
    id_annonce int,
    foreign key (id_utilisateur) references utilisateur(id_utilisateur),
    foreign key (id_annonce) references annonce(id_annonce)
);

CREATE TABLE sary(
     id_sary serial PRIMARY KEY,
     id_annonce int,
     nom text,
     taille int,
     type VARCHAR(20),
     bin text,
     FOREIGN KEY (id_annonce) REFERENCES annonce (id_annonce)
);

create or replace view v_model as select m.*,ma.nom_marque
    from model m
        join marque ma on ma.id_marque=m.marque_id;

create or replace view v_annonce as select a.*,e.nom_energie,t.nom_transmission,u.pseudo,m.marque_id,m.nom_marque,m.nom_model
    from annonce a
        join v_model m on m.id_model=a.id_model
        join energie e on e.id_energie=a.id_energie
        join transmission t on t.id_transmission=a.id_transmission
        join utilisateur u on u.id_utilisateur=a.id_utilisateur;

create or replace view v_nb_vente_par_marque as select marque_id,nom_marque,count(nom_marque) as nb,sum(prix) as prix from v_annonce where etat=10 group by marque_id,nom_marque;

create or replace view v_nb_vendu as select count(etat),etat from annonce where etat!=0 group by etat order by etat;

select * from annonce where etat=5;


INSERT INTO MARQUE (nom_marque) VALUES ('Alpha Romeo'),('Mercedes-Benz');

INSERT INTO model (nom_model,marque_id) VALUES ('Model 1',1),('Model 2',1),('Model 3',2);

INSERT INTO energie (nom_energie) VALUES ('Gasoil'),('Essence'),('Electrique');

INSERT INTO transmission (nom_transmission) VALUES ('Automatique'),('Manuel'),('Seqentiel');


SELECT date_trunc('month', date_vente) AS month_start, COUNT(id_annonce)
FROM annonce
where etat=10
GROUP BY month_start
ORDER BY month_start;


