create table contact(
	id bigint primary key not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    phone varchar(255) not null,
    customer_id bigint not null,
    constraint contact_customer_fk foreign key (customer_id) references customer (id)
);

alter table customer drop column contact_name;
alter table customer drop column email;
alter table customer drop column phone;