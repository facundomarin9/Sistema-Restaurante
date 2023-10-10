create table factura(

    id bigint not null auto_increment,
    fecha DATETIME not null,
    numero int not null,
    montodescuento float,
    formapago varchar(200) not null,
    nrotarjeta varchar(200) not null,
    totalventa DECIMAL(10, 2),
    totalcosto DECIMAL(10, 2),



    primary key(id)


);