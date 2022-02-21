package com.educacionit.implementaciones.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.educacionit.entidades.Direccion;
import com.educacionit.entidades.Ubicacion;
import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.ConexionMariaDB;
import com.educacionit.interfaces.DAO;
import com.educacionit.utilidades.Fechas;

public class DireccionImplementacion implements DAO<Direccion, Long> {
	private PreparedStatement psInsertar;
	private PreparedStatement psBuscar;
	private PreparedStatement psActualizar;
	private PreparedStatement pseliminar;
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
	public boolean insertar(Direccion direccion) {
		boolean inserto = false;
		String sql = "insert into direcciones (id_usuario, ciudad, calle, numero, codigoPostal, longitud, latitud) values(?, ?, ?, ?, ?, ?, ?);";
		try {
			if (null == psInsertar) {
				psInsertar = conexionMariaDB.getConexion().prepareStatement(sql);
			}

			psInsertar.setLong(1, direccion.getIdUsuario());
			psInsertar.setString(2, direccion.getCiudad());
			psInsertar.setString(3, direccion.getCalle());
			psInsertar.setInt(4, direccion.getNumero());
			psInsertar.setString(5, direccion.getCodigoPostal());
			psInsertar.setDouble(6, direccion.getUbicacion().getLongitud());
			psInsertar.setDouble(7, direccion.getUbicacion().getLatitud());

			inserto = psInsertar.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return inserto;
	}

	@Override
	public boolean eliminar(Direccion direccion) {

		return false;
	}

	@Override
	public boolean actualizar(Direccion direccion) {

		return false;
	}

	@Override
	public List<Direccion> listar(Long idUsuario) {
		List<Direccion> direcciones = new ArrayList<>();

		String sql = "select id, id_usuario, ciudad, calle, numero, codigoPostal, longitud, latitud from direcciones where id_usuario = ?;";

		try {
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

		} catch (SQLException e) {
			e.printStackTrace();
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
