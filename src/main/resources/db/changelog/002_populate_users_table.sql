INSERT INTO users (username, email, user_password, surname, user_role, is_active) VALUES
  ('administrator', 'admin@gmail.com', '$2a$10$buGux2XJx8tVDmB/Tkm7w.YB7F.IJOmtY/oTm3bRlP6UaRlYuFQae', 'adminSurname', 'ROLE_ADMIN', 1),
  ('John', 'john.doe@example.com', '$2a$10$t.ufNqL8PC4j5tFUhdTdLO/EyTZzcF4WDmqL4K7ZOspahJizBJJPW', 'Doe', 'ROLE_MANAGER', 0),
  ('Jane', 'jane.doe@example.com', '$2a$10$t.ufNqL8PC4j5tFUhdTdLO/EyTZzcF4WDmqL4K7ZOspahJizBJJPW', 'Doe', 'ROLE_MANAGER', 1),
  ('Alice', 'alice@example.com', '$2a$10$t.ufNqL8PC4j5tFUhdTdLO/EyTZzcF4WDmqL4K7ZOspahJizBJJPW', 'Smith', 'ROLE_MANAGER', 1),
  ('Bob', 'bob@example.com', '$2a$10$t.ufNqL8PC4j5tFUhdTdLO/EyTZzcF4WDmqL4K7ZOspahJizBJJPW', 'Johnson', 'ROLE_MANAGER', 0),
  ('Charlie', 'charlie@example.com', '$2a$10$t.ufNqL8PC4j5tFUhdTdLO/EyTZzcF4WDmqL4K7ZOspahJizBJJPW', 'Brown', 'ROLE_MANAGER', 0);
