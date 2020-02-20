set foreign_key_checks=0;


INSERT INTO projects
VALUES (DEFAULT, 'Create a web application based on SpringJDBC', '2019-07-15');

INSERT INTO projects
VALUES (DEFAULT, 'Create a web application based on SpringBoot','2019-08-13');

INSERT INTO projects
VALUES (DEFAULT, 'Create a web application based on Hibernate', '2020-01-17');


INSERT INTO developers
VALUES (DEFAULT, 'Ivan', 'Ivanov');

INSERT INTO developers
VALUES (DEFAULT, 'Petr', 'Petrov');

INSERT INTO developers
VALUES (DEFAULT, 'Sergey', 'Ivanov');

INSERT INTO developers
VALUES (DEFAULT, 'Mike', 'Djonson');

INSERT INTO developers
VALUES (DEFAULT, 'Artem', 'Lopata');

INSERT INTO developers
VALUES (DEFAULT,'Astra', 'Mironova');

INSERT INTO developers
VALUES (DEFAULT, 'Zlata', 'Maximochkina');

INSERT INTO developers
VALUES (DEFAULT, 'Daria', 'Lihacheva');

INSERT INTO projects_developers
VALUES (1, 1);

INSERT INTO projects_developers
VALUES (1, 3);

INSERT INTO projects_developers
VALUES (3, 2);

INSERT INTO projects_developers
VALUES (2, 4);

set foreign_key_checks=1;