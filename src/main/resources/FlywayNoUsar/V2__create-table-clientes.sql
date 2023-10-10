create table clientes(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    telefono int(15) not null,
    email varchar(100) not null unique,
    idDomicilio bigint not null,


    primary key(id)

);