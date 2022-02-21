package com.educacionit.servicios;

import java.util.List;

import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioServicio {
	UsuarioImplementacion usuarioImplementacion = new UsuarioImplementacion();

	@GET
	@Path("/listar")
	public List<Usuario> getUsuario() {

		return usuarioImplementacion.listar(null);
	}

}
