package com.educacionit.implementaciones.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.entidades.Direccion;
import com.educacionit.entidades.Documento;
import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.ConexionMariaDB;
import com.educacionit.interfaces.DAO;
import com.educacionit.utilidades.Fechas;

public class UsuarioImplementacion implements DAO<Usuario, Long> {
	private PreparedStatement psInsertar;
	private PreparedStatement psBuscar;
	private PreparedStatement psActualizar;
	private PreparedStatement pseliminar;
	private ConexionMariaDB conexionMariaDB;
	private DireccionImplementacion direccionImplementacion;

	public UsuarioImplementacion() {
		conexionMariaDB = new ConexionMariaDB();
		direccionImplementacion = new DireccionImplementacion();
		direccionImplementacion.setConexionMariaDB(conexionMariaDB);
	}

	@Override
	public Usuario buscarPorID(Long id) {
		Usuario usuario = null;
		String sql = "select nombre, apellido, tipoDocumento, numeroDocumento, correo, AES_ENCRYPT(clave, ?) as clave, fechaNacimiento, fechaActualizacion from usuarios where id = ?;";

		try {
			if (null == psBuscar) {
				psBuscar = conexionMariaDB.getConexion().prepareStatement(sql);
			}

			psBuscar.setString(1, conexionMariaDB.getKEY());
			psBuscar.setLong(2, id);

			ResultSet resultado = psBuscar.executeQuery();

			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setId(id);
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellido(resultado.getString("apellido"));
				usuario.setDocumento(
						new Documento(resultado.getString("tipoDocumento"), resultado.getString("numeroDocumento")));
				usuario.setCorreo(resultado.getString("correo"));
				usuario.setFechaNacimiento(Fechas.getLocalDate(resultado.getString("fechaNacimiento")));
				usuario.setFechaActualizacion(Fechas.getLocalDateTime(resultado.getString("fechaActualizacion")));
				usuario.setDirecciones(direccionImplementacion.listar(id));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public boolean insertar(Usuario usuario) {
		boolean inserto = false;
		String sql = "insert into usuarios (nombre, apellido, tipoDocumento, numeroDocumento, correo, clave, fechaNacimiento, fechaActualizacion) values (?, ?, ?, ?, ?, AES_ENCRYPT(?, ?), ?, ?)";

		try {
			if (null == psInsertar) {
				psInsertar = conexionMariaDB.getConexion().prepareStatement(sql);
			}
			psInsertar.setString(1, usuario.getNombre());
			psInsertar.setString(2, usuario.getApellido());
			psInsertar.setString(3, usuario.getDocumento().getTipo());
			psInsertar.setString(4, usuario.getDocumento().getNumero());
			psInsertar.setString(5, usuario.getCorreo());
			psInsertar.setString(6, usuario.getClave());
			psInsertar.setString(7, conexionMariaDB.getKEY());
			psInsertar.setString(8, Fechas.getStringLocalDate(usuario.getFechaNacimiento()));
			psInsertar.setString(9, Fechas.getStringLocalDateTime(usuario.getFechaActualizacion()));
			inserto = psInsertar.executeUpdate() == 1;

			if (inserto) {
				if (null != usuario.getDirecciones() || !(usuario.getDirecciones().size() > 0)) {

					for (Direccion direccion : usuario.getDirecciones()) {
						direccion.setIdUsuario(usuario.getId());
						direccionImplementacion.insertar(direccion);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return inserto;
	}

	@Override
	public boolean eliminar(Usuario usuario) {
		return false;
	}

	@Override
	public boolean actualizar(Usuario usuario) {
		return false;
	}

	@Override
	public List<Usuario> listar(Long id) {
		List<Usuario> usuarios = new ArrayList<>();

		String sql = "select id, nombre, apellido, tipoDocumento, numeroDocumento, correo, AES_ENCRYPT(clave, ?) as clave, fechaNacimiento, fechaActualizacion from usuarios";

		try {
			if (null == psBuscar) {
				psBuscar = conexionMariaDB.getConexion().prepareStatement(sql);
			}

			psBuscar.setString(1, conexionMariaDB.getKEY());

			ResultSet resultado = psBuscar.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getLong("id"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellido(resultado.getString("apellido"));
				usuario.setDocumento(
						new Documento(resultado.getString("tipoDocumento"), resultado.getString("numeroDocumento")));
				usuario.setCorreo(resultado.getString("correo"));
				usuario.setFechaNacimiento(Fechas.getLocalDate(resultado.getString("fechaNacimiento")));
				usuario.setFechaActualizacion(Fechas.getLocalDateTime(resultado.getString("fechaActualizacion")));
				usuario.setDirecciones(direccionImplementacion.listar(resultado.getLong("id")));
				usuarios.add(usuario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public ConexionMariaDB getConexionMariaDB() {
		return conexionMariaDB;
	}

	public void setConexionMariaDB(ConexionMariaDB conexionMariaDB) {
		this.conexionMariaDB = conexionMariaDB;
	}

}
