package com.educacionit.servicios;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.ConexionMariaDB;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioServicio {
	UsuarioImplementacion usuarioImplementacion = new UsuarioImplementacion();
	private static Logger log = LogManager.getLogger(ConexionMariaDB.class);

	@GET
	@Path("/listar")
	public List<Usuario> getUsuario() {
		List<Usuario> usuarios =  usuarioImplementacion.listar(null);
		log.info(usuarios);
		return usuarios;
	}
	
	@POST
	public Response insertar(Usuario usuario) {
		
		if (usuarioImplementacion.insertar(usuario)) {
			log.info(usuario);
			return Response.status(203).entity(usuario).build();
		}
		
		log.error("Error");
		return Response.status(406).build();
	}

}
