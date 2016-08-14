package View;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ShowUser
 */
@WebServlet({ "/ShowUser.jsp", "/showUser.jsp" })
public class ShowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowUser() {
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
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			user = Persistence.Database.getInstance().readUser(user.getId());

			String userString = request.getParameter("user");
			int userInt = Integer.parseInt(userString);

			User useraux = Database.getInstance().readUser(userInt);
			List<User> friends = useraux.getFriends();
			List<Community> communities = useraux.getCommunities();

			out.print("<!DOCTYPE html><html lang='pt-br'><head>");
			out.print("<link rel='stylesheet' type='text/css' href='default.css'>");

			out.print("<ul>");
			out.print("<li><a href='Iface.jsp'>Home</a></li>");
			out.print("<li><a href='ShowUsers.jsp'>Usuários</a></li>");
			out.print("<li><a href='ShowMessages.jsp'>Mensagens</a></li>");
			out.print("<li><a href='createCommunity.html'>Criar comunidade</a></li>");
			out.print("<li><a href='ShowCommunities.jsp'>Ver comunidades</a></li>");
			out.print("<li><a href='EditProfile.jsp'>Editar perfil</a></li>");
			out.print("<li><a href='Logout.jsp'>Logout</a></li>");
			out.print("<li><a href='DeleteUser.jsp'>Deletar conta</a></li>");
			out.print("</ul>");

			out.print("<title>Perfil " + useraux.getProfile().getName()
					+ "</title></head><body>");

			out.print("<h1>" + useraux.getProfile().getName() + "</h1>");
			out.print("<h2>Amigos de " + useraux.getProfile().getName()
					+ "</h2>");
			out.print("<center><table>");
			out.print("<tr><th>Login</th><th>Nome</th><th>Status</th><th>Cidade atual</th><th>Sexo</th></tr>");

			for (User friend : friends) {
				out.print("<tr><td>" + friend.getLogin() + "</td><td>"
						+ friend.getProfile().getName() + "</td><td>"
						+ friend.getProfile().getStatus() + "</td><td>"
						+ friend.getProfile().getCity() + "</td><td>"
						+ friend.getProfile().getGender() + "</td></tr>");
			}

			out.print("</table></center>");

			out.print("<h2>Comunidades que " + useraux.getProfile().getName()
					+ " participa</h2>");
			out.print("<center><table>");
			out.print("<tr><th>Comunidade</th><th>Descrição</th></tr>");

			for (Community community : communities) {
				out.print("<tr><td>" + community.getName() + "</td><td>"
						+ community.getDescription() + "</td></tr>");
			}

			out.print("</table></center>");

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
