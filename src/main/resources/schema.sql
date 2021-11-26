DROP TABLE IF EXISTS `user` CASCADE;
DROP TABLE IF EXISTS `sitter` CASCADE;
DROP TABLE IF EXISTS `parent` CASCADE;
DROP TABLE IF EXISTS `children` CASCADE;

CREATE TABLE `user` (
                        `no` bigint PRIMARY KEY AUTO_INCREMENT,
                        `name` varchar(20) NOT NULL,
                        `birthday` datetime NOT NULL,
                        `gender` varchar(5) NOT NULL,
                        `id` varchar(30) UNIQUE NOT NULL,
                        `password` varchar(80) NOT NULL,
                        `email` varchar(80) UNIQUE NOT NULL
);

CREATE TABLE `sitter` (
                          `no` bigint PRIMARY KEY AUTO_INCREMENT,
                          `user_id` varchar(30) UNIQUE NOT NULL,
                          `intro_msg` varchar(500) NOT NULL,
                          `care_from` int NOT NULL,
                          `care_to` int NOT NULL
);

CREATE TABLE `parent` (
                          `no` bigint PRIMARY KEY AUTO_INCREMENT,
                          `user_id` varchar(30) UNIQUE NOT NULL,
                          `request_msg` varchar(500) NOT NULL
);

CREATE TABLE `children` (
                            `no` bigint PRIMARY KEY AUTO_INCREMENT,
                            `parent_no` bigint UNIQUE NOT NULL,
                            `birthday` datetime NOT NULL
);

ALTER TABLE `sitter` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `sitter` ADD CHECK (`care_to` >= `care_from`);

ALTER TABLE `parent` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `children` ADD FOREIGN KEY (`parent_no`) REFERENCES `parent` (`no`);
