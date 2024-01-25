DROP TABLE IF EXISTS Organization;
DROP TABLE IF EXISTS Restaurant;

CREATE TABLE Organization (
       id INT AUTO_INCREMENT  PRIMARY KEY,
       name VARCHAR(250) NOT NULL,
       address VARCHAR(250) NOT NULL,
       city VARCHAR(250) DEFAULT NULL,
       state VARCHAR(250) DEFAULT NULL,
       manager VARCHAR(250) DEFAULT NULL,
       phone VARCHAR(250) DEFAULT NULL,
       email VARCHAR(250) DEFAULT NULL

);

CREATE TABLE Restaurant (
      id INT AUTO_INCREMENT  PRIMARY KEY,
      name VARCHAR(250) NOT NULL,
      address VARCHAR(250) NOT NULL,
      city VARCHAR(250) DEFAULT NULL,
      state VARCHAR(250) DEFAULT NULL,
      manager VARCHAR(250) DEFAULT NULL,
      phone VARCHAR(250) DEFAULT NULL,
      email VARCHAR(250) DEFAULT NULL,
      food VARCHAR(250) DEFAULT NULL,
      status VARCHAR(250) DEFAULT NULL

);