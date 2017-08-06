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

public class CartHandler {
	
	private List<Articulo> totalArt = new ArrayList<Articulo>();
	private List<Regalo> totalRegalos = new ArrayList<Regalo>();
	private List<Articulo> cartArt = new ArrayList<Articulo>();
	private List<Regalo> cartReg = new ArrayList<Regalo>();
	private float precio = 0;
	private int points = 0;
	private boolean registered = false;

	public CartHandler(boolean r){
		registered = r;
	}
	
	
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public float getPrecio() {
		return precio;
	}



	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getPoints() {
		return points;
	}

	public void leerFichero(){
		String linea = "";
		if(!totalArt.isEmpty()){
			totalArt = new ArrayList<Articulo>();
		}
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("files/articulos.dat"));
			while (fichero.ready()) {
				linea = fichero.readLine();
				String[] info = linea.split(";");
				if(registered){
					if(info[0].equals("CV-PS-001")) info[5]=String.valueOf(Float.parseFloat(info[5])*0.8);
					if(info[0].equals("FV-CR-001")) info[5]=String.valueOf(Float.parseFloat(info[5])*0.8);
					if(info[0].equals("TM-XX-001")) info[5]=String.valueOf(Float.parseFloat(info[5])*0.8);
					if(info[0].equals("OT-SO-001")) info[5]=String.valueOf(Float.parseFloat(info[5])*0.8);
					if(info[0].equals("VI-CA-001")) info[5]=String.valueOf(Float.parseFloat(info[5])*0.8);
				}
				totalArt.add(new Articulo(info[0],info[1],info[2],info[3],info[4],Float.parseFloat(info[5]),
						Integer.parseInt(info[6]),Integer.parseInt(info[7]),"/img/"+info[0]+".jpg"));
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
	
	public void leerRegalo(){
		String linea = "";
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("files/regalos.dat"));
			while (fichero.ready()) {
				linea = fichero.readLine();
				String[] info = linea.split(";");
				totalRegalos.add(new Regalo(info[0],info[1],info[2],Integer.parseInt(info[3])));
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


	public int registrarArticulo(Producto pr) {
		String nombreFichero = "files/cart.dat";
		String linea ="";

		if(pr.isArticulo()){
			Articulo art = (Articulo) pr;
			String cat = "";
			boolean already = false;
			if(art.getCat()==Categoria.Consolas) cat = "Consolas y Videojuegos";
			else if(art.getCat()==Categoria.Foto) cat = "Fotografía y Vídeo";
			else if(art.getCat()==Categoria.Moviles) cat = "Telefonía Móvil";
			else if(art.getCat()==Categoria.PC) cat = "Ordenadores y Tablets";
			else if(art.getCat()==Categoria.VideoM) cat = "Videovigilancia";
			for(Articulo arti:cartArt){
				if(arti.getDenom().equals(art.getDenom())){
					arti.setUnidades(art.getUnidades()+1);
					precio+=arti.getValor();
					points+=arti.getPuntos();
					already = true;
					linea=art.getCodigo()+";"+cat+";"+art.getScat()+";"+art.getDenom()+";"+art.getDescr()
					+";"+art.getValor()+";"+art.getPuntos()+";"+art.getStock()+"\n";
				}
			}
			if(!already){
				art.setUnidades(1);
				precio+=art.getValor();
				points+=art.getPuntos();
				cartArt.add(art);
				linea=art.getCodigo()+";"+cat+";"+art.getScat()+";"+art.getDenom()+";"+art.getDescr()
				+";"+art.getValor()+";"+art.getPuntos()+";"+art.getStock()+"\n";
			}
			/*if(art.getUnidades()+1<=art.getStock()){
				art.setUnidades(art.getUnidades()+1);
				precio+=art.getValor();
				points+=art.getPuntos();
				if(cartArt.indexOf(art)==-1){
					cartArt.add(art);
					linea=art.getCodigo()+";"+cat+";"+art.getScat()+";"+art.getDenom()+";"+art.getDescr()
					+";"+art.getValor()+";"+art.getPuntos()+";"+art.getStock()+"\n";
				}
				else{
					cartArt.remove(art);
					cartArt.add(art);
				}
			}
			else{
				System.out.println("·");
			}*/
		}
		else{
			Regalo rega = (Regalo) pr;
			String cat = "";
			points-=rega.getPuntos();
			cartReg.add(rega);
			if(rega.getCat()==Categoria.Consolas) cat = "Consolas y Videojuegos";
			else if(rega.getCat()==Categoria.Foto) cat = "Fotografía y Vídeo";
			else if(rega.getCat()==Categoria.Moviles) cat = "Telefonía Móvil";
			else if(rega.getCat()==Categoria.PC) cat = "Ordenadores y Tablets";
			else if(rega.getCat()==Categoria.VideoM) cat = "Videovigilancia";
			linea=rega.getCodigo()+";"+cat+";"+rega.getName()+";"+rega.getPuntos()+"\n";
		}

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

	public List<Articulo> getCart() {
		return totalArt;
	}
	
	public List<Regalo>	getReg(){
		return totalRegalos;
	}
	
	public List<Articulo> getCartArt() {
		return cartArt;
	}
	
	public List<Regalo>	getCartReg(){
		return cartReg;
	}
	
	/*
	public void leerCarro(){
		String linea = "";
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("files/cart.dat"));
			while (fichero.ready()) {
				linea = fichero.readLine();
				String[] info = linea.split(";");
				if(info.length>4){
					cartArt.add(new Articulo(info[0],info[1],info[2],info[3],info[4],Float.parseFloat(info[5]),
						Integer.parseInt(info[6]),Integer.parseInt(info[7]),"/img/"+info[0]+".jpg"));
				}
				else if(info.length>1){
					cartReg.add(new Regalo(info[0],info[1],info[2],Integer.parseInt(info[3])));
				}
			}
			fichero.close();
		}
		catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(null,"El archivo no se ha encontrado");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}*/

	public void aplicarDescuento(Categoria c) {
		for(Articulo a:totalArt){
			if(a.getCat()==c){
				double f = a.getValor();
				a.setValor(f-(f*0.1));
			}
		}
		
	}



	public void setCartArt(List<Articulo> lA) {
		cartArt=lA;
	}
	
	
	public int reiniciarCarro() {
		String nombreFichero = "files/cart.dat";
		String linea ="";
		setPrecio(0);
		setPoints(0);
		//BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
		
		
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFichero));
			for(Articulo a:cartArt){
				String cat = "";
				if(a.getCat()==Categoria.Consolas) cat = "Consolas y Videojuegos";
				else if(a.getCat()==Categoria.Foto) cat = "Fotografía y Vídeo";
				else if(a.getCat()==Categoria.Moviles) cat = "Telefonía Móvil";
				else if(a.getCat()==Categoria.PC) cat = "Ordenadores y Tablets";
				else if(a.getCat()==Categoria.VideoM) cat = "Videovigilancia";
				linea=a.getCodigo()+";"+cat+";"+a.getScat()+";"+a.getDenom()+";"+a.getDescr()
				+";"+a.getValor()+";"+a.getPuntos()+";"+a.getStock();
				precio+=a.getValor()*a.getUnidades();
				points+=a.getPuntos()*a.getUnidades();
				fichero.write(linea);
				fichero.newLine();
			}
			for(Regalo r:cartReg){
				String cat = "";
				if(r.getCat()==Categoria.Consolas) cat = "Consolas y Videojuegos";
				else if(r.getCat()==Categoria.Foto) cat = "Fotografía y Vídeo";
				else if(r.getCat()==Categoria.Moviles) cat = "Telefonía Móvil";
				else if(r.getCat()==Categoria.PC) cat = "Ordenadores y Tablets";
				else if(r.getCat()==Categoria.VideoM) cat = "Videovigilancia";
				linea=r.getCodigo()+";"+cat+";"+r.getName()+";"+r.getPuntos();
				points-=r.getPuntos();
				fichero.write(linea);
				fichero.newLine();
			}
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



	private void setPoints(int i) {
		points = i;
	}



	public List<Articulo> getArticulosN(Categoria c) {
		List<Articulo> lA = new ArrayList<Articulo>();
		for(Articulo a:totalArt){
			if(a.getCat()==c){
				lA.add(a);
			}
		}
		return lA;
	}



	public int getStock(Articulo page) {
		int initialSt = page.getStock();
		int units = 0;
		for(Articulo a:cartArt){
			if(a.getDenom()==page.getDenom()){
				units = a.getUnidades();
			}
		}
		return initialSt-units;
	}
	
	public void reiniciarRegalos(){
		List<Regalo> lR = new ArrayList<Regalo>();
		cartReg = lR;
		reiniciarCarro();
	}



	public void setCartReg(List<Regalo> lA) {
		cartReg=lA;
	}



	public void reiniciarArt() {
		List<Articulo> lR = new ArrayList<Articulo>();
		cartArt = lR;
		reiniciarCarro();
	}
	

}
