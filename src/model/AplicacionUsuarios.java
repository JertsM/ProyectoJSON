package model;

import gui.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

public class AplicacionUsuarios implements Serializable{

	private static final String RUTA_FICHERO1 = "./src/ficheroJSON.json";
	static JSONParser parser = new JSONParser();
	static Object obj;

	static JSONObject jsonObject = (JSONObject) obj;

	static JSONArray user = new JSONArray();
	VentanaCrearUsuario crearUsuario;

	VentanaMenuUsuario ventanaMenuUsuario;
	VentanaVerUsuario verUsuario;
	VentanaCambiarContraseña cambiarContra;
	VentanaBorrarUsuario borrarUsuario;
	VentanaMenuUsuario ventanaMenuUsuario1;

	public void crearFicheroJson() {
		File ficheroJSON = new File(RUTA_FICHERO1);
		try{
			if(!ficheroJSON.exists()){
				System.out.println("El archivo se ha creado correctamente.");
				ficheroJSON.createNewFile();
			}else{
				user = (JSONArray) parser.parse(new FileReader(ficheroJSON));
			}
		} catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {
		int pos = 0;
		Iterator it = user.iterator();
		while(it.hasNext()){
			JSONObject obj = (JSONObject) it.next();
			if(obj.get("nombre").equals(nombreUsuario)){
				System.out.println("El número de usuario es " + pos);
				return pos;
			}
			pos++;
		}
		return -1;
	}

	private JSONObject obtenerUsuarioJson(String nombreUsuario) {
		int posicion = obtenerPosicionUsuario(nombreUsuario, user);
		if(posicion == -1)
			return null;
		return (JSONObject) user.get(obtenerPosicionUsuario(nombreUsuario, user));
	}

        public void ejecutar() {
		VentanaInicioSesion miVentana = new VentanaInicioSesion(new AplicacionUsuarios());
		miVentana.setVisible(true);
	}

	public void iniciarSesion(String nombreUsuario, String contraseñaUsuario) {
		jsonObject = obtenerUsuarioJson(nombreUsuario);
		if(jsonObject.get("contraseña") == contraseñaUsuario){
			mostrarVentanaMenuUsuario(nombreUsuario);
		}else{
			JOptionPane.showMessageDialog(ventanaMenuUsuario, "No se ha encontrado");
		}

	}

	public void cerrarSesion() {
		ejecutar();
	}

	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {
		user = new JSONArray();
		jsonObject = new JSONObject();
		jsonObject.put("nombre", nombre);
		jsonObject.put("contraseña", contraseña);
		jsonObject.put("edad", edad);
		jsonObject.put("correo", correo);
		user.add(jsonObject);

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
		crearUsuario = new VentanaCrearUsuario(this);
		crearUsuario.setVisible(true);
	}

	public void mostrarVentanaVerUsuario(String nombreUsuario) {
		verUsuario = new VentanaVerUsuario(this, nombreUsuario, "", "");
		verUsuario.setVisible(true);
	}

	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {
		cambiarContra = new VentanaCambiarContraseña(this, nombreUsuario);
		cambiarContra.setVisible(true);
	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {
		borrarUsuario = new VentanaBorrarUsuario(this, nombreUsuario);
	}

	public void  mostrarVentanaMenuUsuario(String nombreUsuario){
		ventanaMenuUsuario1 = new VentanaMenuUsuario(this, nombreUsuario);
		ventanaMenuUsuario1.setVisible(true);
	}
}
