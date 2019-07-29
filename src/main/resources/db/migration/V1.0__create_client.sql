CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE client
(
  uuid UUID NOT NULL DEFAULT uuid_generate_v1(),
  CONSTRAINT client_pkey PRIMARY KEY ( uuid ),
  name            VARCHAR(100)   NOT NULL,
  email           VARCHAR(100)   NOT NULL UNIQUE,
  birthdate       DATE
);

INSERT INTO client (name, email) VALUES ('Pedro Bacchini', 'pedrobacchini@outlook.com');
INSERT INTO client (name, email, birthdate) VALUES ('Maria Silva', 'mariasilva@outlook.com', '1990-08-01');