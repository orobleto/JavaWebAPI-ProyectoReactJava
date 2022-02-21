package com.educacionit.configuraciones;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/servicios")
public class Configuracion extends ResourceConfig {

	public Configuracion() {
		packages("com.educacionit");
	}

}
