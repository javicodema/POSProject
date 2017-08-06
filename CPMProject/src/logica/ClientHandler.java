package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ClientHandler {
	
	
	private List<Cliente> registered = new ArrayList<Cliente>();
	private Cliente userCurrent = null;
	
	public ClientHandler(){
		
	}
	
	public void leerFichero(){
		String linea = "";
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("files/clientes.dat"));
			while (fichero.ready()) {
				linea = fichero.readLine();
				String[] info = linea.split(";");
				registered.add(new Cliente(info[0],info[1],info[2],info[3],info[4],info[5],info[6],Integer.parseInt(info[7])));
			}
			fichero.close();
		}
		catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(null,"El archivo no se ha encontrado");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}
	
	public int registrarUsuario(Cliente user) {
		registered.add(user);
		String nombreFichero = "files/clientes.dat";
		String linea="\n"+user.getName()+";"+user.getSurname()+";"+user.getNif()+";"+user.getUser()+";"+user.getPassword()
		+";"+user.getPhone()+";"+user.getBankAccount()+";"+user.getPuntos();
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero,true));
			fichero.write(linea);
			fichero.close();
			return(0);
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
			return(-1);
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
			return(-2);
		}
	}

	public List<Cliente> getRegister() {
		return registered;
	}

	public Cliente existeUsuario(String string, String string2) {
		for(Cliente c:registered){
			if(c.getUser().equals(string)&&c.getPassword().equals(string2)) return c;
		}
		return null;
	}

	public String registroCorrecto(String[] lista) {
		String charr="[A-Za-z]";
		String num = "[0-9]";
		String both = "[0-9A-Za-z]";
		String nifExp = num+"{8}"+charr;
		String passExp = both+"{8,}";
		String userExp = both+"{1,20}";
		String phoneExp = num +"{9}";
		
		String name = lista[0];
		String surn = lista[1];
		String user = lista[2];
		String nif = lista[3];
		String phone = lista[4];
		String pass = lista[5];
		String passR=lista[6];
		String bank =lista[7];
		if(!pass.equals(passR)) return "The passwords does not match";
		for(Cliente c:registered){
			if(c.getUser().equals(user)) return "This username is already used";
			if(c.getNif().equals(nif)) return "You already have an account in our system";
		}
		if(!nif.matches(nifExp)) return "Wrong NIF format";
		if(!pass.matches(passExp)||!pass.matches(both+"*"+num+both+"*")||!pass.matches(both+"*"+charr+both+"*"))
			return "Wrong password format. Must contain at least one digit and"
				+ " one letter and have at least 8 characters(no special characters accepted)";
		if(!user.matches(userExp)) return "Wrong username. Can have at most 20 alphanumeric characters"; 
		if(!phone.matches(phoneExp)) return "Wrong phone number format. Can only have 9 digits";
		
		userCurrent = new Cliente(name,surn,nif,user,pass,phone,bank,0);
		if(!user.equals("sampletext"))registrarUsuario(userCurrent);
		return "";
	}

	public Cliente getUserCurrent() {
		return userCurrent;
	}

	public void actualizar(Cliente user,int puntos) {
		String nombreFichero = "files/clientes.dat";
		
		String linea="";
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero));
			for(Cliente cliente : registered){
				if(cliente.getNif().equals(user.getNif())){
					cliente.setPuntos(puntos);
				}
				linea=cliente.getName()+";"+cliente.getSurname()+";"+cliente.getNif()+";"+cliente.getUser()+
						";"+cliente.getPassword()+";"+cliente.getPhone()+";"
						+cliente.getBankAccount()+";"+cliente.getPuntos()+"\n";
				fichero.write(linea);
			}
			fichero.close();
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		
	}
	
	
}
