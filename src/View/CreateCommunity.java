package View;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Community;
import Model.Post;
import Model.User;
import Persistence.Database;

/**
 * Servlet implementation class CreateCommunity
 */
@WebServlet({"/CreateCommunity.jsp","/createCommunity.jsp"})
public class CreateCommunity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCommunity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			user = Persistence.Database.getInstance().readUser(user.getId());

			String name = request.getParameter("name");
			String description = request.getParameter("description");

			Community community = new Community(0, name, description,
					new ArrayList<User>(), new ArrayList<Post>(), user);
			Database.getInstance().createCommunity(community);
			response.sendRedirect("Iface.jsp");
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("Iface.jsp");
		}
	}

}
