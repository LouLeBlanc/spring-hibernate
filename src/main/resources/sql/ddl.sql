CREATE USER IF NOT EXISTS 'prospring5' IDENTIFIED BY 'prospring5';

DROP DATABASE publishing_hibernate;

CREATE SCHEMA publishing_hibernate;

GRANT ALL PRIVILEGES ON publishing_hibernate.* TO 'prospring5';
FLUSH PRIVILEGES;

/* Clarify timezone in case of java.sql.SQLException */
SET GLOBAL time_zone = '+5:00';

