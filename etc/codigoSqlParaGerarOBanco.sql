drop database controledetoner;
create database controledetoner;

use controledetoner;

ALTER DATABASE controledetoner CHARSET = UTF8 COLLATE = utf8_general_ci;

create table usuarios(
    idUsuario int auto_increment,
    login varchar(50),
    senha varchar(50),
    tipoDeUsuario varchar(50),
    primary key(idUsuario)
);

create table setores(
    idSetor int auto_increment,
    nomeSetor varchar(50),
    nomeEmpresa varchar(50),
    primary key(idSetor)
);

create table impressoras(
    idImpressora int auto_increment,
    idSetor int,
    modeloImpressora varchar(50),
    modeloToner varchar(50),
    precoToner double,
    primary key(idImpressora),
    foreign key(idSetor) references setores (idSetor)
);

create table toners(
    idToner int auto_increment,
    idImpressora int,
    tipoDeToner varchar(50),
    estoque int,
    fora int,
    desabilitado int,
    primary key(idToner),
    foreign key(idImpressora) references impressoras(idImpressora)
);

create table entradas(
    idEntrada int auto_increment,
    idToner int,
    idUsuario int,
    dataEntrada datetime,
    tipoDeEntrada varchar(50),
    quantidade int,
    primary key(idEntrada),
    foreign key(idToner) references toners(idToner),
    foreign key(idUsuario) references usuarios(idUsuario)
);

create table saidas(
    idSaida int auto_increment,
    idToner int,
    idUsuario int,
    idSetor int,
    dataSaida datetime,
    quantidade int,
    primary key(idSaida),
    foreign key(idToner) references toners(idToner),
    foreign key(idUsuario) references usuarios(idUsuario)
);



insert into usuarios (login,senha,tipoDeUsuario) values ('admin','123','Administrador');
insert into usuarios (login,senha,tipoDeUsuario) values ('padrao','123','padrao');

insert into setores (nomeSetor,nomeEmpresa) values ('Secretaria','Faculdade');
insert into setores (nomeSetor,nomeEmpresa) values ('Financeiro','Faculdade');

insert into impressoras(idSetor,modeloImpressora,modeloToner,precoToner) values (1,'HP P1102w','HP85A',240.0);
insert into impressoras(idSetor,modeloImpressora,modeloToner,precoToner) values (2,'Samsung M2020w','D111S',72.68);

insert into toners (idImpressora,tipoDeToner,estoque,fora,desabilitado) values (1,'Consignado',0,0,0);
insert into toners (idImpressora,tipoDeToner,estoque,fora,desabilitado) values (1,'Próprio',0,0,0);
insert into toners (idImpressora,tipoDeToner,estoque,fora,desabilitado) values (2,'Consignado',0,0,0);
