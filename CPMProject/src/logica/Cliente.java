package logica;

public class Cliente {
	private String password;
	private String user;
	private String nif;
	private int puntos;
	private String phone;
	private String bankAccount;
	
	private String name;
	private String surname;
	
	
	public Cliente(){
		puntos = 0;
	}
	
	public Cliente(String nam, String sur, String niff,String userN,String pass,String tphone,String account,int punt){
		name=nam;
		surname=sur;
		user=userN;
		password = pass;
		puntos = 0;
		nif=niff;
		phone=tphone;
		bankAccount=account;
		puntos = punt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getNif() {
		return nif;
	}



	public void setNif(String nif) {
		this.nif = nif;
	}



	public int getPuntos() {
		return puntos;
	}



	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getBankAccount() {
		return bankAccount;
	}



	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	

}
