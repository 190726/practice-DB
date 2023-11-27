drop table CT_USERS;
create table CT_USERS(
    ID number(10) not null primary key,
    username varchar2(50) not null,
    password varchar2(500) not null,
    verified varchar2(1),
    locked varchar2(1),
    ACC_CRED_EXPIRED varchar2(1)
);

drop sequence USERS_SEQ;
create sequence USERS_SEQ;

