package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Community;
import Model.User;
import Persistence.Database;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet({"/DeleteUser.jsp","/deleteUser.jsp"})
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUser() {
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

			List<User> friends = user.getFriends();
			List<User> allUsers = Database.getInstance().readAllUsers();
			List<Community> managedCommunities = user.getManagedCommunities();
			List<Community> communities = user.getCommunities();

			for (User userFriend : friends) {
				userFriend.getFriends().remove(user);
				Database.getInstance().updateUser(userFriend);
			}

			for (User useraux : allUsers) {
				useraux.getFriendshipRequests().remove(user);
			}

			for (Community community : managedCommunities) {
				community.setMembers(new ArrayList<User>());
				Database.getInstance().updateCommunity(community);
				Database.getInstance().deleteCommunity(community);
			}

			for (Community community : communities) {
				community.getMembers().remove(user);
				Database.getInstance().updateCommunity(community);
			}

			user.setManagedCommunities(new ArrayList<Community>());
			user.setCommunities(new ArrayList<Community>());
			user.setFriendshipRequests(new ArrayList<User>());
			user.setFriends(new ArrayList<User>());

			Database.getInstance().updateUser(user);
			Database.getInstance().deleteUser(user);
			response.sendRedirect("index.html");
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("Iface.jsp");
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
