---require
create table if not exists oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);


 
---require
create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token  BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication  BYTEA,
  refresh_token VARCHAR(255)
);

---require
create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token  BYTEA,
  authentication  BYTEA
);

 
---CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS roles (
  id            bigserial NOT NULL PRIMARY KEY,
  role_name     varchar(255) DEFAULT NULL
) WITH (OIDS=FALSE);

CREATE TABLE IF NOT EXISTS users (
  id           bigserial NOT NULL PRIMARY KEY,
  name	   varchar(255) NOT NULL,
  email        varchar(255) NOT NULL,
  password     varchar(255) NOT NULL,
  phone character varying(255),
  profile bytea,
  birthdate character varying(255),
  gender character varying(255) DEFAULT 'NA'
) WITH (OIDS=FALSE);

CREATE TABLE IF NOT EXISTS users_roles (
  user_id       bigserial NOT NULL,
  role_id       bigserial NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES roles (id)
) WITH (OIDS=FALSE);

