/**
 * CREATE Script for init of DB
 */

insert into user (id, email, password, role) values (1, 'user01@abc.com', 'password@1', 'ROLE_USER');
insert into user (id, email, password, role) values (2, 'user02@pqr.com', 'password@2', 'ROLE_USER');

insert into loan (id, amount, duration, status, user_id) values (loan_seq.nextval, 10000.10, 3, 'IN_PROGRESS', 1);
insert into loan (id, amount, duration, status, user_id) values (loan_seq.nextval, 20000.20, 6, 'CREATED', 1);
insert into loan (id, amount, duration, status, user_id) values (loan_seq.nextval, 30000.30, 9, 'IN_PROGRESS', 1);
insert into loan (id, amount, duration, status, user_id) values (loan_seq.nextval, 40000.40, 12, 'CREATED', 1);


insert into loan (id, amount, duration, status, user_id) values (loan_seq.nextval, 35000.30, 9, 'IN_PROGRESS', 2);
insert into loan (id, amount, duration, status, user_id) values (loan_seq.nextval, 45000.40, 12, 'CREATED', 2);
