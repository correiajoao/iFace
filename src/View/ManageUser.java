package View;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.User;
import Persistence.Database;

/**
 * Servlet implementation class ManageUser
 */
@WebServlet({ "/ManageUser.jsp","/manageUser.jsp" })
public class ManageUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			user = Persistence.Database.getInstance().readUser(user.getId());

			String userString = request.getParameter("user");
			String option = request.getParameter("option");
			int userInt = Integer.parseInt(userString);

			User userAux = Database.getInstance().readUser(userInt);
			if (option.equals("sendFriendRequest")) {
				if (userAux.getId() != user.getId()
						&& !userAux.getFriends().contains(user)
						&& !user.getFriends().contains(userAux)
						&& !user.getFriendshipRequests().contains(userAux)
						&& !userAux.getFriendshipRequests().contains(user)) {
					userAux.addFriendshipRequests(user);
					Database.getInstance().updateUser(userAux);
				} else {
					response.sendRedirect("ShowUsers.jsp");
				}
			} else if (option.equals("confirmFriendRequest")) {
				if (user.getFriendshipRequests().contains(userAux)
						&& !user.getFriends().contains(userAux)
						&& !userAux.getFriends().contains(user)) {
					user.addFriend(userAux);
					user.removeFriendshipRequests(userAux);

					userAux.addFriend(user);
					userAux.removeFriendshipRequests(user);

					Database.getInstance().updateUser(user);
					Database.getInstance().updateUser(userAux);
				}
			} else if (option.equals("deleteFriendRequest")) {
				if (user.getFriendshipRequests().contains(userAux)
						&& !user.getFriends().contains(userAux)
						&& !userAux.getFriends().contains(user)) {
					user.removeFriendshipRequests(userAux);
					userAux.removeFriendshipRequests(user);

					Database.getInstance().updateUser(user);
					Database.getInstance().updateUser(userAux);
				}
			}
			response.sendRedirect("ShowUsers.jsp");

		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("Iface.jsp");
		} catch (NumberFormatException e){
			e.printStackTrace();
			response.sendRedirect("ShowUsers.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
