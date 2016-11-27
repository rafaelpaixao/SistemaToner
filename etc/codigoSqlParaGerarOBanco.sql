/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Rafael
 * Created: 23/11/2016
 */

create database controledetoner;

use controledetoner;

create table setor(
    idSetor int auto_increment,
    nomeSetor varchar(255),
    empresa varchar(255),
    
);

create table toner(
    idToner int auto_increment,
    descricao varchar(255),
    idModelo int,
    idSetor int,
    numSerie varchar(255),
    tipo varchar(255),
    primary key(idToner)
);

create table modeloToner(
    idModeloToner int auto_increment,
    nomeModeloToner varchar(255),
    fabricante varchar(255),
    preco double,
    primary key(idModeloToner)
);

create table movEntradas(

);

create table movSaidas(

);