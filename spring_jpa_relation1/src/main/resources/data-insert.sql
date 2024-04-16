INSERT INTO category (id,code,name) VALUES(category_seq.nextval, 'FICTION','소설');
INSERT INTO category (id,code,name) VALUES(category_seq.nextval, 'ESSAY','에세이');
INSERT INTO category (id,code,name) VALUES(category_seq.nextval, 'ENGLISH','영어일반');
INSERT INTO category (id,code,name) VALUES(category_seq.nextval, 'ENGLISH BUSINESS ','비지니스영어');

insert into provider(id,name) values(provider_seq.nextval,'삼송출판사');
insert into provider(id,name) values(provider_seq.nextval,'오릴리출판사');
insert into provider(id,name) values(provider_seq.nextval,'아이티윌출판사');

insert into product  (id,name, price, stock,category_id,provider_id) values (product_seq.nextval,'모순', 3000, 34,1,1);
insert into product  (id,name, price, stock,category_id,provider_id) values (product_seq.nextval,'철도원3대', 2000, 45,1,1);
insert into product  (id,name, price, stock,category_id,provider_id) values (product_seq.nextval,'삼체세트', 2500, 56,1,1);
insert into product  (id,name, price, stock,category_id,provider_id) values (product_seq.nextval,'길 떠나기 그리고 걷기', 2200,12,2,2);
insert into product  (id,name, price, stock,category_id,provider_id) values (product_seq.nextval,'우미의 사랑별 편지', 1200, 23,2,2);


insert into product_detail (id,description, product_id)  values (product_detail_seq.nextval, '진짜모순이네요~~', 1);
insert into product_detail (id,description, product_id)  values (product_detail_seq.nextval, '철도원3대좋아요', 2);
insert into product_detail (id,description, product_id)  values (product_detail_seq.nextval, '삼체세트좋아요', 3);