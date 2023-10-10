create table articulomanufacturado(

    id bigint not null auto_increment,
    tiempoestimadococina int not null,
    denominacion varchar(200) not null,
    precioventa DECIMAL(10, 2),
    imagen varchar(500),
    rubrogeneral_id bigint not null,


    primary key(id)


);