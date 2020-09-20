package sv.edu.udb.www.managed_beans;

public class LoginBean {

	private String userName;
	private String password;
	private String correo;
	private int codigoEmpleado;
	
	
	public LoginBean() {
		this.userName="";
		this.password="";
		this.correo="";
		this.codigoEmpleado = 0;
	}
	
	public LoginBean(String userName, String password, String correo, int codigoEmpleado) {
		this.userName=userName;
		this.password=password;
		this.correo=correo;
		this.codigoEmpleado= codigoEmpleado;
	}
	
	

	public int getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(int codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}