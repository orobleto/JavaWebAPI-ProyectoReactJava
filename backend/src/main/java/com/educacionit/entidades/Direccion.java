package com.educacionit.entidades;

public final class Direccion {
	private String ciudad;
	private String calle;
	private Integer numero;
	private String codigoPostal;
	private Ubicacion ubicacion;

	public Direccion() {

	}

	public Direccion(String ciudad, String calle, Integer numero, String codigoPostal, Ubicacion ubicacion) {
		super();
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Direccion [ciudad=" + ciudad + ", calle=" + calle + ", numero=" + numero + ", codigoPostal="
				+ codigoPostal + ", ubicacion=" + ubicacion + "]";
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

}