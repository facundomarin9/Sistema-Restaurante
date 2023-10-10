create table detallefactura(

    id bigint not null auto_increment,
    cantidad int not null,
    subtotal DECIMAL(10, 2) not null,
    articulomanufacturado_id bigint not null,
    articuloinsumo_id bigint not null,


    primary key(id)


);