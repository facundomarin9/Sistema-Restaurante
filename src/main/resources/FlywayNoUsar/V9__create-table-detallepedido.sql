create table detallepedido(

    id bigint not null auto_increment,
    cantidad int not null,
    subtotal DECIMAL(10, 2) not null,
    pedido_id bigint not null,
    articulomanufacturado_id bigint not null,
    articuloinsumo_id bigint not null,


    primary key(id)


);