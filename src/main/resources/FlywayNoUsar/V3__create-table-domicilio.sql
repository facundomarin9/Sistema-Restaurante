create table domicilio(

    id bigint not null auto_increment,
    calle varchar(100) not null,
    numero bigint not null,
    localidad varchar(100),

    primary key(id)

);