package sv.edu.udb.www.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.managed_beans.*;

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginDao loginDao = new LoginDao();		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(userName);
		System.out.println(password);
		
		LoginBean loginBean = new LoginBean();

		loginBean.setUserName(userName);
		loginBean.setPassword(password);

		try {
			String userValidate = loginDao.authenticateUser(loginBean);
			int numero = loginDao.obtenerCodigo(userName);
			loginBean.setCodigoEmpleado(numero);
			System.out.println(numero);
			if (userValidate.equals("1")) {
				System.out.println("Admin's Home");
				HttpSession session = request.getSession(); // Creating a session
				session.setAttribute("Admin", userName); // setting session attribute
				request.setAttribute("usuarioEmpleado", userName);
				request.setAttribute("codigoEmpleado", numero);
				request.getRequestDispatcher("faces/paginaadmin1.xhtml").forward(request, response);
			} else if (userValidate.equals("2")) {
				System.out.println("Admin2's Home");

				HttpSession session = request.getSession();
				session.setAttribute("Editor", userName);
				request.setAttribute("usuarioEmpleado", userName);
				request.setAttribute("codigoEmpleado", numero);

				request.getRequestDispatcher("faces/paginaadmin.xhtml").forward(request, response);
			} else if (userValidate.equals("3")) {
				System.out.println("User's Home");

				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10 * 60);
				session.setAttribute("User", userName);
				request.setAttribute("usuarioEmpleado", userName);
				request.setAttribute("codigoEmpleado", numero);
				request.getRequestDispatcher("faces/paginausuario.xhtml").forward(request, response);
			} else {
				System.out.println("Error message = " + userValidate);
				request.setAttribute("errMessage", userValidate);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	} // End of doPost()
}