use `test`;

create table account(
    id int(11) not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    primary key(id)
)Engine = InnoDB charset = utf8;

create table role(
    id int(11) not null auto_increment,
    account_id int(11) not null,
    authority varchar(255) not null,
    primary key(id)
)Engine = InnoDB  charset = utf8;use `test`;
