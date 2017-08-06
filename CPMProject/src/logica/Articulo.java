package logica;

import java.util.Locale;

public class Articulo implements Producto{
	private String codigo;
	private Categoria cat;
	private String scat;
	private String denom;
	private String descr;
	private float valor;
	private int puntos;
	private int stock;
	private String foto;
	private int unidades;
	
	public Articulo(){}
	public Articulo(String code, String categ, String sub, String den, String descr, float v, int p, int s, String f){
		codigo=code;
		
		if(categ.equals("Consolas y Videojuegos")) cat = Categoria.Consolas;
		else if(categ.equals("Fotografía y Vídeo")) cat = Categoria.Foto;
		else if(categ.equals("Telefonía Móvil")) cat = Categoria.Moviles;
		else if(categ.equals("Ordenadores y Tablets")) cat = Categoria.PC;
		else if(categ.equals("Videovigilancia")) cat = Categoria.VideoM;
		//else System.out.println("Wrong article");
		scat=sub;
		denom = den;
		this.descr =descr;
		valor = v;
		puntos = p;
		stock = s;
		foto = f;
		unidades = 0;
	}
	
	public String getStrCat(Categoria c, Locale loc){
		if(loc==Locale.ENGLISH){
			if(c==Categoria.Consolas) return "Consoles";
			else if(c==Categoria.Foto) return "Photography and video";
			else if(c==Categoria.Moviles) return "Mobiles";
			else if(c==Categoria.PC) return "Computers and Tablets";
			else if(c==Categoria.VideoM) return "Videomonitoring";
			else return "";
		}
		else{
			if(c==Categoria.Consolas) return "Consolas y Videojuegos";
			else if(c==Categoria.Foto) return "Fotografía y Vídeo";
			else if(c==Categoria.Moviles) return "Telefonía Móvil";
			else if(c==Categoria.PC) return "Ordenadores y Tablets";
			else if(c==Categoria.VideoM) return "Videovigilancia";
			else return "";
		}
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Categoria getCat() {
		return cat;
	}
	public void setCat(Categoria cat) {
		this.cat = cat;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(double d) {
		this.valor = (float) d;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getScat() {
		return scat;
	}
	public void setScat(String scat) {
		this.scat = scat;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public boolean isArticulo() {
		return true;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getString(Locale localizacion) {
		String result = "";
		String coin = "";
		if(Locale.ENGLISH==localizacion){
			coin = "£";
			result+=getDenom()+";"+coin+getValor()+" "+getUnidades()+" units";
		}
		else{
			coin = "€";
			result+=getDenom()+";"+getValor()+coin+" "+getUnidades()+" units";
		}
		return result;
		
	}
	
	
}
