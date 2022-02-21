package com.educacionit.entidades;

public final class Ubicacion {
	private Double latitud;
	private Double longitud;

	public Ubicacion() {

	}

	public Ubicacion(Double latitud, Double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "Ubicacion [latitud=" + latitud + ", longitud=" + longitud + "]";
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

}
