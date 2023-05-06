INSERT INTO worker (NAME,BIRTHDAY,LEVEL,SALARY )
VALUES
('Anna Moore','1996-01-01','Trainee',700),
('Ben Williams','1992-02-11','Junior',1100),
('David Jackson','1990-03-22','Middle',4000),
('Emma Garcia','1987-04-12','Senior',12000),
('Jon Lewis','1999-05-08','Trainee',800),
('Robert King','1994-06-19','Junior',1500),
('Bob Jackson','1989-07-24','Middle',6000),
('Emily Lopez','1988-08-17','Senior',13000),
('Max Snow','2000-12-07','Trainee',800),
('Elon Kingly','1994-10-02','Junior',1500),
('Bill Musk','1993-11-04','Middle',6000),
('Alise Dreamer','1985-01-13','Senior',13000);

INSERT INTO client (NAME)
VALUES
('Kate Foster'),
('Emma Fox'),
('Robert Green'),
('Peter Perez'),
('Joseph Baker'),
('Ally Young');


INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
(1, '2023-04-01', '2023-05-01'),
(2, '2023-05-01', '2024-05-01'),
(4, '2023-06-01', '2025-01-01'),
(4, '2023-07-01', '2023-08-01'),
(5, '2023-08-01', '2023-12-31'),
(6, '2023-09-01', '2023-11-01'),
(1, '2023-10-01', '2023-12-01'),
(2, '2023-11-01', '2025-11-01'),
(3, '2023-09-01', '2023-12-01'),
(4, '2023-04-01', '2025-04-01'),
(2, '2023-05-01', '2024-11-01'),
(6, '2023-06-01', '2023-12-31');



INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES
  (1, 3),
  (2, 2),
  (2, 10),
  (3, 3),
  (4, 4),
  (5, 4),
  (6, 3),
  (7, 7),
  (7, 8),
  (8, 8),
  (9, 6),
  (10, 10),
  (11, 10),
  (12, 2),
  (12, 7);