DROP USER 'prospring5';
FLUSH PRIVILEGES;
CREATE USER 'prospring5' IDENTIFIED BY 'prospring5';

CREATE SCHEMA publishing_hibernate;

GRANT ALL PRIVILEGES ON publishing_hibernate.* TO 'prospring5';
FLUSH PRIVILEGES;

/* Clarify timezone in case of java.sql.SQLException */
SET GLOBAL time_zone = '+5:00';

