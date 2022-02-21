import java.util.Arrays;
import java.util.List;

import com.educacionit.entidades.Direccion;
import com.educacionit.entidades.Documento;
import com.educacionit.entidades.Ubicacion;
import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;
import com.educacionit.utilidades.Fechas;

public class App {
	public static void main(String[] args) {
		/*
		List<Direccion> direcciones = Arrays
				.asList(new Direccion("CABA", "Cesar Diaz", 2647, "1416", new Ubicacion(-37.3159, 81.1496)),
						new Direccion("CABA", "Mario Bravo", 1248, "1105", new Ubicacion(-34.59269235185108, -58.41595896733179)));
						
		
		List<Direccion> direcciones = Arrays
				.asList(new Direccion("CABA", "Colegiales", 2, "25", new Ubicacion(-34.57224675747592, -58.448299845460035)));

		Usuario usuario = new Usuario(1L, "Mariana", "Bracho",new Documento("DNI","02"), "mariana.bracho@gmail.com", "65478",
				Fechas.getLocalDate("1989-06-06"), Fechas.getLocalDateTime("2021-02-01 05:03:02"), direcciones);

		System.out.println(usuario);
		
		
		UsuarioImplementacion implementacion =  new UsuarioImplementacion();
		implementacion.insertar(usuario);*/
		
		UsuarioImplementacion implementacion =  new UsuarioImplementacion();
		
		for (Usuario usuario : implementacion.listar(null)) {
			System.out.println(usuario);
		}
		
	}
}
