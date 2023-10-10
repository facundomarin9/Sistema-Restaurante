create table pedido(

    id bigint not null auto_increment,
    estado int not null,
    horaestimadaFin TIME not null,
    tipoenvio int not null,
    total float not null,
    cliente_id bigint not null,
    domicilio_id bigint not null,
    factura_id bigint not null,


    primary key(id)


);