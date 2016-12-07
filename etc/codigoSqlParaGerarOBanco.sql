drop database controledetoner;
create database controledetoner;

use controledetoner;

ALTER DATABASE controledetoner CHARSET = UTF8 COLLATE = utf8_general_ci;

create table usuarios(
    id int auto_increment,
    login varchar(50),
    senha varchar(50),
    tipo varchar(50),
    primary key(id)
);

create table setores(
    id int auto_increment,
    nome varchar(50),
    empresa varchar(50),
    primary key(id)
);

create table modelosImpressoras(
    id int auto_increment,
    modeloImpressora varchar(50),
    modeloToner varchar(50),
    precoToner double,
    primary key(id)
);

create table impressoras(
    id int auto_increment,
    idModeloImpressora int,
    idSetor int,
    tipo varchar(50),
    codigo varchar(50),
    primary key(id),
    foreign key(idModeloImpressora) references modelosImpressoras(id),
    foreign key(idSetor) references setores (id)
);

create table toners(
    id int auto_increment,
    idModeloImpressora int,
    tipo varchar(50),
    estoque int,
    fora int,
    desabilitado int,
    primary key(id),
    foreign key(idModeloImpressora) references modelosImpressoras(id)
);

create table entradas(
    id int auto_increment,
    idToner int,
    idUsuario int,
    data datetime,
    tipo varchar(50),
    quantidade int,
    primary key(id),
    foreign key(idToner) references toners(id),
    foreign key(idUsuario) references usuarios(id)
);

create table saidas(
    id int auto_increment,
    idToner int,
    idUsuario int,
    idSetor int,
    data datetime,
    quantidade int,
    primary key(id),
    foreign key(idToner) references toners(id),
    foreign key(idUsuario) references usuarios(id)
);



insert into usuarios (login,senha,tipo) values ('admin','123','Administrador');
insert into usuarios (login,senha,tipo) values ('padrao','123','Padrão');

insert into setores (nome,empresa) values ('Secretaria','Faculdade');
insert into setores (nome,empresa) values ('Financeiro','Faculdade');

insert into modelosImpressoras(modeloImpressora,modeloToner,precoToner) values ('HP P1102w','HP85A',240.0);
insert into modelosImpressoras(modeloImpressora,modeloToner,precoToner) values ('Samsung M2020w','D111S',72.68);

insert into impressoras(idModeloImpressora,idSetor,tipo,codigo) values (1,1,'Consignado','ABC123');
insert into impressoras(idModeloImpressora,idSetor,tipo,codigo) values (2,2,'Próprio','45678');

insert into toners (idModeloImpressora,tipo,estoque,fora,desabilitado) values (1,'Consignado',0,0,0);
insert into toners (idModeloImpressora,tipo,estoque,fora,desabilitado) values (1,'Próprio',0,0,0);
insert into toners (idModeloImpressora,tipo,estoque,fora,desabilitado) values (2,'Consignado',0,0,0);
