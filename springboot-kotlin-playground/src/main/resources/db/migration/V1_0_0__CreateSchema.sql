
create table message(id varchar(36) not null, text varchar(142) not null);

alter table message add constraint pk_message primary key (id);