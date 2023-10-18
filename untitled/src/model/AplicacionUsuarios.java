package model;

import gui.VentanaBorrarUsuario;
import gui.VentanaCambiarContraseña;
import gui.VentanaCrearUsuario;
import gui.VentanaInicioSesion;
import gui.VentanaMenuUsuario;
import gui.VentanaVerUsuario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AplicacionUsuarios {

	private final String RUTA_FICHERO = "";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearUsuario ventanaCrearUsuario;
	private VentanaMenuUsuario ventanaMenuUsuario;
	private VentanaVerUsuario ventanaVerUsuario;
	private VentanaCambiarContraseña ventanaCambiarContraseña;
	private VentanaBorrarUsuario ventanaBorrarUsuario;

	private void crearFicheroJson() {
		File ficheroJSON = new File("./ficheroJSON.json");
		try{
			if(!ficheroJSON.exists()){
				ficheroJSON.createNewFile();
				System.out.println("El archivo se ha creado correctamente.");
			}else{
				System.err.println("No se ha podido crear el archivo.");
			}
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	private JSONArray obtenerUsuariosJson() {
		JSONParser parser = new JSONParser();
		JSONArray user;
		try {
			Object obj = parser.parse(new FileReader("./ficheroJSON.json"));
			JSONObject jsonObject = (JSONObject) obj;
			user = (JSONArray) jsonObject.get("usuario");
			System.out.println("Usuarios:");
			Iterator iterator = user.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return user;
	}

	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {
		JSONParser parser = new JSONParser();
		JSONArray user;
		int pos = 0;
		try {
			Object obj = parser.parse(new FileReader("./ficheroJSON.json"));
			JSONObject jsonObject = (JSONObject) obj;
			user = (JSONArray) jsonObject.get("usuario");

			Iterator it = user.iterator();
			while(it.hasNext()){
				if(it.next().equals(nombreUsuario)){
					pos = (int) it.next();
					System.out.println("El número de usuario es " + pos);
				}
			}

		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
		return pos;
	}

	private JSONObject obtenerUsuarioJson(String nombreUsuario) {
		return null;
	}

        public void ejecutar() {

	}

	public void iniciarSesion(String nombreUsuario, String contraseñaUsuario) {

	}

	public void cerrarSesion() {

	}

	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {

	}

	public void cambiarContraseña(String nombreUsuario, String nuevaContraseña) {

	}

	public void borrarUsuario(String nombreUsuario) {

	}

	public void mostrarVentanaCrearUsuario() {

	}

	public void mostrarVentanaVerUsuario(String nombreUsuario) {

	}

	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {

	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {

	}
}
