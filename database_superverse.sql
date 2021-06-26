CREATE DATABASE superverse;

USE superverse;

CREATE TABLE battles(
    id_battle INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_winner INT(11) NOT NULL,
    id_loser INT(11) NOT NULL,
    id_chosen INT(11) NOT NULL,
    skill VARCHAR(255) NOT NULL,
    google_uid_user VARCHAR(255) NOT NULL,
    battle_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);