package com.educacionit.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.educacionit.utilidades.Fechas;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class Usuario {
	private Long id;
	private String nombre;
	private String apellido;
	private Documento documento;
	private String correo;
	private String clave;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Fechas.PATRON_FECHA_YYYYMMDD)
	private LocalDate fechaNacimiento;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Fechas.PATRON_FECHA_HORA_YYYYMMDD_HHMMSS)
	private LocalDateTime fechaActualizacion;
	private List<Direccion> direcciones;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nombre, String apellido, Documento documento, String correo, String clave,
			LocalDate fechaNacimiento, LocalDateTime fechaActualizacion, List<Direccion> direcciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.correo = correo;
		this.clave = clave;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaActualizacion = fechaActualizacion;
		this.direcciones = direcciones;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
				+ ", correo=" + correo + ", clave=" + clave + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaActualizacion=" + fechaActualizacion + ", direcciones=" + direcciones + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

}
