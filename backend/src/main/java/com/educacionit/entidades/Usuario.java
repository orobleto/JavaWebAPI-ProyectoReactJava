package com.educacionit.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Usuario {
	private Integer id;
	private String nombre;
	private String apellido;
	private String correo;
	private String clave;
	private LocalDate fechaNacimiento;
	private LocalDateTime fechaActualizacion;

	private List<Direccion> direcciones;
	private static AtomicInteger contador = new AtomicInteger(1);

	public Usuario() {
		super();
		this.id = contador.getAndIncrement();
	}

	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @param clave
	 * @param fechaNacimiento
	 * @param fechaActualizacion
	 * @param direcciones
	 */
	public Usuario(Integer id, String nombre, String apellido, String correo, String clave, LocalDate fechaNacimiento,
			LocalDateTime fechaActualizacion, List<Direccion> direcciones) {
		super();
		this.id = null == id ? contador.getAndIncrement() : id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.clave = clave;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaActualizacion = fechaActualizacion;
		this.direcciones = direcciones;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", clave="
				+ clave + ", fechaNacimiento=" + fechaNacimiento + ", fechaActualizacion=" + fechaActualizacion
				+ ", direcciones=" + direcciones + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
