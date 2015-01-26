/* Drop Tables */

DROP TABLE users CASCADE CONSTRAINTS;

/* Create Tables */

CREATE TABLE users
(
	id varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	pass varchar2(20),
	email varchar2(30),
	join_date date DEFAULT sysdate,
	phone varchar2(20),
	grade varchar2(10) DEFAULT 'member',
	PRIMARY KEY (id)
);