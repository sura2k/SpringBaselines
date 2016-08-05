CREATE DATABASE  IF NOT EXISTS demodb;
USE demodb;

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `role` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`,`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`username`, `password`, `enabled`)
VALUES
('user@app.com', 'password123', 1),
('admin@app.com', 'password123', 1);

INSERT INTO `roles` (`username`, `role`)
VALUES
('user@app.com', 'ROLE_USER'),
('admin@app.com', 'ROLE_ADMIN');

commit;