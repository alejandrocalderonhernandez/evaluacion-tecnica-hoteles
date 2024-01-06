### Features

- CRUD con spring web
- JPA con spring data + postgres
- CACHE con spring cache + redis
- SWAGGER con spring docs
- BASIC AUTH con spring security
- VALIDATIONS con validation
- UNITTEST con spring boot test + mockinto + junit
- DOCKER la app y las bases de datos estan dockerizadas

###Base de datos

la base de datos es postgresql

el diagrama entidad relacion y los scripts de esquema y datos se encuentran en:

booking_horl/db/sql

### Como usar la app con docker

La aplicacion esta dockerizada 100% para usarla promero debemos construir la imagen utilizando el siguente comando

`docker build -t booking-hotel:latest .`

Una ves construida la imagen podemos levantar el archivo docker-compose el cual ya trae las bases de datos y la configuracion de la imagen que construimos en el paso anteriror

`docker compose up`

una ves levantada la app vamos a la siguiente ruta para ver la documentacion de swagger
http://localhost:8080/booking_hotel/swagger-ui/index.html

default solo existe un usuario para probar los endpoints

|username   |  password |
| ------------ | ------------ |
| admin  |   secret|


### Como usar la app para desarrollo


primero debemos levantar nuestras bases de datos para eso usamos el archivo docker-compose-local.yml

`docker compose -f docker-compose-local.yml up `

debemos configurar un par de bariables de entorno para redis y postgres ya que estas variables son dinamicas debido a la virtualizacion en contenedores

|name   |  value |
| ------------ | ------------ |
| JDBC_URL  | jdbc:postgresql://localhost:5432/booking |
| REDIS_URL  |redis://127.0.0.1:6379|

Importar postman script:
en la raiz del proyecto esta un archivo llamado booking-hotel.postman_collection.json el cual debemos importar en postman

default solo existe un usuario para probar los endpoints

|username   |  password |
| ------------ | ------------ |
| admin  |   secret|

con eso podemos levantar la app en desarrollo

##Visita mis cursos que doy en udemy :)

https://www.udemy.com/user/rene-calderon-9/

##Visita mis canal de youtube :)

https://www.youtube.com/channel/UCIZ1JzPi-4tLmWFCdIoqmLQ


###End