create table mercadopagodatos(

    id bigint not null auto_increment,
    identificadorpago bigint not null,
    fechacreacion DATETIME not null,
    fechaaprobacion DATETIME not null,
    formapago varchar(200) not null,
    metodopago varchar(200) not null,
    numerotarjeta varchar(500) not null,
    estado varchar(200) not null,
    pedido_id bigint not null,



    primary key(id)


);