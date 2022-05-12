create database mo_cantine;
use mo_cantine;

create table `t_cantine` (
    `id` int(11) NOT NULL,
    `name` varchar(50) NOT NULL DEFAULT '',
    `location` varchar(50),
    `dr` int(11) NOT NULL DEFAULT 0 COMMENT '0 active, 1 inactive',
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mo_cantine`.`t_cantine` (`id`, `name`, `location`, `dr`) VALUES (1, 'utbm_sevenans', 'Sevenans', 0);
INSERT INTO `mo_cantine`.`t_cantine` (`id`, `name`, `location`, `dr`) VALUES (2, 'utbm_duvillard', 'Belfort', 0);


create table `menu` (
    `id` int(11) NOT NULL,
    `name` varchar(50) NOT NULL DEFAULT '',
    `type` int(11) NOT NULL DEFAULT 3, -- 0 entrée, 1 plat, 2 dessert, 3 garnitures, 4 autres
    `rate` varchar(10),
    `content` text,
    `amount` int(11),
    `day` int(11),
    `imgurl` varchar(255),
    `ts` varchar(50),
    `cid` int(11),
    `dr` int(11) NOT NULL DEFAULT 0, -- 0 active, 1 inactive
    `ctimes` int(11) NOT NULL DEFAULT 0,
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `news`(
    `id` int(11) NOT NULL,
    `content` varchar(200),
    `time` varchar(20),
    `cid` int(11),
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `user`(
    `id` int(11) NOT NULL,
    `name` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `role` int(11) NOT NULL, -- 0 boss, 1 cook
     primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mo_cantine`.`user` (`id`, `name`, `password`, `role`) VALUES (1, 'admin', 'admin',0);
INSERT INTO `mo_cantine`.`user` (`id`, `name`, `password`, `role`) VALUES (2, 'cook', 'cook',1);
INSERT INTO `mo_cantine`.`user` (`id`, `name`, `password`, `role`) VALUES (3, 'Yuan Cao', '351ad58134d2110bb7ca0a4e3c8c9dfe',1);

insert into menu (id,name,type,rate,content,amount,day,imgurl,cid,ts,dr)
values
    (1,"Poisson pané",1,'',"Poisson",55,0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6yW9NhPNz8n--NfwFffveNDvL16Jt39rWYg&usqp=CAU',1,NULL,0),
    (2,"Omelette aux champignons", 1, '', "Oeuf, Champignon", 12, 0, 'https://fr.chatelaine.com/wp-content/uploads/0001/01/omelettes-individuelles-champignons-fromage-7713.jpg',1, NULL, 0),
    (3,"Paupiette de veau sauce forestière", 1, '', "Veau, Champignon", 35, 0, 'https://www.tendriade.fr/wp-content/uploads/30-paupiette-prov-v2-1.jpg',1, NULL, 0),
    (4,"Pomme de terre Catalane", 0, '', "Pomme de terre", 26, 0, 'https://p7.storage.canalblog.com/79/77/1168148/117643434_o.jpg',1, NULL, 0),
    (5,"Betteraves oeuf dur tomate", 0, '', "Betterave, oeuf, tomate",25,0,'https://p4.storage.canalblog.com/41/87/949906/77224594.jpg',1,NULL, 0),
    (6,"Rosette", 0, '', "Porc",65,0,'https://kirn.fr/wp-content/uploads/2020/06/rosette.jpg',1,NULL, 0),
    (7,"Haricots verts", 3, '', "Haricots verts",100,0,'https://www.academiedugout.fr/images/16047/1200-auto/haricot-vert_000.jpg?poix=50&poiy=50',1,NULL, 0),
    (8,"Penne Rigate", 3, '', "Pâtes",100,0,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo6aMhvcvwjLvJcJHNV92avSzMm8CRndAfYw&usqp=CAU',1,NULL, 0),
    (9,"Frites", 3, '', "Pommes de terres",100,0,'https://i0.wp.com/bonpourtoi.ca/app/uploads/2021/06/BPT-Articles-frites-tinyjpg.jpeg?fit=2530%2C1860&ssl=1',1,NULL, 0),
    (10,"Beignet", 2, '', "Beignet",36,0,'https://i0.wp.com/www.ivorianfood.com/wp-content/uploads/2020/05/beignets-sucr%C3%A9s-gateau-galette-donut-ivorianfood-1.png?fit=1500%2C1125&ssl=1',2,NULL, 0),
    (11,"Tarte coco", 2, '', "Noix de coco",34,0,'https://www.boncolac.fr/w2019/wp-content/uploads/2018/04/tarte-coco-intense.jpg',2,NULL, 0),
    (12,"Yaourt", 2, '', "Lait",58,0,'https://fr.openfoodfacts.org/images/products/303/349/000/4521/front_fr.85.full.jpg',2,NULL, 0),
    (13,"Brochette de poisson pané", 1, '', "Poisson",46,1,'https://www.passionfroid.fr/sites/passionfroid/files/styles/main_image_full/public/externals/f90004cb7335d247cd0c4115936ce1e1.jpg?itok=X4yNk6JF',2,NULL, 0),
    (14,"Tartiflette", 1, '', "Pomme de terre, Fromage, Porc",68,1,'https://p7.storage.canalblog.com/76/68/1599007/121810391.jpg',2,NULL, 0),
    (15,"Emincé de porc à l'ananas", 1, '', "Porc, Ananas",59,1,'https://img.clicbienetre.com/prod/images/news/640x350_4158.jpeg',2,NULL, 0),
    (16,"Salade à la russe", 0, '', "Crudités",15,1,'https://recettes.de/images/blogs/tradition-transmission/franciasalata-ou-salade-russe-la-typique-salade-hongroise-de-macedoine-de-legumes-61.640x480.jpg',2,NULL, 0),
    (17,"Pâté de campagne", 0, '', "Viande",45,1,'https://www.lameilleurecette.fr/refonte/wp-content/uploads/2015/05/pate.jpg',2,NULL, 0),
    (18,"Carotte tomates feta", 0, '', "Carotte, Tomate, Feta",58,1,'https://i0.wp.com/croquantfondantgourmand.com/wp-content/uploads/2020/04/tagliatelles-de-carottes-a-la-feta-p1230944-r.jpg?fit=1030%2C753&ssl=1',2,NULL, 0),
    (19,"Donut", 2, '', "Donut",61,1,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRFE_Rcifpa_9kwcfZtfoagwUDzQ76Dc3FNg&usqp=CAU',2,NULL, 0),
    (20,"Tarte cerise", 2, '', "Cerise",35,1,'http://img.over-blog.com/500x333/1/74/32/90/photos-2/photos-2-4421.JPG',2,NULL, 0),
    (21,"Fromage Blanc", 2, '', "Fromage",56,1,'https://static.750g.com/images/1200-630/024e34ebfef04a75a859a7e6be28600d/thinkstockphotos-511805370.jpg',2,NULL, 0),
    (22,"Petits pois", 3, '', "Petits pois",100,1,'https://cdn.radiofrance.fr/s3/cruiser-production/2020/05/c1bbe8ea-5cbc-4d83-96de-29b381c93514/1200x680_petits_pois_3.jpg',2,NULL, 0),
    (23,"Blé pilaf", 3, '', "Blé",100,1,'https://previews.123rf.com/images/kodbanker/kodbanker1703/kodbanker170300167/73708499-gros-bulgur-bl%C3%A9-%C3%A0-gros-grains-riz-entier-boulgour-bl%C3%A9-jaune-bulgur-pilaf-dinde-de-bl%C3%A9-bulgur-turc-bo.jpg',2,NULL, 0);


