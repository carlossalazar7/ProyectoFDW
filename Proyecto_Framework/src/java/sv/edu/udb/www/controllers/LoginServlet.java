package sv.edu.udb.www.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.managed_beans.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
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
            switch (userValidate) {
                case "1": {
                    System.out.println("Admin's Home");
                    HttpSession session = request.getSession(); // Creating a session
                    session.setAttribute("Admin", userName); // setting session attribute
                    request.setAttribute("usuarioEmpleado", userName);
                    request.setAttribute("codigoEmpleado", numero);
                    request.getRequestDispatcher("faces/listado.xhtml").forward(request, response);
                    break;
                }
                case "2": {
                    System.out.println("Admin2's Home");
                    HttpSession session = request.getSession();
                    session.setAttribute("Editor", userName);
                    request.setAttribute("usuarioEmpleado", userName);
                    request.setAttribute("codigoEmpleado", numero);
                    request.getRequestDispatcher("faces/paginaadmin2.xhtml").forward(request, response);
                    break;
                }
                case "3": {
                    System.out.println("User's Home");
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(10 * 60);
                    session.setAttribute("User", userName);
                    request.setAttribute("usuarioEmpleado", userName);
                    request.setAttribute("codigoEmpleado", numero);
                    request.getRequestDispatcher("faces/paginausuario.xhtml").forward(request, response);
                    break;
                }
                default:
                    System.out.println("Error message = " + userValidate);
                    request.setAttribute("errMessage", userValidate);
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
                    break;
            }
        } catch (IOException | SQLException | ServletException e1) {
        }
    } // End of doPost()
}
