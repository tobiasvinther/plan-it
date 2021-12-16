USE planit;

-- Users

-- It is not possible to create a user through an SQL script, because of the way out program hashes and saves passwords in the database. If you were to create a user through a script, the user's password will not be hashed,
-- and this will create problems. Therefore, we recommend that you create a user through the app itself and then run the scripts below to generate projects, subprojects, and tasks for that user.
-- These scripts will only work if your user has an id of 1, but if it is the first user to be created, it should indeed have id = 1. 
-- However, if you only want to look at the database and don't need the application itself to work, then you can use the script in the comment below to generate a user:

-- INSERT INTO users (userid, username , useremail, userpassword) VALUES (1, 'TestUser1', 'testUser1@gmail.com', 'Test123');

-- Projects

INSERT INTO projects (id, name , deadline, project_owner, status) VALUES (1, 'Build a house', '2022-12-31', 1, 'In progress');
INSERT INTO projects (id, name , deadline, project_owner, status) VALUES (2, 'Study for exam', '2022-01-12', 1, 'Pending');
INSERT INTO projects (id, name , deadline, project_owner, status) VALUES (3, 'Launch video game', '2023-06-01', 1, 'Pending');

-- Subprojects

INSERT INTO subprojects (id, name , deadline, subproject_owner) VALUES (1, 'Design', '2022-02-28', 1);
INSERT INTO subprojects (id, name , deadline, subproject_owner) VALUES (2, 'Pro-curement', '2022-04-01', 1);
INSERT INTO subprojects (id, name , deadline, subproject_owner) VALUES (3, 'Construction', '2022-10-01', 1);

INSERT INTO subprojects (id, name , deadline, subproject_owner) VALUES (4, 'Subjects', '2022-10-01', 2);

INSERT INTO subprojects (id, name , deadline, subproject_owner) VALUES (5, 'Idea phase', '2022-10-01', 3);
INSERT INTO subprojects (id, name , deadline, subproject_owner) VALUES (6, 'Prototyping', '2022-10-01', 3);

-- Tasks

INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (1, 'Permits', 'Get some permits.', 12, 'Done', '2022-02-01', 1);
INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (2, 'Drawings', 'Draw some drawings.', 20, 'In progress', '2022-02-14', 1);

INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (3, 'Electrical', 'Gotta have some power.', 30, 'Pending', '2022-02-01', 2);
INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (4, 'Plumbing', 'Gotta have some light.', 25, 'Pending', '2022-03-01', 2);
INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (5, 'HVAC', 'Let us find out what this is.', 6, 'Pending', '2022-03-14', 2);

INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (6, 'Interior', 'Let us build the inside of the house.', 100, 'Pending', '2022-03-01', 3);
INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (7, 'Exterior', 'Let us outside of the house.', 110, 'Pending', '2022-03-14', 3);

--

INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (8, 'Math', 'Adding, subtracting, all that stuff.', 10, 'In progress', '2022-05-14', 4);
INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (9, 'English', 'To be or not to be.', 8, 'Pending', '2022-05-14', 4);
INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (10, 'Geography', 'Where countries are and more.', 9, 'Pending', '2022-05-14', 4);

--

INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (11, 'Settle on a genre', 'FPS, RPG, RTS, etc.', 9, 'Pending', '2022-02-22', 5);

INSERT INTO tasks (id, name , description, hours, status, deadline, task_owner) VALUES (12, 'Choose an engine', 'Game Maker, Unity, or Unreal?', 6, 'Pending', '2022-03-22', 6);


