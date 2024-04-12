-- 2024-04-11
-- jspUser / mysql / jspdb

create table board(
bno int not null auto_increment,
title varchar(100) not null,
writer varchar(50) not null,
content text,
regdate datetime default now(),
moddate datetime default now(),
primary key(bno));