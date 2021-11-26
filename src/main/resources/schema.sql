DROP TABLE IF EXISTS `user` CASCADE;

CREATE TABLE `user`
(
    `id`   bigint      NOT NULL AUTO_INCREMENT,
    `name` varchar(10) NOT NULL,
    PRIMARY KEY (`id`)
);
