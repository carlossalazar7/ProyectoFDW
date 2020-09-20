package  sv.edu.udb.www.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.managed_beans.*;
import sv.edu.udb.www.utils.*;
public class LoginDao extends DBConnection {
	
	Connection con = null;
	Statement statement = null;
	ResultSet resultSet = null;

	public String authenticateUser(LoginBean loginBean) {
		String userName = loginBean.getUserName();
		String password = loginBean.getPassword();

		String userNameDB = "";
		String passwordDB = "";
		String roleDB = "";
		int codigo = 0;

		
		try {
			con = DBConnection.createConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery("select * from empleados");

			while (resultSet.next()) {
				userNameDB = resultSet.getString("usuarioEmpleado");
				passwordDB = resultSet.getString("contrasena");
				roleDB = resultSet.getString("codigoTipoEmpleado");
				codigo =Integer.parseInt(resultSet.getString("codigoEmpleado"));

				if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("1"))
					return "1";
				else if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("2"))
					return "2";
				else if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("3"))
					return "3";
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Invalid user credentials";
		
	}
        
        public int obtenerCodigo(String usuario) throws SQLException{
		try {
		String sql = "select codigoEmpleado FROM empleados  WHERE usuarioEmpleado  = ?";
		cs = con.prepareCall(sql);
		cs.setString(1, usuario);
		resultSet = cs.executeQuery();
		
		LoginBean miUsuario = new LoginBean();
		if (resultSet.next()) {
	 miUsuario.setCodigoEmpleado(resultSet.getInt("codigoEmpleado"));
		this.desconectar();
		return miUsuario.getCodigoEmpleado();
		}
		this.desconectar();
		return 0;
		
		}catch (SQLException ex) {
			Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
			
			}
	}
}