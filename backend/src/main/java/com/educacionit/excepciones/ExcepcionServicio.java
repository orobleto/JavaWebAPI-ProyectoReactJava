package com.educacionit.excepciones;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ExcepcionServicio implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		return Response.serverError().type(MediaType.TEXT_HTML).entity("Error del servicio" + e.getMessage()).build();
	}

}
