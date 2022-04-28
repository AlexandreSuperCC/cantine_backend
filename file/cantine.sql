create database mo_cantine;
use mo_cantine;
create table `menu` (
    `id` int(11) NOT NULL,
    `name` varchar(50) NOT NULL DEFAULT '',
    `type` int(11) NOT NULL DEFAULT 3 COMMENT '0 entrée, 1 plat, 2 dessert, 3 autres',
    `rate` varchar(10),
    `content` text,
    `amount` int(11),
    `day` int(11),
    `imgurl` varchar(100),
    `ts` varchar(50),
    `dr` int(11) NOT NULL DEFAULT 0 COMMENT '0 active, 1 inactive',
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `news`(
    `id` int(11) NOT NULL,
    `content` varchar(200),
    `time` varchar(20),
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;