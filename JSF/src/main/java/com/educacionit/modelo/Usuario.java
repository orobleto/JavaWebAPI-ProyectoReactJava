package com.educacionit.modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

//POJO
// lombok maven


@ManagedBean
@SessionScoped
public class Usuario {
	private String correo;
	private String clave;

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

}
