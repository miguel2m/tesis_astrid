--DDL
--Modulo PERSONA
CREATE TABLE USUARIO (
    us_id		SERIAL PRIMARY KEY,
    us_nombreUsuario 	varchar(50) DEFAULT '' UNIQUE,
    us_nombre         	varchar(50) NOT NULL,
    us_apellido        	varchar(50) NOT NULL,
    us_fechaNacimiento  date NOT NULL,
    us_genero		varchar(1) CHECK (us_genero ='M' OR us_genero='F'),
    us_email	        varchar(100) UNIQUE,
    us_password         varchar(50)DEFAULT '',
    us_nivel_juego	integer DEFAULT 0
);



-- Procedures para REGISTRAR un Usuario
CREATE OR REPLACE FUNCTION InsertarUsuario
(_nombreUsuario VARCHAR(50), _nombre VARCHAR(50),
 _apellido VARCHAR(50), _fechaNacimiento date,
 _genero VARCHAR(1), _correo VARCHAR(100),
 _clave VARCHAR(50))
RETURNS integer
AS $$

BEGIN

   INSERT INTO usuario (us_nombreusuario,
	us_nombre,us_apellido,us_fechanacimiento,us_genero,us_email,us_password) VALUES
    ( _nombreUsuario, _nombre, _apellido, _fechaNacimiento, _genero, _correo, _clave);

   RETURN currval('usuario_us_id_seq');

END;
$$ LANGUAGE plpgsql;

-- Procedures para Buscar a un Usuario y comprueba si el nombre de usuario y la contrasena estan correctos (LOGIN)

CREATE OR REPLACE FUNCTION loginUsuario(_nombreusuario varchar, _clave varchar)
RETURNS TABLE
  (id integer,
   nombreUsuario varchar,
   nombre varchar,
   apellido varchar,
   fechNacimiento date,
   genero varchar,
   email varchar,
   _password varchar,
   nivel_juego integer)
AS
$$
BEGIN
	RETURN QUERY SELECT
	us_id, us_nombreusuario, us_nombre, us_apellido, us_fechanacimiento,us_genero,us_email, us_password, us_nivel_juego
	FROM usuario
	WHERE us_nombreusuario=_nombreusuario AND  us_password = _clave  ;
END;
$$ LANGUAGE plpgsql;

--Recupera la contrasena de un usuario con su correo
-- devuelve la clave del usuario
CREATE OR REPLACE FUNCTION RecuperarContrasena(_nombreUsuario varchar, _correo varchar)
RETURNS TABLE(clave varchar)
AS $$
DECLARE clave VARCHAR(20);
BEGIN

	RETURN QUERY SELECT
		us_password
	FROM usuario WHERE us_nombreUsuario = _nombreUsuario OR us_email=_correo;

END;
$$ LANGUAGE plpgsql;

/*UPDATES ACTUALIZA EL Perfil de usuario*/
CREATE OR REPLACE FUNCTION ActualizarUsuario( _id int ,_nombreUsuario VARCHAR(20), _nombre VARCHAR(30),
 _apellido VARCHAR(30), _fechaNacimiento date, _correo VARCHAR(30),
 _clave VARCHAR(20))
RETURNS void AS
$$
BEGIN
	UPDATE usuario SET us_nombreusuario = _nombreUsuario, us_nombre= _nombre ,
 us_apellido= _apellido , us_fechanacimiento = _fechaNacimiento , us_email=_correo ,
 us_password =_clave
	WHERE  us_id = _id;
END;
$$ LANGUAGE plpgsql;

/*UPDATES AUNMENTA NIVEL CUANDO EL USUARIO GANA*/
CREATE OR REPLACE FUNCTION AumentarNivelUsuario( _id integer)
RETURNS void AS
$$
BEGIN
	UPDATE usuario SET us_nivel_juego = us_nivel_juego +1
	WHERE  us_id = _id;
END;
$$ LANGUAGE plpgsql;

/*DELETE eliminar usuario */
--elimina USUARIOS por su id
CREATE OR REPLACE FUNCTION EliminarUsuarioPorId
(
  _id integer
)
RETURNS void AS
$$
BEGIN

    DELETE from usuario where us_id = _id;

END;
$$ LANGUAGE plpgsql;
/*-------------------------------PARA LAS IMAGENES-----------------------------------------------------------*/

CREATE TABLE imagen (
	ima_id	SERIAL PRIMARY KEY,
	ima_nombre text, 
	ima_byte bytea,
	ima_categoria varchar(100),
	ima_respuesta_correcta varchar(100)
	);

--- Procedimiento que retorna la imagen
CREATE OR REPLACE FUNCTION retornaImagen(id integer)
RETURNS TABLE
  (
   _id integer,
   _nombre text,
   _byte  bytea,
   _categoria varchar,
   _respuesta_correcta varchar
   )
AS
$$
BEGIN
	RETURN QUERY SELECT
	ima_id,ima_nombre,ima_byte,ima_categoria,ima_respuesta_correcta
	FROM imagen
	WHERE ima_id = id ;
END;
$$ LANGUAGE plpgsql;
	