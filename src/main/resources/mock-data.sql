INSERT INTO user_roles (role)
VALUES ('DIRECTOR'), ('MANAGER'), ('LEAD'), ('SENIOR'), ('JUNIOR'), ('QA');

INSERT INTO app_users (given_name, surname, email, username, "password", role_id)
VALUES
  ('Alice', 'Anderson', 'alice.anderson@revature.com', 'aanderson', 'p4$$W0RD', 'b904a168-b5fe-41df-bb61-730a7bb83b86'),
  ('Bob', 'Bailey', 'bob.bailey@revature.com', 'bbailey', 'p4$$W0RD', '531f46a6-3859-44f6-90ad-f45596e025fa'),
  ('Charles', 'Cantrell', 'charles.cantrell@revature.com', 'ccantrell', 'p4$$W0RD', 'd5fad1fc-2a8b-43ba-90f4-0b6741afe447'),
  ('David', 'Daniels', 'david.daniels@revature.com', 'ddaniels', 'p4$$W0RD', 'd5fad1fc-2a8b-43ba-90f4-0b6741afe447'),
  ('Emily', 'Erikson', 'emily.erikson@revature.com', 'eerikson', 'p4$$W0RD', '3cbd7cf4-e0ce-4b70-92ca-3ec53643e563'),
  ('Frank', 'Freeman', 'frank.freeman@revature.com', 'ffreeman', 'p4$$W0RD', '3cbd7cf4-e0ce-4b70-92ca-3ec53643e563'),
  ('Greg', 'Garrett', 'greg.garrett@revature.com', 'ggarett', 'p4$$W0RD', '5a2e0415-ee08-440f-ab8a-778b37ff6874'),
  ('Howard', 'Hayes', 'howard.hayes@revature.com', 'hhayes', 'p4$$W0RD', '5a2e0415-ee08-440f-ab8a-778b37ff6874'),
  ('Tester', 'McTesterson', 'tester.mctesterson@revature.com', 'tester', 'p4$$W0RD', '7a0cecdb-1d9d-4f67-ae91-aee379826ab8');
 
INSERT INTO projects (name, owner_id) 
 VALUES ('Taskmaster', 'e07463f9-1d98-4757-a9f3-a2adadf9e001');
 
INSERT INTO project_members
VALUES 
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', 'e07463f9-1d98-4757-a9f3-a2adadf9e001'),
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', '8014d4b7-0412-47c7-a0d8-2129c8d47551'),
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', 'bcb6e445-2876-4dfd-9ea3-385354af2a17'),
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', 'fd20348a-2b0a-4ed2-b633-8e39ee2155ab'),
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', '031856cf-2fd4-4c34-9ccc-cd6ff6566a86'),
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', 'f2909110-a0de-4ac3-9dcc-7465167aa484'),
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', 'adeeb6cc-b81c-42be-a406-b47d9bae9649'),
  ('a9302e3d-69b0-4834-b892-e48f62009d0e', 'ab7f27b0-266a-4400-9ddb-f0b3dcbc413f');

INSERT INTO sprints (name, start_date, end_date, project_id)
VALUES 
  ('Sprint 0', '2022-08-29', '2022-09-02', 'a9302e3d-69b0-4834-b892-e48f62009d0e'),
  ('Sprint 1', '2022-09-05', '2022-09-16', 'a9302e3d-69b0-4834-b892-e48f62009d0e');
 
INSERT INTO tasks (title, description, point_value, due_date, priority_id, state_id, reporter_id, sprint_id)
VALUES
  ('[Taskmaster] - Create data model', 'Create a ERD for Taskmaster application', 5, '2022-09-02', 'P1', 'DONE', '8014d4b7-0412-47c7-a0d8-2129c8d47551', '6c400875-5560-45cd-a853-04d61c48a82f'),
  ('[Taskmaster] - Create database + tables', 'Create a remote DB with specified tables', 5, '2022-09-02', 'P1', 'DONE', '8014d4b7-0412-47c7-a0d8-2129c8d47551', '6c400875-5560-45cd-a853-04d61c48a82f'),
  ('[Taskmaster] - Initialize project repo', 'Create Java project and initialize GitHub repository', 5, '2022-09-02', 'P1', 'IN_PROGRESS', '8014d4b7-0412-47c7-a0d8-2129c8d47551', '6c400875-5560-45cd-a853-04d61c48a82f'),
  ('[Taskmaster] - Create welcome screen', 'Create a landing view that prompts users with navigation options when application starts', 2, '2022-09-07', 'P2', 'UNASSIGNED', '8014d4b7-0412-47c7-a0d8-2129c8d47551', '45afff16-df37-47eb-9ead-67f93707f177'),
  ('[Taskmaster] - User registration', 'Allow users to register accounts for the system', 8, '2022-09-09', 'P2', 'UNASSIGNED', '8014d4b7-0412-47c7-a0d8-2129c8d47551', '45afff16-df37-47eb-9ead-67f93707f177'),
  ('[Taskmaster] - User login', 'Allow users to log into the system using registered credentials', 5, '2022-09-09', 'P2', 'UNASSIGNED', '8014d4b7-0412-47c7-a0d8-2129c8d47551', '45afff16-df37-47eb-9ead-67f93707f177'),
  ('[Taskmaster] - Create task', 'Allow authenticated users to create tasks', 5, '2022-09-16', 'P2', 'UNASSIGNED', '8014d4b7-0412-47c7-a0d8-2129c8d47551', '45afff16-df37-47eb-9ead-67f93707f177');

INSERT INTO task_assignees
VALUES
  ('1ce02fe3-0941-4667-be18-f1d2b0b1c674', '8014d4b7-0412-47c7-a0d8-2129c8d47551'),
  ('1ce02fe3-0941-4667-be18-f1d2b0b1c674', 'fd20348a-2b0a-4ed2-b633-8e39ee2155ab'),
  ('8c3f9a42-8677-4eaf-a497-1ab8ed90e645', '8014d4b7-0412-47c7-a0d8-2129c8d47551'),
  ('8c3f9a42-8677-4eaf-a497-1ab8ed90e645', 'fd20348a-2b0a-4ed2-b633-8e39ee2155ab'),
  ('24da40dd-58d6-4818-9ac7-9305d1a7cfe6', '8014d4b7-0412-47c7-a0d8-2129c8d47551'),
  ('f77edbe9-11fb-4f02-94ed-8c6436bf0ebf', '031856cf-2fd4-4c34-9ccc-cd6ff6566a86'),
  ('92c0b7bc-7ed5-4dc0-bcdb-6b698707d037', 'bcb6e445-2876-4dfd-9ea3-385354af2a17'),
  ('92c0b7bc-7ed5-4dc0-bcdb-6b698707d037', 'f2909110-a0de-4ac3-9dcc-7465167aa484'),
  ('87af9611-43d0-48ed-9957-21cdad4b5c22', 'adeeb6cc-b81c-42be-a406-b47d9bae9649');