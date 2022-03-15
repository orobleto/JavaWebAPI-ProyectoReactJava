package com.educacionit.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.educacionit.modelo.Usuario;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioControlador {

	// Spring
	private Usuario usuario = new Usuario();
	public static List<Usuario> usuarios = new ArrayList<Usuario>();

	public String agregarUsuario() {
		usuarios.add(usuario);
		return "listaUsuarios";
	}

	public String eliminarUsuario(Usuario usuario) {
		usuarios.remove(usuario);
		return "listaUsuarios";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<Usuario> usuarios) {
		UsuarioControlador.usuarios = usuarios;
	}

}
