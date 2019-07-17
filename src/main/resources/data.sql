INSERT INTO tm_roles(id,name) VALUES(1,'standard');
INSERT INTO tm_roles(id,name) VALUES(2,'admin');

INSERT INTO tm_users(id,name,password) VALUES (1,'user1','$2a$04$GJ85Ihcglhbqac2zc3z3A.C3v55FMmN8.qGQ8FlNBCuyLtQ5/TyMO');
INSERT INTO tm_users(id,name,password) VALUES(2,'Felipe','$2a$11$PPiRVXaLAl0GPh7VhQZ53uhvRvK8Ei9DoFmvAxa5mhL8p.yLxJQeW');
INSERT INTO tm_users(id,name,password) VALUES(3,'Roberto','$2a$11$PPiRVXaLAl0GPh7VhQZ53uhvRvK8Ei9DoFmvAxa5mhL8p.yLxJQeW');
INSERT INTO tm_users(id,name,password) VALUES(4,'Maria' ,'$2a$11$PPiRVXaLAl0GPh7VhQZ53uhvRvK8Ei9DoFmvAxa5mhL8p.yLxJQeW');
INSERT INTO tm_users(id,name,password) VALUES(5,'Rebeca','$2a$11$PPiRVXaLAl0GPh7VhQZ53uhvRvK8Ei9DoFmvAxa5mhL8p.yLxJQeW');

INSERT INTO user_roles (user_id, role_id) VALUES(1,1);
INSERT INTO user_roles (user_id, role_id) VALUES(1,2);
INSERT INTO user_roles (user_id, role_id) VALUES(2,1);
INSERT INTO user_roles (user_id, role_id) VALUES(3,1);
INSERT INTO user_roles (user_id, role_id) VALUES(4,1);
INSERT INTO user_roles (user_id, role_id) VALUES(5,2);