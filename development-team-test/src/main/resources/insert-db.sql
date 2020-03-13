set foreign_key_checks=0;


INSERT INTO projects
VALUES (1, 'Create a web application based on SpringJDBC', '2019-07-15');

INSERT INTO projects
VALUES (2, 'Create a web application based on SpringBoot','2019-08-13');

INSERT INTO projects
VALUES (3, 'Create a web application based on Hibernate', '2020-01-17');

INSERT INTO developers
VALUES (4, 'Ivan', 'Ivanov');

INSERT INTO developers
VALUES (5, 'Petr', 'Petrov');

INSERT INTO developers
VALUES (6, 'Sergey', 'Ivanov');

INSERT INTO developers
VALUES (7, 'Mike', 'Djonson');

INSERT INTO developers
VALUES (8, 'Artem', 'Lopata');

INSERT INTO developers
VALUES (9,'Astra', 'Mironova');

INSERT INTO developers
VALUES (10, 'Zlata', 'Maximochkina');

INSERT INTO developers
VALUES (11, 'Daria', 'Lihacheva');

INSERT INTO projects_developers
VALUES (1, 1);

INSERT INTO projects_developers
VALUES (1, 3);

INSERT INTO projects_developers
VALUES (3, 2);

INSERT INTO projects_developers
VALUES (2, 4);

set foreign_key_checks=1;