CREATE USER 'camx'@'localhost' IDENTIFIED BY 'uol123';

CREATE DATABASE camx_development;
GRANT ALL PRIVILEGES ON camx_development.* TO 'camx'@'localhost' WITH GRANT OPTION;
