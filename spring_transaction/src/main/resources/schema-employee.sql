drop table employee CASCADE CONSTRAINTS;
drop sequence employee_id_seq;
CREATE SEQUENCE employee_id_seq start with 1;
CREATE TABLE employee (
  id  NUMBER(4)  PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(30),
  phone VARCHAR(30),
  salary NUMBER(8) default 0
);