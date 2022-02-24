package com.educacionit.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<E, K> {

	E buscarPorID(K key) throws SQLException;

	boolean insertar(E elemento) throws SQLException;

	boolean eliminar(E elemento) throws SQLException;

	boolean actualizar(E elemento) throws SQLException;

	List<E> listar() throws SQLException;

}