package com.educacionit.implementaciones.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
	private PreparedStatement psEliminar;
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
		String sql = "select nombre, apellido, tipoDocumento, numeroDocumento, correo, AES_DECRYPT(clave, ?) as clave, fechaNacimiento, fechaActualizacion from usuarios where id = ?;";

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
				usuario.setClave(resultado.getString("clave"));
				usuario.setFechaNacimiento(Fechas.getLocalDate(resultado.getString("fechaNacimiento")));
				usuario.setFechaActualizacion(Fechas.getLocalDateTime(resultado.getString("fechaActualizacion")));
				usuario.setDirecciones(direccionImplementacion.listarPorUsuario(id));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public boolean insertar(Usuario usuario) throws SQLException {
		boolean inserto = false;
		String sql = "insert into usuarios (nombre, apellido, tipoDocumento, numeroDocumento, correo, clave, fechaNacimiento, fechaActualizacion) values (?, ?, ?, ?, ?, AES_ENCRYPT(?, ?), ?, ?)";

		if (null == psInsertar) {
			// el segundo parametro indica qupodemos recuperar el
			// ID autoincrementable generado por MariaDB
			psInsertar = conexionMariaDB.getConexion().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		}
		psInsertar.setString(1, usuario.getNombre());
		psInsertar.setString(2, usuario.getApellido());
		psInsertar.setString(3, usuario.getDocumento().getTipo());
		psInsertar.setString(4, usuario.getDocumento().getNumero());
		psInsertar.setString(5, usuario.getCorreo());
		psInsertar.setString(6, usuario.getClave());
		psInsertar.setString(7, conexionMariaDB.getKEY());
		psInsertar.setString(8, Fechas.getStringLocalDate(usuario.getFechaNacimiento()));
		psInsertar.setString(9, Fechas.getStringLocalDateTime(LocalDateTime.now()));

		inserto = psInsertar.executeUpdate() == 1;

		ResultSet auto_incremental = psInsertar.getGeneratedKeys();

		// recuperamos el ID autoincrementable
		if (auto_incremental.next()) {
			usuario.setId(auto_incremental.getLong(1));
		}

		if (inserto) {
			if (null != usuario.getDirecciones() || !(usuario.getDirecciones().size() > 0)) {

				for (Direccion direccion : usuario.getDirecciones()) {
					direccion.setIdUsuario(usuario.getId());
					direccionImplementacion.insertar(direccion);
				}
			}
		}

		return inserto;
	}

	@Override
	public boolean eliminar(Usuario usuario) throws SQLException {
		String sql = "delete from usuarios where id = ? ";

		if (null == psEliminar) {
			psEliminar = conexionMariaDB.getConexion().prepareStatement(sql);
		}
		psEliminar.setLong(1, usuario.getId());

		// eliminamos primero las direcciones del usuario
		direccionImplementacion.eliminarPorUsuario(usuario.getId());

		return psEliminar.executeUpdate() == 1;
	}

	@Override
	public boolean actualizar(Usuario usuario) throws SQLException {
		String sql = "update usuarios set  nombre = ?, apellido = ?, tipoDocumento = ?, numeroDocumento = ?, correo = ?, clave = AES_ENCRYPT(?, ?), fechaNacimiento = ?, fechaActualizacion = ? where id = ?";

		if (null == psActualizar) {
			psActualizar = conexionMariaDB.getConexion().prepareStatement(sql);
		}

		psActualizar.setString(1, usuario.getNombre());
		psActualizar.setString(2, usuario.getApellido());
		psActualizar.setString(3, usuario.getDocumento().getTipo());
		psActualizar.setString(4, usuario.getDocumento().getNumero());
		psActualizar.setString(5, usuario.getCorreo());
		psActualizar.setString(6, usuario.getClave());
		psActualizar.setString(7, conexionMariaDB.getKEY());
		psActualizar.setString(8, Fechas.getStringLocalDate(usuario.getFechaNacimiento()));
		// actualizamos la fecha a la hora de la ejecucion
		usuario.setFechaActualizacion(LocalDateTime.now());
		psActualizar.setString(9, Fechas.getStringLocalDateTime(usuario.getFechaActualizacion()));
		psActualizar.setLong(10, usuario.getId());

		if (null != usuario.getDirecciones() || !(usuario.getDirecciones().size() > 0)) {
			for (Direccion direccion : usuario.getDirecciones()) {
				direccionImplementacion.actualizar(direccion);
			}
		}

		return psActualizar.executeUpdate() == 1;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();

		String sql = "select id, nombre, apellido, tipoDocumento, numeroDocumento, correo, AES_DECRYPT(clave, ?) as clave, fechaNacimiento, fechaActualizacion from usuarios";

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
				usuario.setClave(resultado.getString("clave"));
				usuario.setFechaNacimiento(Fechas.getLocalDate(resultado.getString("fechaNacimiento")));
				usuario.setFechaActualizacion(Fechas.getLocalDateTime(resultado.getString("fechaActualizacion")));
				usuario.setDirecciones(direccionImplementacion.listarPorUsuario(resultado.getLong("id")));
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
