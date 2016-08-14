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

import Model.User;
import Persistence.Database;

/**
 * Servlet implementation class ShowUsers
 */
@WebServlet({ "/ShowUsers.jsp", "/showUsers.jsp" })
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowUsers() {
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

			List<User> friends = user.getFriends();
			List<User> friendshipRequests = user.getFriendshipRequests();
			List<User> allUsers = Database.getInstance().readAllUsers();

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

			out.print("<title>Todos os amigos</title></head><body>");

			out.print("<center><h2> Seus amigos </h2></center>");
			out.print("<center><table>");
			out.print("<tr><th>Amigo</th><th>Status</th><th>Ação</th></tr>");

			for (User friend : friends) {
				out.print("<tr><td><a href='/iFace/ShowUser.jsp?user="
						+ friend.getId()
						+ "'>"
						+ friend.getProfile().getName()
						+ "</td><td>"
						+ friend.getProfile().getStatus()
						+ "</td><td> <form method='get' action='ManageMessage.jsp'> <input type='text' name='messageContent' placeholder='Envie uma mensagem para "
						+ friend.getProfile().getName()
						+ "'><input type='hidden' name='user' value='"
						+ friend.getId()
						+ "'><input type='hidden' name='option' value='sendMessage'><input type='hidden' name='user' value='"
						+ friend.getId()
						+ "'> <input type='hidden' name='redirect' value='ShowUsers.jsp'><input type='submit' value='Enviar'></form></td></tr>");
			}

			out.print("</table></center>");

			out.print("<center><h2> Requisições de amizade </h2></center>");
			out.print("<center><table>");
			out.print("<tr><th>Usuário</th><th>Status</th><th>Ação</th><th>Ação</th></tr>");

			for (User requester : friendshipRequests) {
				out.print("<tr><td>"
						+ requester.getProfile().getName()
						+ "</td><td>"
						+ requester.getProfile().getStatus()
						+ "</td><td><a href='/iFace/ManageUser.jsp?user="
						+ requester.getId()
						+ "&option=confirmFriendRequest'> Confirmar </a></td><td><a href='/iFace/ManageUser.jsp?user="
						+ requester.getId()
						+ "&option=deleteFriendRequest'> Deletar </a></td></tr>");
			}

			out.print("</table></center>");
			out.print("</body></html>");

			out.print("<center><h2> Outros usuários </h2></center>");
			out.print("<center><table>");
			out.print("<tr><th>Usuário</th><th>Status</th><th>Ação</th></tr>");

			for (User userAux : allUsers) {
				if (userAux.getId() != user.getId()
						&& !userAux.getFriends().contains(user)
						&& !user.getFriends().contains(userAux)
						&& !user.getFriendshipRequests().contains(userAux)
						&& !userAux.getFriendshipRequests().contains(user)) {
					out.print("<tr><td>"
							+ userAux.getProfile().getName()
							+ "</td><td>"
							+ userAux.getProfile().getStatus()
							+ "</td><td><a href='/iFace/ManageUser.jsp?user="
							+ userAux.getId()
							+ "&option=sendFriendRequest'> Solicitar amizade </a></td></tr>");
				} else if (userAux.getFriendshipRequests().contains(user)
						|| user.getFriendshipRequests().contains(userAux)) {
					out.print("<tr><td>"
							+ userAux.getProfile().getName()
							+ "</td><td>"
							+ userAux.getProfile().getStatus()
							+ "</td><td> Solicitação de amizade enviada </td></tr>");
				}
			}

			out.print("</table></center>");
			out.print("</body></html>");

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
