INSERT INTO roles (name, version) values ('ADMIN', 0);
INSERT INTO roles (name, version) values ('CUSTOMER', 0);

INSERT INTO users (first_name, last_name, email, version) values ('Super', 'Admin', 'admin@gmail.com', 0);
INSERT INTO users (first_name, last_name, email, version) values ('Mr.', 'Tom', 'tom@gmail.com', 0);
INSERT INTO users (first_name, last_name, email, version) values ('Mr.', 'Jerry', 'jerry@gmail.com', 0);

INSERT INTO user_roles (user_id, role_id, version) values (1, 1, 0);
INSERT INTO user_roles (user_id, role_id, version) values (2, 2, 0);
INSERT INTO user_roles (user_id, role_id, version) values (3, 2, 0);

INSERT INTO discount_deals (description, version) values ('Buy 1 get 20% off the second)', 0);
INSERT INTO discount_deals (description, version) values ('Buy 1 get 30% off the second)', 0);
INSERT INTO discount_deals (description, version) values ('Buy 1 get 50% off the second)', 0);