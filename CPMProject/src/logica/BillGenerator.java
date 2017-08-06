package logica;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BillGenerator {
	
	private Shop shop = null;
	private ResourceBundle mensajes;
	
	public BillGenerator(Shop s){
		shop = s;
	}
	

	public String billString(Locale localizacion){
		inter(localizacion);
		String result="";
		float price = (float) shop.getCartHandler().getPrecio();
		float points = (float) shop.getDiscountedPoints();
		result+=mensajes.getString("texto1")+ "– STARK SHOP – ";
		Date fechaHora = new Date();
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG,localizacion);
		result+=formatoFecha.format(fechaHora)+"\n";
		result+="----------------------------------------------------------------- \n";
		result+=mensajes.getString("texto2")+shop.getUser().getName()+" "+shop.getUser().getSurname()+"   NIF:"+shop.getUser().getNif()+"\n";
		result+="----------------------------------------------------------------- \n\n\n";
		result+="*"+mensajes.getString("texto3")+"*\n\n";
		result+=mensajes.getString("texto4")+"\n";
		List<Articulo> lsA = shop.getCartHandler().getCartArt();
		for(Articulo a:lsA){
			String disc = "";
			if(a.getCat()==shop.getDiscounted()) disc = " (*) ";
			result +="* "+a.getDenom()+" / "+a.getCodigo()+" / "+a.getUnidades()+
					" / "+a.getUnidades()*a.getValor()+ disc + "\n";
			
		}
		result+="\n (*)"+mensajes.getString("texto5")+"\n\n\n";
		List<Regalo> lsr = shop.getCartHandler().getCartReg();
		if(!lsr.isEmpty()){
			result+="*"+mensajes.getString("texto6")+"*\n\n";
			result+=mensajes.getString("texto7")+"\n";
			for(Regalo a:lsr){
				result +="* "+a.getName()+" / "+a.getCodigo()+"\n";	
			}
		}
		if(localizacion!=Locale.ENGLISH){
			result+="\n\n"+ mensajes.getString("texto8")+". . . . . . . . . . . . . . . . . . . . . . . . . "+price+mensajes.getString("texto11");
			result+="\n"+ mensajes.getString("texto9")+". . . . . . . . . . . . . . . . . . . . . . . . . "+points+mensajes.getString("texto11");
			result+="\n"+ mensajes.getString("texto10")+ ". . . . . . . . . . . . . . . . . . . . . . . . . . . "+(price-points)+mensajes.getString("texto11");
		}
		else{
			result+="\n\n"+ mensajes.getString("texto8")+". . . . . . . . . . . . . . . . . . . . . . . . . "+mensajes.getString("texto11")+price;
			result+="\n"+ mensajes.getString("texto9")+". . . . . . . . . . . . . . . . . . . . . . . . . "+mensajes.getString("texto11")+points;
			result+="\n"+ mensajes.getString("texto10")+ ". . . . . . . . . . . . . . . . . . . . . . . . . . . "+mensajes.getString("texto11")+(price-points);
		}
		//System.out.println(result);
		return result;
	}
	
	private void inter(Locale l){
		Locale localizacion = l;
		mensajes = ResourceBundle.getBundle("textosF",localizacion); 
	}
	
}
