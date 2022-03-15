package com.educacionit.controladores;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.educacionit.conexiones.ConexionMariaDB;
import com.educacionit.entidades.Usuario;
import com.educacionit.mapeadores.UsuarioMapper;

@Controller
public class Usuariocontrolador {
	// modelo que contiene los objetos y la vista
	private ModelAndView mav = new ModelAndView();
	private JdbcTemplate jdbcT = new JdbcTemplate(new ConexionMariaDB().getConexion());

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView getListar() {
		getMav().addObject("usuarios", getUsuarios());
		getMav().setViewName("listaUsuarios");
		// listar todos los usuarios jdbcTemplate
		return getMav();
	}

	private List<Usuario> getUsuarios() {
		List<Usuario> usuarios = null;
		String query = "SELECT nombre, apellido, tipoDocumento, numeroDocumento, correo, AES_DECRYPT(clave, 'JavaWebAPI_proyecto') as clave FROM usuarios";

		usuarios = jdbcT.query(query, new UsuarioMapper());
		System.out.println(usuarios);
		return usuarios;
	}

	@RequestMapping(value = "/insertar.html", method = RequestMethod.POST)
	public ModelAndView insertar(Usuario usuario) {
		String query = "insert into usuarios (nombre, apellido, tipoDocumento, numeroDocumento, correo, clave) values (?, ?, ?, ?, ?, AES_ENCRYPT(?, 'JavaWebAPI_proyecto')) ";
		jdbcT.update(query, usuario.getNombre(), usuario.getApellido(), usuario.getTipoDocumento(),
				usuario.getNumeroDocumento(), usuario.getCorreo(), usuario.getClave());

		return getListar();
	}

	@RequestMapping(value = "/editar.html", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		String tipoDocumento = request.getParameter("tipoDocumento");
		String numeroDodcumento = request.getParameter("numeroDocumento");
		String query = "SELECT nombre, apellido, tipoDocumento, numeroDocumento, correo,CAST( AES_DECRYPT(clave, 'JavaWebAPI_proyecto') AS VARCHAR(100))as clave FROM usuarios where tipoDocumento = ? and numeroDocumento = ?";
		List<Map<String, Object>> usuarios = jdbcT.queryForList(query, tipoDocumento, numeroDodcumento);
		getMav().addObject("usuarios", usuarios);
		getMav().setViewName("modificar");
		return getMav();
	}

	public ModelAndView getMav() {
		return mav;
	}

	public void setMav(ModelAndView mav) {
		this.mav = mav;
	}

}
