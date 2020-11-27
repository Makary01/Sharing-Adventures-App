-- --------------------------------
-- SCHEMA sharing_adventures
-- --------------------------------
CREATE DATABASE IF NOT EXISTS sharing_adventures DEFAULT CHARACTER SET utf8;
USE sharing_adventures;

-- --------------------------------
-- Table sharing_adventures -> users
-- --------------------------------
CREATE TABLE IF NOT EXISTS users
(
    id       INT UNIQUE AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) UNIQUE        NOT NULL,
    email    VARCHAR(255) UNIQUE       NOT NULL,
    password VARCHAR(255)              NOT NULL,
    city     VARCHAR(255),
    country  VARCHAR(255),
    PRIMARY KEY (id)
);


-- --------------------------------
-- Table sharing_adventures -> adventures
-- --------------------------------
CREATE TABLE IF NOT EXISTS adventures
(
    id       INT UNIQUE AUTO_INCREMENT NOT NULL,
    user_id INT UNIQUE NOT NULL REFERENCES users(id),
    type VARCHAR(255) NOT NULL ,
    title VARCHAR(255) NOT NULL ,
    content TEXT ,
    start_date DATE NOT NULL ,
    end_date DATE NOT NULL
);
