create database agenda;

use agenda;

create table usuarios(
	id int primary key not null auto_increment,
	nombre varchar(50) not null,
    apellido varchar(70),
    direccion varchar(70),
    telefono varchar(50),
    email varchar(50) not null unique,
    fechaNacimiento datetime not null,
    pass varchar(150) not null
);


-- alter table contactos change id_usuario id_usuario int not null;

select * from usuarios;

INSERT INTO usuarios(nombre, apellido, direccion, telefono, email, fechaNacimiento, pass)
values('Joaquin', 'Coronado Ramírez', 'Rio reforma #1357', '3329887673','joaquin@hotmail.com', current_date(), '123');
INSERT INTO usuarios(nombre, apellido, direccion, telefono, email, fechaNacimiento, pass)
values('Jesica Monserrath', 'Cervantes Hernandez', 'Av. revolucion #5300', '098765434','jesica@hotmail.com', current_date(), '123');
INSERT INTO usuarios(nombre, apellido, direccion, telefono, email, fechaNacimiento, pass)
values('Claudia Adriana', 'Castillo Coronado', '5 de mayo #515', '3310998793','claudia@hotmail.com', current_date(), '123');

create table contactos(
	id int primary key not null auto_increment,
    id_usuario int not null,
	nombre varchar(50) not null,
    apellido varchar(70),
    direccion varchar(70),
    telefono varchar(50),
    email varchar(50) not null unique,
    fechaNacimiento datetime not null,
    imagen varchar(250),
    foreign key (id_usuario) references usuarios(id)
);

select * from contactos;

UPDATE contactos set imagen = 'test 'WHERE id = 10 and id_usuario = 1;

alter table contactos add imagen varchar(250);


INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Jesica Monserrath', 'Cervantes Hernandez', 'Revolucion #1357', '3329887673', 'jesi@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Claudia Adriana', 'Castillo Coronado', '5 de mayo #515', '3329887673', 'fea@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Ricardo', 'Coronado Ramírez', 'Rio Reforma #1357', '3329887673', 'ricardo@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Moisés', 'Chávez Rios', 'martires #515', '3329887673', 'moy@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Gerardo David', 'González Velazco', 'Pensador Mesicano #53', '3329887673', 'gera@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Efren', 'Cabañas Luna', 'Silva Romero #5215', '3329887673', 'efren@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('ector Ivan', 'Sanchez Ascencio', 'Donde da vuelta el viento #666', '3329887673', 'hector@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Romina Estefania', 'Perez López', 'Av. Gonzáles Gallo #51445', '3329887673', 'romina@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Marisol', 'Fernandez Castillo', 'Insulgenres #342', '3329887673', 'marisol@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Esteban', 'Castillo Pulido', 'Rio Seco #515', '3329887673', 'esteban@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Perla', 'Pulido Fernandez', 'patria  #3342', '3329887673', 'perla@hotmail.com', current_timestamp(), 1);
INSERT INTO contactos(nombre, apellido, direccion, telefono, email, fechaNacimiento, id_usuario)
values('Paloma', 'Peredo Salazar', 'rio rin  #52215', '3329887673', 'paloma@hotmail.com', current_timestamp(), 1);

update contactos set 
nombre = 'Claudia Adriana', 
apellido = 'Castillo Coronado', 
direccion = '5 de mayo #515', 
telefono =  '3329887673', 
email =  'claudia@hotmail.com', 
fechaNacimiento = current_timestamp()
where id = 3;
-- delete from contactos where id = 2;

select count(*) from contactos where id_usuario = 1;
select * from contactos where nombre like '%l%';