create database mo_cantine;
use mo_cantine;

create table `t_cantine` (
    `id` int(11) NOT NULL,
    `name` varchar(50) NOT NULL DEFAULT '',
    `location` varchar(50),
    `dr` int(11) NOT NULL DEFAULT 0 COMMENT '0 active, 1 inactive',
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table "menu" (
    "id" int(11) NOT NULL,
    "name" varchar(50) NOT NULL DEFAULT '',
    "type" int(11) NOT NULL DEFAULT 3, -- 0 entrée, 1 plat, 2 dessert, 3 garnitures, 4 autres
    "rate" varchar(10),
    "content" text,
    "amount" int(11),
    "day" int(11),
    "imgurl" varchar(100),
    "ts" varchar(50),
    "cid" int(11),
    "dr" int(11) NOT NULL DEFAULT 0, --0 active, 1 inactive
    primary key("id")
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table "news"(
    "id" int(11) NOT NULL,
    "content" varchar(200),
    "time" varchar(20),
    primary key("id")
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into "menu"("id","name","type","rate","content","amount","day","imgurl","ts","dr")
values
    (1,"Poisson pané",1,'',"Poisson",55,0,NULL,NULL,0),
    (2,"Omelette aux champignons", 1, '', "Oeuf, Champignon", 12, 0, NULL, NULL, 0),
    (3,"Paupiette de veau sauce forestière", 1, '', "Veau, Champignon", 35, 0, NULL, NULL, 0),
    (4,"Pomme de terre Catalane", 0, '', "Pomme de terre", 26, 0, NULL, NULL, 0),
    (5,"Betteraves oeuf dur tomate", 0, '', "Betterave, oeuf, tomate",25,0,NULL,NULL, 0),
    (6,"Rosette", 0, '', "Porc",65,0,NULL,NULL, 0),
    (7,"Haricots verts", 3, '', "Haricots verts",100,0,NULL,NULL, 0),
    (8,"Penne Rigate", 3, '', "Pâtes",100,0,NULL,NULL, 0),
    (9,"Frites", 3, '', "Pommes de terres",100,0,NULL,NULL, 0),
    (10,"Beignet", 2, '', "Beignet",36,0,NULL,NULL, 0),
    (11,"Tarte coco", 2, '', "Noix de coco",34,0,NULL,NULL, 0),
    (12,"Yaourt", 2, '', "Lait",58,0,NULL,NULL, 0),
    (13,"Brochette de poisson pané", 1, '', "Poisson",46,1,NULL,NULL, 0),
    (14,"Tartiflette", 1, '', "Pomme de terre, Fromage, Porc",68,1,NULL,NULL, 0),
    (15,"Emincé de porc à l'ananas", 1, '', "Porc, Ananas",59,1,NULL,NULL, 0),
    (16,"Salade à la russe", 0, '', "Crudités",15,1,NULL,NULL, 0),
    (17,"Pâté de campagne", 0, '', "Viande",45,1,NULL,NULL, 0),
    (18,"Carotte tomates feta", 0, '', "Carotte, Tomate, Feta",58,1,NULL,NULL, 0),
    (19,"Donut", 2, '', "Donut",61,1,NULL,NULL, 0),
    (20,"Tarte cerise", 2, '', "Cerise",35,1,NULL,NULL, 0),
    (21,"Fromage Blanc", 2, '', "Fromage",56,1,NULL,NULL, 0),
    (22,"Petits pois", 3, '', "Petits pois",100,1,NULL,NULL, 0),
    (23,"Blé pilaf", 3, '', "Blé",100,1,NULL,NULL, 0),
    (24,"Frites", 3, '', "Pomme de terre",100,1,NULL,NULL, 0);