package model;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {
		AplicacionUsuarios u1 = new AplicacionUsuarios();
		u1.crearFicheroJson();
		u1.crearUsuario("", "", "", "");
		u1.ejecutar();
	}
}
