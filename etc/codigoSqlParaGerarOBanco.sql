drop database controledetoner;
create database controledetoner;

use controledetoner;

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
    qtdEstoqueCheio int,
    qtdEstoqueVazio int,
    qtdForaCheio int,
    qtdForaVazio int,
    qtdDesabilitadoCheio int,
    qtdDesabilitadoVazio int,
    primary key(idToner),
    foreign key(idImpressora) references impressoras(idImpressora)
);

create table entradas(
    idEntrada int auto_increment,
    idToner int,
    idUsuario int,
    dataEntrada date,
    tipoDeEntrada varchar(50),
    qtdCheio int,
    qtdVazio int,
    primary key(idEntrada),
    foreign key(idToner) references toners(idToner),
    foreign key(idUsuario) references usuarios(idUsuario)
);

create table saidas(
    idSaida int auto_increment,
    idToner int,
    idUsuario int,
    idSetor int,
    dataSaida date,
    qtdCheio int,
    qtdVazio int,
    primary key(idSaida),
    foreign key(idToner) references toners(idToner),
    foreign key(idUsuario) references usuarios(idUsuario)
);



insert into usuarios (login,senha) values ('joao','123');