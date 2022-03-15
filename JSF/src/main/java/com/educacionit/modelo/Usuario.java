package com.educacionit.modelo;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String correo;
	private String clave;
	private Date fechaCreacion = new Date();

	public Usuario() {

	}

	public Usuario(String correo, String clave) {
		super();
		this.correo = correo;
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Usuario [correo=" + correo + ", clave=" + clave + "]";
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
