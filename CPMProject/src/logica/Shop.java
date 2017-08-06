package logica;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Shop {
	
	private Cliente user = null;
	private ClientHandler ch = new ClientHandler();
	private CartHandler cah = new CartHandler(false);
	private BillGenerator b;
	private Categoria discounted = null;
	private int disc = 0;
	
	public Shop(){
		b=new BillGenerator(this);
		ch.leerFichero();
		cah.leerFichero();
		cah.leerRegalo();
		randomDiscount();
	}

	public void addUser(Cliente c) {
		user = c;
		cah.setRegistered(true);
		cah.leerFichero();
		List<Articulo> lA = cah.getCartArt();
		for(Articulo a:lA){
			if(a.getCodigo().equals("CV-PS-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("FV-CR-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("TM-XX-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("OT-SO-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("VI-CA-001")) a.setValor(a.getValor()*0.8);
		}
		cah.setCartArt(lA);
		cah.reiniciarCarro();
	}
	
	public Cliente getUser(){
		return user;
	}
	
	public void randomDiscount(){
		Categoria c = null;
		int a = (int)(Math.random()*5)+1;
		switch(a){
			case 1:
				c=Categoria.Consolas;
				break;
			case 2:
				c=Categoria.Moviles;
				break;
			case 3:
				c=Categoria.Foto;
				break;
			case 4:
				c=Categoria.PC;
				break;
			case 5:
				c=Categoria.VideoM;
				break;
		}
		discounted = c;
		cah.aplicarDescuento(c);
				
	}

	public Categoria getDiscounted() {
		return discounted;
	}

	public CartHandler getCartHandler() {
		return cah;
	}
	
	public void setDiscounted(int d){
		disc = d;
	}

	public double getDiscountedPoints() {
		return disc;
	}

	public ClientHandler getClientHandler() {
		return ch;
	}

	public void addUserinHandler() {
		user = ch.getUserCurrent();	
		cah.setRegistered(true);
		cah.leerFichero();
		List<Articulo> lA = cah.getCartArt();
		for(Articulo a:lA){
			if(a.getCodigo().equals("CV-PS-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("FV-CR-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("TM-XX-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("OT-SO-001")) a.setValor(a.getValor()*0.8);
			if(a.getCodigo().equals("VI-CA-001")) a.setValor(a.getValor()*0.8);
		}
		cah.setCartArt(lA);
		cah.reiniciarCarro();
	}

	public int getStock(Articulo page) {
		return cah.getStock(page);
	}
	
	public String getFactura(Locale localizacion){
		return b.billString(localizacion);
	}

	public void generarFactura(Locale localizacion) {
		Date fechaHora = new Date();
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG,localizacion);
		String nombreFichero = "files/"+user.getNif()+formatoFecha.format(fechaHora)+".dat";
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero));
			fichero.write(b.billString(localizacion));
			fichero.close();
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public void resetAll() {
		user = null;
		ch.leerFichero();
		randomDiscount();
		disc=0;
		cah.reiniciarArt();
		cah.reiniciarRegalos();
	}

	public void actualizarUser() {
		int finale = 0;
		finale+=user.getPuntos();
		finale+=cah.getPoints();
		finale-=disc;
		ch.actualizar(user,finale);
	}
	
	
}
