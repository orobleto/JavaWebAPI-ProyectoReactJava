package com.educacionit.entidades;

import java.util.List;

public final class RespuestaServicio {
	private String estado;
	private Integer codigoEstado;
	private String mensaje;
	private List<Object> datos;

	public RespuestaServicio() {

	}

	public RespuestaServicio(String estado, Integer codigoEstado, String mensaje, List<Object> datos) {
		super();
		this.estado = estado;
		this.codigoEstado = codigoEstado;
		this.mensaje = mensaje;
		this.datos = datos;
	}

	@Override
	public String toString() {
		return "RespuestaServicio [estado=" + estado + ", codigoEstado=" + codigoEstado + ", mensaje=" + mensaje
				+ ", datos=" + datos + "]";
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<Object> getDatos() {
		return datos;
	}

	public void setDatos(List<Object> datos) {
		this.datos = datos;
	}

}