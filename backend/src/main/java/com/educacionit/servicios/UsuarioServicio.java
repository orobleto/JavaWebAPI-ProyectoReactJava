package com.educacionit.servicios;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.educacionit.entidades.RespuestaServicio;
import com.educacionit.entidades.Usuario;
import com.educacionit.enumerados.CRUD;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioServicio {
	UsuarioImplementacion usuarioImplementacion = new UsuarioImplementacion();
	private static Logger log = LogManager.getLogger(UsuarioServicio.class);

	@GET
	@Path("/listar")
	public List<Usuario> getUsuario() {
		List<Usuario> usuarios = usuarioImplementacion.listar();
		log.info(usuarios);
		return usuarios;
	}

	@POST
	public Response insertar(Usuario usuario) {
		return CRUD(usuario, CRUD.INSERTAR);
	}

	@PUT
	public Response actualizar(Usuario usuario) {
		return CRUD(usuario, CRUD.ACTUALIZAR);
	}

	@DELETE
	public Response eliminar(Usuario usuario) {
		return CRUD(usuario, CRUD.ELIMINAR);
	}

	// METODO GENERICO
	private Response CRUD(Usuario usuario, CRUD operacion) {

		RespuestaServicio respuesta = new RespuestaServicio();
		
		Integer codigoEstado = 201;

		try {
			switch (operacion) {
			case INSERTAR:
				usuarioImplementacion.insertar(usuario);
				break;
			case ACTUALIZAR:
				usuarioImplementacion.actualizar(usuario);
				break;
			case ELIMINAR:
				usuarioImplementacion.eliminar(usuario);	
				break;
			default:
				break;
			}

			respuesta.setEstado("OK");
			respuesta.setCodigoEstado(codigoEstado);
			respuesta.setMensaje(operacion.getMensaje());
			log.info(respuesta);

		} catch (SQLException e) {
			respuesta.setEstado("ERROR");
			respuesta.setCodigoEstado(400);
			respuesta.setMensaje(e.getMessage());
			log.error(respuesta);
		}
		respuesta.setDatos(Arrays.asList(usuarioImplementacion.buscarPorID(usuario.getId())));
		return Response.status(codigoEstado).entity(respuesta).build();
	}

}
