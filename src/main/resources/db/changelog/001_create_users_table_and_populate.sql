-- Create the "users" table
CREATE TABLE `users` (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   username VARCHAR(50) NOT NULL,
   email VARCHAR(100) NOT NULL UNIQUE,
   user_password VARCHAR(100) NOT NULL,
   surname VARCHAR(50),
   user_role VARCHAR(50) NOT NULL,
   is_active TINYINT(1) DEFAULT 1,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   INDEX (email)
);
