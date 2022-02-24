package com.educacionit.enumerados;

public enum CRUD {
	INSERTAR("Registro creado correctamente"), BUSCAR("Registro encontrado correctamente"),
	ACTUALIZAR("Registro actualizado correctamente"), ELIMINAR("Registro eliminado correctamente");

	private String mensaje;

	private CRUD(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
