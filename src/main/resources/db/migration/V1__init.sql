create table users
(
    id       bigint,
    email    varchar(50) unique,
    password varchar(80) not null,
    primary key (id)
);

create table roles (
    id bigint,
    name varchar (50) not null,
    primary key (id)
);

create table users_roles (
    user_id bigint not null ,
    role_id bigint not null ,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles(name)
values
    ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (email, password)
values
    ('user@gmail.com', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
    ('admin@gmail.com', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
values
    (1, 1),
    (2, 2);