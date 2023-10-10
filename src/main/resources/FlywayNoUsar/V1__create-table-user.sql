create table users(

    id bigint not null auto_increment,
    usuario varchar(100) not null unique,
    clave varchar(500) not null,
    role varchar(100) not null,
    idCliente bigint not null,


    primary key(id)


);