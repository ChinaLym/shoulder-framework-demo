INSERT INTO user_info (id, name, sex, age, birth, level, id_card, real_name, initials, spellings, phone_num, email, status, group_auth, group_id, group_name, group_path, creator, create_time, update_time, description) VALUES (1, 'shoulder', 0, 21, '2020-01-01', 0, null, null, null, null, null, 'shoulder@shoulder.com', 0, null, null, '', null, 1, '2021-05-15 19:29:06', '2021-05-16 23:43:42', null) ON DUPLICATE KEY UPDATE name = VALUES(name), sex = VALUES(sex), age = VALUES(age), birth = VALUES(birth), level = VALUES(level), id_card = VALUES(id_card), real_name = VALUES(real_name), initials = VALUES(initials), spellings = VALUES(spellings), phone_num = VALUES(phone_num), email = VALUES(email), status = VALUES(status), group_auth = VALUES(group_auth), group_id = VALUES(group_id), group_name = VALUES(group_name), group_path = VALUES(group_path), creator = VALUES(creator), update_time = VALUES(update_time), description = VALUES(description);
INSERT INTO user_info (id, name, sex, age, birth, level, id_card, real_name, initials, spellings, phone_num, email, status, group_auth, group_id, group_name, group_path, creator, create_time, update_time, description) VALUES (2, 'jack', 0, 22, '2020-01-01', 0, null, null, null, null, null, 'jack@shoulder.com', 0, null, null, '', null, 1, '2021-05-15 19:29:06', '2021-05-15 19:29:06', null) ON DUPLICATE KEY UPDATE name = VALUES(name), sex = VALUES(sex), age = VALUES(age), birth = VALUES(birth), level = VALUES(level), id_card = VALUES(id_card), real_name = VALUES(real_name), initials = VALUES(initials), spellings = VALUES(spellings), phone_num = VALUES(phone_num), email = VALUES(email), status = VALUES(status), group_auth = VALUES(group_auth), group_id = VALUES(group_id), group_name = VALUES(group_name), group_path = VALUES(group_path), creator = VALUES(creator), update_time = VALUES(update_time), description = VALUES(description);
INSERT INTO user_info (id, name, sex, age, birth, level, id_card, real_name, initials, spellings, phone_num, email, status, group_auth, group_id, group_name, group_path, creator, create_time, update_time, description) VALUES (3, 'tom', 0, 31, '2020-01-01', 0, null, null, null, null, null, 'tom@shoulder.com', 0, null, null, '', null, 1, '2021-05-15 19:29:06', '2021-05-15 19:29:06', null) ON DUPLICATE KEY UPDATE name = VALUES(name), sex = VALUES(sex), age = VALUES(age), birth = VALUES(birth), level = VALUES(level), id_card = VALUES(id_card), real_name = VALUES(real_name), initials = VALUES(initials), spellings = VALUES(spellings), phone_num = VALUES(phone_num), email = VALUES(email), status = VALUES(status), group_auth = VALUES(group_auth), group_id = VALUES(group_id), group_name = VALUES(group_name), group_path = VALUES(group_path), creator = VALUES(creator), update_time = VALUES(update_time), description = VALUES(description);
INSERT INTO user_info (id, name, sex, age, birth, level, id_card, real_name, initials, spellings, phone_num, email, status, group_auth, group_id, group_name, group_path, creator, create_time, update_time, description) VALUES (4, 'sandy', 0, 33, '2020-01-01', 0, null, null, null, null, null, 'sandy@shoulder.com', 0, null, null, '', null, 1, '2021-05-15 19:29:06', '2021-05-15 19:29:06', null) ON DUPLICATE KEY UPDATE name = VALUES(name), sex = VALUES(sex), age = VALUES(age), birth = VALUES(birth), level = VALUES(level), id_card = VALUES(id_card), real_name = VALUES(real_name), initials = VALUES(initials), spellings = VALUES(spellings), phone_num = VALUES(phone_num), email = VALUES(email), status = VALUES(status), group_auth = VALUES(group_auth), group_id = VALUES(group_id), group_name = VALUES(group_name), group_path = VALUES(group_path), creator = VALUES(creator), update_time = VALUES(update_time), description = VALUES(description);
