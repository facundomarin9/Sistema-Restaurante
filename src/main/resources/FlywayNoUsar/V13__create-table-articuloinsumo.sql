create table articuloinsumo(

    id bigint not null auto_increment,
    denominacion varchar(500) not null,
    preciocompra DECIMAL(10, 2) not null,
    precioventa DECIMAL(10, 2) not null,
    stockactual DECIMAL(10, 2) not null,
    stockminimo DECIMAL(10, 2) not null,
    unidadmedida varchar(200) not null,
    esinsumo TINYINT,
    rubroarticulo_id bigint not null,



    primary key(id)


);