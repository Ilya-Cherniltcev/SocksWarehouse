-- liquibase formatted sql

-- changeset cherniltcev:1

CREATE TABLE IF NOT EXISTS socks (
    id          BIGSERIAL PRIMARY KEY,
    color       VARCHAR(30) NOT NULL,
    cotton_part  INT NOT NULL CHECK (cotton_part >= 0 and cotton_part <=100),
    quantity    INT NOT NULL CHECK (quantity > 0)
);
