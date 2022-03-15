package com.educacionit.mapeadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.educacionit.entidades.Usuario;

public class UsuarioMapper implements RowMapper<Usuario> {

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setNombre(rs.getString("nombre"));
		usuario.setApellido(rs.getString("apellido"));
		usuario.setTipoDocumento(rs.getString("tipoDocumento"));
		usuario.setNumeroDocumento(rs.getString("numeroDocumento"));
		usuario.setCorreo(rs.getString("correo"));
		usuario.setClave(rs.getString("clave"));
		return usuario;
	}

}
