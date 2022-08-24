CREATE TABLE user_roles (
  id uuid DEFAULT gen_random_uuid(),
  role varchar UNIQUE NOT NULL,
  
  CONSTRAINT user_roles_pk
  PRIMARY KEY (id)
);

CREATE TABLE app_users (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  given_name varchar NOT NULL,
  surname varchar NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  username varchar(25) NOT NULL UNIQUE CHECK (length(username) >= 4),
  password varchar NOT NULL CHECK (length(password) >= 8),
  role_id uuid NOT NULL,
  
  FOREIGN KEY (role_id) REFERENCES user_roles (id)
);

CREATE TABLE projects (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name varchar NOT NULL,
  owner_id uuid NOT NULL,
  
  FOREIGN KEY (owner_id) REFERENCES app_users (id)
);

CREATE TABLE project_members (
  project_id uuid NOT NULL, 
  user_id uuid NOT NULL,
  
  PRIMARY KEY (project_id, user_id),
  
  FOREIGN KEY (project_id) REFERENCES projects (id),
  FOREIGN KEY (user_id) REFERENCES app_users (id) 
);

CREATE TABLE sprints (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name varchar NOT NULL,
  start_date date NOT NULL,
  end_date date NOT NULL CHECK (end_date > start_date),
  project_id uuid NOT NULL,
  
  FOREIGN KEY (project_id) REFERENCES projects (id)
);

CREATE TABLE tasks (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  title varchar NOT NULL,
  description varchar NOT NULL,
  point_value int DEFAULT 5,
  due_date date CHECK (due_date >= CURRENT_DATE),
  priority_id varchar DEFAULT 'P4',
  state_id varchar DEFAULT 'UNASSIGNED',
  reporter_id uuid NOT NULL, 
  sprint_id uuid,
  
  FOREIGN KEY (reporter_id) REFERENCES app_users (id),
  FOREIGN KEY (sprint_id) REFERENCES sprints (id)
);

CREATE TABLE task_assignees (
  task_id uuid NOT NULL,
  user_id uuid NOT NULL,
  
  PRIMARY KEY (task_id, user_id),
  FOREIGN KEY (task_id) REFERENCES tasks (id),
  FOREIGN KEY (user_id) REFERENCES app_users (id)
);
