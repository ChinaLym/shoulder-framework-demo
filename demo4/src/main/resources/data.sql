INSERT INTO user_info (id, username, password, name, age, email)
VALUES (1, 'shoulder', '$2a$10$tNmATC/VgsmFXEgCm2vIX.ndFlVGS/VKff.4L.9UGkW/JegEC4NXu', 'Shoulder', 8, 'shoulder@shoulder.com');

INSERT INTO user_info (id, username, password, name, age, email)
VALUES (2, 'jack', '$2a$10$krRf9r03W2hbyDeX64f5Nud0zyjf5Q7af3yTRPQU6aF98l/T5A6Zi', 'Jack', 20, 'jack@shoulder.com');

INSERT INTO user_info (id, username, password, name, age, email)
VALUES (3, 'tom', '$2a$10$zMbaVJ3qfEZAPenBgjTg/u2zfw96Bb3iLbJclJ63cs9EFA95Q1Eta', 'Tom', 28, 'tom@shoulder.com');

---

INSERT INTO crypto_info (app_id, header, data_key, root_key_part, vector, create_time)
VALUES ('demo2', '${a8} ', 'aXb7kq0YjumXXdVpKETyjf3CJg+ejSdAgkJR7bIq0FSLp4aa4PTwiakootcdFnZH',
        'Ia8SVsjnWTqfAh+cwn1Ks0fc3DUeH48lh2cPs/aCotK0gLTr4T/OW8ZQgpJu4tEEhRXpY0blhgaCgwfVHOWcSwMYbG1lKsXJLzTZFFNorKX8+T+t5kJWbQnw5VgAzSkBN8GaT0PtQCkNGvfSYqZkCePZk/JZLSwOa85wtYVVB0LWKncscY+KdGzuESvcLUdCEBoT1RgdKLvicECvwSPWFmrUlxTl1yQGuaM2J70UNEdh9j77Bi9D8hAu1CsBkqFTLtneBa4ILoeSi/kBZ/GnMQukv5ulqopVAsYh2lA6ubeLSt72GlJy4Q==',
        '8o2Ohcqn5yk1ISQcNjw1Xg==', '2021-05-15 19:11:38');

----

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, active, create_time) VALUES ('demo-client-id', null, 'secret', 'message.read,message.write,message:read,message:write,user:read', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://demo.com/login/oauth2/code/demo,http://localhost/login/oauth2/code/demo,http://127.0.0.1/login/oauth2/code/demo', null, 36000, 7200000, null, 'false', 1, null);
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, active, create_time) VALUES ('shoulder', null, 'shoulder', 'all', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://demo.com/login/oauth2/code/demo,http://localhost/login/oauth2/code/demo,http://127.0.0.1/login/oauth2/code/demo', null, 36000, 7200000, null, 'false', 1, null);
