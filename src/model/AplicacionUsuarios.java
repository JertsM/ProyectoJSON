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

import java.io.*;
import java.util.Iterator;

public class AplicacionUsuarios implements Serializable{

	private static final String RUTA_FICHERO1 = "./src/ficheroJSON.json";
	private static final String RUTA_FICHERO = "." + File.separator + "src" + File.separator+ "model" + File.separator + "ficheroJSON.json";

	static JSONParser parser = new JSONParser();
	static Object obj;

	/*static {
		try {
			obj = parser.parse(new FileReader(RUTA_FICHERO1));
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
    }*/

	static JSONObject jsonObject = (JSONObject) obj;

	static JSONArray user = new JSONArray();

	public void crearFicheroJson() {
		File ficheroJSON = new File(RUTA_FICHERO1);
		try{
			if(!ficheroJSON.exists()){
				System.out.println("El archivo se ha creado correctamente.");
				ficheroJSON.createNewFile();
			}else{
				System.err.println("No se ha podido crear el archivo.");
				FileInputStream fis = new FileInputStream(RUTA_FICHERO1);
				DataInputStream dis = new DataInputStream(fis);
				dis.read();
			}
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	private JSONArray obtenerUsuariosJson() {

		try {
			user = (JSONArray) jsonObject.get("usuario");
			System.out.println("Usuarios:");
			Iterator iterator = user.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
	}

	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {
		int pos = 0;
		try {
			Object obj = parser.parse(new FileReader(RUTA_FICHERO));
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
		if(jsonObject.isNull(nombreUsuario)){
			return null;
		} else{
			return jsonObject;
		}
	}

        public void ejecutar() {
		VentanaInicioSesion miVentana = new VentanaInicioSesion(new AplicacionUsuarios());
		miVentana.setVisible(true);
	}

	public void iniciarSesion(String nombreUsuario, String contraseñaUsuario) {
		jsonObject = obtenerUsuarioJson(nombreUsuario);
		if(jsonObject.get("contraseña") == contraseñaUsuario){
			mostrarVentanaMenuUsuario(nombreUsuario);
		}

	}

	public void cerrarSesion() {
		ejecutar();
	}

	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {
		jsonObject = new JSONObject();
		jsonObject.put("nombre", nombre);
		jsonObject.put("contraseña", contraseña);
		jsonObject.put("edad", edad);
		jsonObject.put("correo", correo);
		user.put(jsonObject);

	}

	public void cambiarContraseña(String nombreUsuario, String nuevaContraseña) {
		obtenerPosicionUsuario(nombreUsuario, user);
		jsonObject = obtenerUsuarioJson(nombreUsuario);
				jsonObject.put("nueva contraseña",  nuevaContraseña);
			}

	public void borrarUsuario(String nombreUsuario) {
		user.remove(obtenerPosicionUsuario(nombreUsuario, user));
	}

	public void mostrarVentanaCrearUsuario() {
		VentanaCrearUsuario miVentana = new VentanaCrearUsuario(new AplicacionUsuarios());
		miVentana.setVisible(true);
	}

	public void mostrarVentanaVerUsuario(String nombreUsuario) {
		VentanaVerUsuario miVentana = new VentanaVerUsuario(new AplicacionUsuarios(), nombreUsuario, "", "");
		miVentana.setVisible(true);
	}

	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {
		VentanaCambiarContraseña miVentana = new VentanaCambiarContraseña(new AplicacionUsuarios(), nombreUsuario);
		miVentana.setVisible(true);
	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {
		VentanaBorrarUsuario miVentana = new VentanaBorrarUsuario(new AplicacionUsuarios(), nombreUsuario);
	}

	public void  mostrarVentanaMenuUsuario(String nombreUsuario){
		VentanaMenuUsuario miVentana = new VentanaMenuUsuario(new AplicacionUsuarios(), nombreUsuario);
		miVentana.setVisible(true);
	}
}