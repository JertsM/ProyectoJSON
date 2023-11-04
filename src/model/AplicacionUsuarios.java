package model;

import gui.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;
import java.util.Iterator;

public class AplicacionUsuarios implements Serializable{

	private static final String RUTA_FICHERO1 = "./src/ficheroJSON.json";
	static JSONParser parser = new JSONParser();
	static Object obj;

	static JSONObject jsonObject = (JSONObject) obj;

	static JSONArray user;
	VentanaCrearUsuario crearUsuario;

	VentanaMenuUsuario ventanaMenuUsuario;
	VentanaVerUsuario verUsuario;
	VentanaCambiarContrasenha cambiarContra;
	VentanaBorrarUsuario borrarUsuario;

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
            e.printStackTrace();
        }
    }

	public void escribirFichero(JSONObject jsonObject, String nombreArchivo) {
		try{
			FileWriter fw = new FileWriter(RUTA_FICHERO1, true);
			jsonObject.writeJSONString(fw);
			fw.append(", " + '\n');
			fw.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void obtenerUsuariosJson(){
		try{
			FileReader fr = new FileReader(RUTA_FICHERO1);
			JSONTokener jsonTokener = new JSONTokener(fr);
			jsonObject = (JSONObject) parser.parse(String.valueOf(jsonTokener));
			user = (JSONArray) jsonObject.get("nombre");

			for(Object usuario : user){
				JSONObject usuarioObj = (JSONObject) usuario;

				String nombre = (String) usuarioObj.get("nombre");
				String edad = (String) usuarioObj.get("edad");

				System.out.println("Nombre: " + nombre);
				System.out.println("Edad: " + edad);
				System.out.println();
			}
			fr.close();
		} catch (ParseException | IOException e) {
            e.printStackTrace();
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
		if(jsonObject.get("contraseña").equals(contraseñaUsuario)){
			mostrarVentanaMenuUsuario(nombreUsuario);
		}else{
			JOptionPane.showMessageDialog(ventanaMenuUsuario, "Usuario o contraseña incorrectos.");
		}

	}

	public void cerrarSesion() {
		ejecutar();
	}

	public void crearUsuario(String nombre, String contrasenha, String edad, String correo) {
		jsonObject = new JSONObject();
		jsonObject.put("nombre", nombre);
		jsonObject.put("contraseña", contrasenha);
		jsonObject.put("edad", edad);
		jsonObject.put("correo", correo);
		user.add(jsonObject);
		escribirFichero(jsonObject, RUTA_FICHERO1);
		JOptionPane.showMessageDialog(crearUsuario, "El usuario ha sido creado con éxito.");
	}

	public void cambiarContraseña(String nombreUsuario, String nuevaContrasenha) {
		int posicion = obtenerPosicionUsuario(nombreUsuario, user);
		if(posicion != -1){
			JSONObject usuario = (JSONObject) user.get(posicion);
			usuario.put("contraseña", nuevaContrasenha);
			System.out.println("La contraseña se ha actualizado correctamente.");
		}else {
			System.out.println("El usuario introducido no existe.");
		}
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

	public void mostrarVentanaCambiarContrasenha(String nombreUsuario) {
		cambiarContra = new VentanaCambiarContrasenha(this, nombreUsuario);
		cambiarContra.setVisible(true);
	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {
		borrarUsuario = new VentanaBorrarUsuario(this, nombreUsuario);
	}

	public void  mostrarVentanaMenuUsuario(String nombreUsuario){
		ventanaMenuUsuario = new VentanaMenuUsuario(this, nombreUsuario);
		ventanaMenuUsuario.setVisible(true);
	}
}
