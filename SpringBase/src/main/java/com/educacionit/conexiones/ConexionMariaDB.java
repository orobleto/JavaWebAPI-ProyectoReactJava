package com.educacionit.conexiones;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConexionMariaDB {

	public DriverManagerDataSource getConexion() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.mariadb.jdbc.Driver");
		driver.setUrl("jdbc:mariadb://localhost:3306/JavaWebAPI_proyecto");
		driver.setUsername("root");
		driver.setPassword("");
		return driver;
	}
}
