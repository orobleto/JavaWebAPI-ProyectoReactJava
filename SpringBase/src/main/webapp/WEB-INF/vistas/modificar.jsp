<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div>
		<div>${usuarios[0].nombre}</div>
		<div>${usuarios[0].apellido}</div>
		<div>${usuarios[0].tipoDocumento}</div>
		<div>${usuarios[0].numeroDocumento}</div>
		<div>${usuarios[0].correo}</div>
		<div>${usuarios[0].clave}</div>
	</div>

</body>
</html>