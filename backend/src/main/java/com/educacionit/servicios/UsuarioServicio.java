package com.educacionit.servicios;

import java.util.Arrays;
import java.util.List;

import com.educacionit.entidades.Direccion;
import com.educacionit.entidades.Ubicacion;
import com.educacionit.entidades.Usuario;
import com.educacionit.utilidades.Fechas;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioServicio {

	@GET
	@Path("/usuario")
	public Usuario getUsuario() {
		List<Direccion> direcciones = Arrays
				.asList(new Direccion("CABA", "Cesar Diaz", 2647, "1416", new Ubicacion(-37.3159, 81.1496)));

		Usuario usuario = new Usuario(2, "Octavio", "Robleto", "octavio.robleto@gmail.com", "1234",
				Fechas.getLocalDate("1983-03-15"), Fechas.getLocalDateTime("2021-02-01 05:03:02"), direcciones);

		System.out.println(usuario);

		return usuario;
	}

}
