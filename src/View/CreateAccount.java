package View;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Community;
import Model.Message;
import Model.Post;
import Model.Profile;
import Model.User;
import Persistence.Database;

/**
 * Servlet implementation class singin
 */
@WebServlet({"/CreateAccount.jsp","/createAccount.jsp" })
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		try {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String city = request.getParameter("city");
			String gender = request.getParameter("gender");
			String status = request.getParameter("status");
			
			User user = new User(0, login, password, new Profile(0, name, city, gender, status),new ArrayList<Message>(),new ArrayList<Community>(),new ArrayList<Community>(),new ArrayList<User>(), new ArrayList<User>(), new ArrayList<Post>());
			
			if (Database.getInstance().validateLogin(user) && !login.equals("") && !password.equals("")) {
				Database.getInstance().createUser(user);
				response.sendRedirect("login.html");
			} else {
				response.sendRedirect("invalidData.html");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("invalidData.html");
		}
	}

}
