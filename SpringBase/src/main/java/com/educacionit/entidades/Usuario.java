package com.educacionit.entidades;

public class Usuario {
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String numeroDocumento;
	private String correo;
	private String clave;

	public Usuario() {

	}

	public Usuario(String nombre, String apellido, String tipoDocumento, String numeroDocumento, String correo,
			String clave) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.correo = correo;
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento
				+ ", numeroDocumento=" + numeroDocumento + ", correo=" + correo + ", clave=" + clave + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	// id, nombre, apellido, tipoDocumento, numeroDocumento, correo, clave,
	// fechaNacimiento, fechaActualizacion

}
