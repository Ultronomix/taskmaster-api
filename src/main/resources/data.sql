INSERT INTO user_roles VALUES ('62612f5fd0da465b9c6d0111472bdfd6', 'DIRECTOR');
INSERT INTO user_roles VALUES ('2d3f2dab9fae46aba798e968098050a6', 'MANAGER');
INSERT INTO user_roles VALUES ('98e78bd57dcf475d901f4b4072651999', 'LEAD');
INSERT INTO user_roles VALUES ('4b4121d715524cd0995dfa8e5618e154', 'SENIOR');
INSERT INTO user_roles VALUES ('5a2e0415ee08440fab8a778b37ff6874', 'JUNIOR');
INSERT INTO user_roles VALUES ('c2dfbb5fb4b3485284db146984bc6a0b', 'QA');

INSERT INTO app_users (id, username, password, given_name, surname, email, role_id)
VALUES ('0cf6b1e0-a092-49df-b553-aebcf70d40b4', 'someone', 'p4$$w0RD', 'Some', 'One', 'someone@revature.com', '5a2e0415ee08440fab8a778b37ff6874');