DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `review` varchar(255) NOT NULL,
  `post_id` int DEFAULT NULL
);

INSERT INTO `comment` VALUES (1,'comment1',2),(2,'comment2',2),(3,'comment3',3),(4,'comment4',3);

DROP TABLE IF EXISTS `details`;

CREATE TABLE `details` (
  `details_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `create_on` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `post_id` int DEFAULT NULL
);

INSERT INTO `details` VALUES (1,'2022-05-12 10:17:54','romain',1),(2,'2022-05-12 12:14:38','romain',2),(3,'2022-05-16 14:03:51','romain',3),(4,'2022-05-15 22:00:00','sessa',4),(5,'2022-05-15 22:00:00','romain',5),(6,'2022-05-16 14:32:03','romain',6),(7,'2022-05-16 14:36:20','romain',7);

DROP TABLE IF EXISTS `internal_role`;

CREATE TABLE `internal_role` (
  `role_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) NOT NULL
);

INSERT INTO `internal_role` VALUES (1,'ADMIN'),(2,'USER');

DROP TABLE IF EXISTS `internal_user`;

CREATE TABLE `internal_user` (
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `user_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY
);

/** admin / admin ; user / user */
INSERT INTO `internal_user` VALUES ('$2a$10$9a1zixwLAD4PGFHpEmDEs.uOzVArR6JK1IEthePXF.PlkShilgKXO','admin',1),('$2a$10$h2e3LV0aO8q..pDDSRYMiuTPOuGIjbcYIRyAS7siFrYz0IhpnibWi','user',2);

DROP TABLE IF EXISTS `internal_user_roles`;

CREATE TABLE `internal_user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
);

INSERT INTO `internal_user_roles` VALUES (1,1),(2,2);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` varchar(255) NOT NULL
);

INSERT INTO `post` VALUES (1,'post1'),(2,'post2'),(3,'post3'),(4,'post4'),(5,'post5'),(6,'post6'),(7,'post7');

DROP TABLE IF EXISTS `post_tags`;

CREATE TABLE `post_tags` (
  `post_id` int NOT NULL,
  `tag_id` int NOT NULL
);

INSERT INTO `post_tags` VALUES (1,1),(1,2),(2,2);

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) NOT NULL
);

INSERT INTO `tag`(name) VALUES ('tag1'),('tag2'),('tag3');