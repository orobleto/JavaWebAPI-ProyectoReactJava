
drop database if exists JavaWebAPI_proyecto;


create database if not exists JavaWebAPI_proyecto;



use JavaWebAPI_proyecto;


DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `tipoDocumento` char(3) NOT NULL,
  `numeroDocumento` varchar(20) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` blob NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `fechaActualizacion` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuarios_documento` (`numeroDocumento`,`tipoDocumento`),
  UNIQUE KEY `usuarios_correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;



LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Octavio','Robleto','DNI','01','octavio.robleto@gmail.com', AES_ENCRYPT('1234', 'JavaWebAPI_proyecto'),'1983-03-15','2021-02-01 05:03:02'),(4,'Mariana','Bracho','DNI','02','mariana.bracho@gmail.com', AES_ENCRYPT('9876', 'JavaWebAPI_proyecto'),'1989-06-06','2021-02-01 05:03:02');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direcciones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint(20) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `numero` int(11) NOT NULL,
  `codigoPostal` varchar(20) NOT NULL,
  `longitud` double DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `direcciones_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (1,1,'CABA','Cesar Diaz',2647,'1416',81.1496,-37.3159),(2,1,'CABA','Mario Bravo',1248,'1105',-58.41595896733179,-34.59269235185108),(3,4,'CABA','Colegiales',2,'25',-58.448299845460035,-34.57224675747592);
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;
