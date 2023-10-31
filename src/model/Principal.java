package model;

public class Principal {

	public static void main(String[] args) {
		AplicacionUsuarios u1 = new AplicacionUsuarios();
		u1.crearFicheroJson();
		u1.crearUsuario("mauri", "abc123.", "22", "mauri@gmail.com");
		u1.ejecutar();
	}
}
