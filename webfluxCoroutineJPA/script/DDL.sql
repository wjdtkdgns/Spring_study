CREATE
DATABASE test CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `a`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `varchar256`  varchar(256) NOT NULL,
    `varchar512`  varchar(512) NOT NULL,
    `intt`         int          NOT NULL,
    `bigintt`      bigint       NOT NULL,
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='a';

CREATE TABLE `b`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `aid`         bigint       NOT NULL,
    `varchar256`  varchar(256) NOT NULL,
    `varchar512`  varchar(512) NOT NULL,
    `intt`         int          NOT NULL,
    `bigintt`      bigint       NOT NULL,
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='b';

CREATE TABLE `c`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `aid`         bigint       NOT NULL,
    `varchar256`  varchar(256) NOT NULL,
    `varchar512`  varchar(512) NOT NULL,
    `intt`         int          NOT NULL,
    `bigintt`      bigint       NOT NULL,
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='c';
CREATE INDEX idx__aid ON c (aid);
