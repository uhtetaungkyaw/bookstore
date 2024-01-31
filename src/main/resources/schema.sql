

create table book(
    id int not null auto_increment,
    name varchar (100) not null,
    isbn varchar (45) not null,
    description varchar (900) not null,
    category varchar (45),
    our_Price double,
    list_price double,
    primary key (id));

create table order_detail(
    id int not null auto_increment,
    name varchar (100) not null,
    price double,
    quantity int,
    primary key (id)
);





