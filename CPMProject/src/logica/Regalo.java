package logica;

public class Regalo implements Producto {

	private String codigo;
	private Categoria cat;
	private String name;
	private int puntos;
	
	public Regalo(String cod, String categ, String nm, int pts){
		codigo=cod;
		
		if(categ.equals("Consolas y Videojuegos")) cat = Categoria.Consolas;
		else if(categ.equals("Fotograf�a y V�deo")) cat = Categoria.Foto;
		else if(categ.equals("Telefon�a M�vil")) cat = Categoria.Moviles;
		else if(categ.equals("Ordenadores y tablets")) cat = Categoria.PC;
		else if(categ.equals("Videovigilancia")) cat = Categoria.VideoM;
		//else System.out.println("Wrong article");
		name=nm;
		puntos=pts;
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




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public int getPuntos() {
		return puntos;
	}




	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}




	@Override
	public boolean isArticulo() {
		return false;
	}
	
	public String getStrCat(Categoria c){
		if(c==Categoria.Consolas) return "Consolas y Videojuegos";
		else if(c==Categoria.Foto) return "Fotograf�a y V�deo";
		else if(c==Categoria.Moviles) return "Telefon�a M�vil";
		else if(c==Categoria.PC) return "Ordenadores y Tablets";
		else if(c==Categoria.VideoM) return "Videovigilancia";
		else return "";
	}



	public String getString() {
		String result = "";
		String cat = "";
		if(getCat()!=null){
			cat = getStrCat(getCat());
		}
		result+=getName()+";"+cat+" > "+getPuntos()+" points";
		return result;
	}
	
}
