create table articulomanufacturadodetalle(

    id bigint not null auto_increment,
    cantidad DECIMAL(10, 2) not null,
    unidadMedida varchar(200) not null,
    articulomanufacturado_id bigint not null,
    articuloinsumo_id bigint not null,


    primary key(id)


);