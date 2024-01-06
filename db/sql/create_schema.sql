CREATE TABLE customer
(
    id            bigserial,
    name       varchar(50) NOT NULL,
    lastname    varchar(50) NOT NULL,
    email  varchar(50) NOT NULL,
    phone varchar(20)  NOT NULL,
    date_record  timestamp NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY ( "id" )
);

CREATE TABLE room
(
    "id"            bigserial NOT NULL,
    number_room int,
    kind_of      varchar(50) NOT NULL,
    description varchar(50) NOT NULL,
    size            int NOT NULL,
    price         double precision NOT NULL,
    CONSTRAINT pk_hotel PRIMARY KEY ( "id" )
);

CREATE TABLE booking
(
    "id"                  serial NOT NULL,
    date_start       date NOT NULL,
    date_end         date NULL,
    status             varchar(20),
    total_amount  double precision NULL,
    customer_id    bigint,
    room_id          bigint,
    CONSTRAINT pk_reservation PRIMARY KEY ( "id" ),
    CONSTRAINT fk_customer FOREIGN KEY ( customer_id ) REFERENCES customer ( "id" ) ON DELETE NO ACTION ,
    CONSTRAINT fk_room FOREIGN KEY ( room_id ) REFERENCES room ( "id" ) ON DELETE NO ACTION
);
