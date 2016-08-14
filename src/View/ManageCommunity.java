package View;

import java.io.IOException;

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
 * Servlet implementation class joinCommunity
 */
@WebServlet({ "/ManageCommunity.jsp", "/manageCommunity.jsp" })
public class ManageCommunity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageCommunity() {
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

			String communityString = request.getParameter("community");
			String option = request.getParameter("option");
			String redirect = request.getParameter("redirect");

			int communityInt = Integer.parseInt(communityString);

			Community community = Database.getInstance().readCommunity(
					communityInt);
			if (option.equals("join")) {
				if (!user.getCommunities().contains(community)) {
					community.addMember(user);
					user.addCommunities(community);
					Database.getInstance().updateUser(user);
				}
			} else if (option.equals("leave")) {
				if (user.getCommunities().contains(community)) {
					user.removeCommunities(community);
					Database.getInstance().updateUser(user);

					community.getMembers().remove(user);
					Database.getInstance().updateCommunity(community);
				}
			} else if (option.equals("writePost")) {
				if (user.getCommunities().contains(community)) {
					String content = request.getParameter("content");
					Post post = new Post(0, content, user, community);

					community.addPost(post);
					Database.getInstance().createCommunity(community);
				}
			}
			response.sendRedirect(redirect);
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("login.html");
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
