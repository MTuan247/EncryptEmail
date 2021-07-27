create database EncryptEmail;
use EncryptEmail;
go

create table Account(
	id int not null primary key identity(1,1),
	email varchar(100) not null unique,
	password varchar(100) not null,
	application_password char(20) not null,
	public_key binary(350),
	private_key binary(1250)
);

drop table Account;
