package com.educacionit.implementaciones;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.educacionit.utilidades.EsquemaBase64;

public class ConexionMariaDB {
	private String KEY;
	private Connection conexion;
	

	public Connection getConexion() {

		// no volvemos a instanciar
		if (null != conexion) {
			return conexion;
		}

		// instanciamos el objeto
		Properties propiedades = new Properties();
		try {
			InputStream archivoPropiedades = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("database.properties");
			// cargamos el archivo utilizando la ruta relativa donde esta el proyecto
			// propiedades.load(new FileInputStream("resources/database.properties"));
			propiedades.load(archivoPropiedades);

			// leemos las propiedades
			String DRIVER = propiedades.getProperty("db.driver");
			String URL = propiedades.getProperty("db.url");
			String USER = propiedades.getProperty("db.user", "root");
			String PASS = propiedades.getProperty("db.pass");
			KEY = EsquemaBase64.decodificar(propiedades.getProperty("db.key"));

			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conexion;
	}

	public String getKEY() {
		return KEY;
	}

}
