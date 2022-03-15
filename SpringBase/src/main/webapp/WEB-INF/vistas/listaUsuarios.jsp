<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Usuarios</title>
</head>
<body>
	<div>Lista de Usuarios</div>
	<table id="table-1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Tipo Documento</th>
				<th>Documento</th>
				<th>Correo</th>
				<th>Clave</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td>${usuario.nombre}</td>
					<td>${usuario.apellido}</td>
					<td>${usuario.tipoDocumento}</td>
					<td>${usuario.numeroDocumento}</td>
					<td>${usuario.correo}</td>
					<td>${usuario.clave}</td>
					<td><a
						href="editar.html?tipoDocumento=${usuario.tipoDocumento}&numeroDocumento=${usuario.numeroDocumento}">Editar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>


</body>
</html>