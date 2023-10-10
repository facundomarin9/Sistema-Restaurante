create table configuracion(

    id bigint not null auto_increment,
    cantidadcocineros int not null,
    emailempresa varchar(100) not null,
    tokenmercadopago varchar(500) not null,


    primary key(id)


);