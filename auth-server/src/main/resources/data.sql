INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
	('client', '$2a$10$TRu6LVxcA7CTv7/6L.u2JOowWyLnfwCNxzYt5Iq.2axhJFXhCkB4S', 'trust,read,write','password,authorization_code,refresh_token', null, null, 86400, 172800, null, true);

INSERT INTO roles (id, role_name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, role_name) VALUES (2, 'USER');
 
INSERT INTO users (id,name,email,password) VALUES (1,'admin', 'admin@admin.com', '$2a$10$QLKv79DuyBP5YAoJghSwC.LqD3RUZ2uPMe8J0eZMIRauhAaSxZVZG');
INSERT INTO users (id,name,email,password) VALUES (2, 'User', 'user@admin.com', '$2a$10$QLKv79DuyBP5YAoJghSwC.LqD3RUZ2uPMe8J0eZMIRauhAaSxZVZG');


INSERT INTO users_roles(user_id, role_id) VALUES(1,1);
INSERT INTO users_roles(user_id, role_id) VALUES(2,2);
 
