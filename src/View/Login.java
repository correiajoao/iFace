package View;

import java.io.IOException;

import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Persistence.Database;

/**
 * Servlet implementation class Login
 */
@WebServlet({"/Login.jsp","/login.jsp"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				HttpSession session = request.getSession(true);
				
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				
				if(Database.getInstance().validateUser(login,password)){
					User user = Database.getInstance().readUser(login);
					session.setAttribute("user", user);
					response.sendRedirect("Iface.jsp");
				}else{
					response.sendRedirect("invalidLogin.html");
				}
				
		} catch (Exception e) {
			response.sendRedirect("index.html");
		}
	}

}
