package com.educacionit.implementaciones.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.entidades.Direccion;
import com.educacionit.entidades.Ubicacion;
import com.educacionit.implementaciones.ConexionMariaDB;
import com.educacionit.interfaces.DAO;

public class DireccionImplementacion implements DAO<Direccion, Long> {
	private PreparedStatement psInsertar;
	private PreparedStatement psBuscar;
	private PreparedStatement psActualizar;
	private PreparedStatement psEliminar;
	private ConexionMariaDB conexionMariaDB;

	@Override
	public Direccion buscarPorID(Long id) {
		Direccion direccion = null;
		String sql = "select id_usuario, ciudad, calle, numero, codigoPostal, longitud, latitud from direcciones where id = ?;";

		try {
			if (null == psBuscar) {
				psBuscar = conexionMariaDB.getConexion().prepareStatement(sql);
			}

			psBuscar.setLong(1, id);

			ResultSet resultado = psBuscar.executeQuery();

			if (resultado.next()) {
				direccion = new Direccion();
				direccion.setId(id);
				direccion.setIdUsuario(resultado.getLong("id_usuario"));
				direccion.setCiudad(resultado.getString("ciudad"));
				direccion.setCalle(resultado.getString("calle"));
				direccion.setNumero(resultado.getInt("numero"));
				direccion.setCodigoPostal(resultado.getString("codigoPostal"));
				direccion.setUbicacion(new Ubicacion(resultado.getDouble("longitud"), resultado.getDouble("latitud")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return direccion;
	}

	@Override
	public boolean insertar(Direccion direccion) throws SQLException {
		boolean inserto = false;
		String sql = "insert into direcciones (id_usuario, ciudad, calle, numero, codigoPostal, longitud, latitud) values (?, ?, ?, ?, ?, ?, ?);";

		if (null == psInsertar) {
			// el segundo parametro indica qupodemos recuperar el
			// ID autoincrementable generado por MariaDB
			psInsertar = conexionMariaDB.getConexion().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		}

		psInsertar.setLong(1, direccion.getIdUsuario());
		psInsertar.setString(2, direccion.getCiudad());
		psInsertar.setString(3, direccion.getCalle());
		psInsertar.setInt(4, direccion.getNumero());
		psInsertar.setString(5, direccion.getCodigoPostal());
		psInsertar.setDouble(6, direccion.getUbicacion().getLongitud());
		psInsertar.setDouble(7, direccion.getUbicacion().getLatitud());

		inserto = psInsertar.executeUpdate() == 1;

		// recuperamos el ID autoincrementable
		ResultSet auto_incremental = psInsertar.getGeneratedKeys();

		if (auto_incremental.next()) {
			direccion.setId(auto_incremental.getLong(1));
		}

		return inserto;
	}

	@Override
	public boolean eliminar(Direccion direccion) throws SQLException {
		String sql = "delete from direcciones where id = ? ";

		if (null == psEliminar) {
			psEliminar = conexionMariaDB.getConexion().prepareStatement(sql);
		}
		psEliminar.setLong(1, direccion.getId());

		return psEliminar.executeUpdate() == 1;
	}
	
	public boolean eliminarPorUsuario(long id) throws SQLException {
		String sql = "delete from direcciones where id_usuario = ? ";

		if (null == psEliminar) {
			psEliminar = conexionMariaDB.getConexion().prepareStatement(sql);
		}
		psEliminar.setLong(1, id);

		return psEliminar.executeUpdate() == 1;
	}

	@Override
	public boolean actualizar(Direccion direccion) throws SQLException {
		String sql = "update direcciones set ciudad = ?, calle = ?, numero = ?, codigoPostal = ?, longitud = ?, latitud = ? where id = ? ";

		if (null == psActualizar) {
			psActualizar = conexionMariaDB.getConexion().prepareStatement(sql);
		}

		psActualizar.setString(1, direccion.getCiudad());
		psActualizar.setString(2, direccion.getCalle());
		psActualizar.setInt(3, direccion.getNumero());
		psActualizar.setString(4, direccion.getCodigoPostal());
		psActualizar.setDouble(5, direccion.getUbicacion().getLongitud());
		psActualizar.setDouble(6, direccion.getUbicacion().getLatitud());
		psActualizar.setLong(7, direccion.getId());

		return psActualizar.executeUpdate() == 1;
	}

	@Override
	public List<Direccion> listar() throws SQLException {
		List<Direccion> direcciones = new ArrayList<>();

		String sql = "select id, id_usuario, ciudad, calle, numero, codigoPostal, longitud, latitud from direcciones where id_usuario;";

		if (null == psBuscar) {
			psBuscar = conexionMariaDB.getConexion().prepareStatement(sql);
		}

		ResultSet resultado = psBuscar.executeQuery();

		while (resultado.next()) {
			Direccion direccion = new Direccion();
			direccion.setId(resultado.getLong("id"));
			direccion.setIdUsuario(resultado.getLong("id_usuario"));
			direccion.setCiudad(resultado.getString("ciudad"));
			direccion.setCalle(resultado.getString("calle"));
			direccion.setNumero(resultado.getInt("numero"));
			direccion.setCodigoPostal(resultado.getString("codigoPostal"));
			direccion.setUbicacion(new Ubicacion(resultado.getDouble("longitud"), resultado.getDouble("latitud")));
			direcciones.add(direccion);
		}

		return direcciones;
	}

	public List<Direccion> listarPorUsuario(long idUsuario) throws SQLException {
		List<Direccion> direcciones = new ArrayList<>();

		String sql = "select id, id_usuario, ciudad, calle, numero, codigoPostal, longitud, latitud from direcciones where id_usuario = ?;";

		if (null == psBuscar) {
			psBuscar = conexionMariaDB.getConexion().prepareStatement(sql);
		}

		psBuscar.setLong(1, idUsuario);

		ResultSet resultado = psBuscar.executeQuery();

		while (resultado.next()) {
			Direccion direccion = new Direccion();
			direccion.setId(resultado.getLong("id"));
			direccion.setIdUsuario(idUsuario);
			direccion.setCiudad(resultado.getString("ciudad"));
			direccion.setCalle(resultado.getString("calle"));
			direccion.setNumero(resultado.getInt("numero"));
			direccion.setCodigoPostal(resultado.getString("codigoPostal"));
			direccion.setUbicacion(new Ubicacion(resultado.getDouble("longitud"), resultado.getDouble("latitud")));
			direcciones.add(direccion);
		}

		return direcciones;
	}

	public ConexionMariaDB getConexionMariaDB() {
		return conexionMariaDB;
	}

	public void setConexionMariaDB(ConexionMariaDB conexionMariaDB) {
		this.conexionMariaDB = conexionMariaDB;
	}

}
