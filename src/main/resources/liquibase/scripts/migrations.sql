-- liquibase formatted sql

-- changeset cherniltcev:1

CREATE TABLE IF NOT EXISTS Socks (
    id          BIGINT PRIMARY KEY,
    color       VARCHAR(30) UNIQUE NOT NULL,
    cottonPart  INT NOT NULL,
    quantity    INT NOT NULL
);
