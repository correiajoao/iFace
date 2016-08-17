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

import Model.Message;
import Model.User;
import Persistence.Database;

/**
 * Servlet implementation class ShowMessages
 */
@WebServlet({ "/ShowMessages.jsp","/showMessages.jsp"})
public class ShowMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowMessages() {
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
			user = Database.getInstance().readUser(user.getId());

			List<Message> messages = user.getReceivedMessages();

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

			out.print("<title>(" + user.getReceivedMessages().size()
					+ ") Mensagens</title></head><body>");

			out.print("<center><h2> Mensagens recebidas </h2></center>");
			out.print("<center><table>");
			out.print("<tr><th>Amigo</th><th>Mensagem</th><th>Ação</th><th>Ação</th></tr>");

			for (Message message : messages) {
				out.print("<tr><td>"
						+ message.getUserSender().getProfile().getName()
						+ "</td><td>"
						+ message.getContent()
						+ "</td><td><form method='get' action='ManageMessage.jsp'> <input type='text' name='messageContent' placeholder='Responder'> <input type='submit' value='Enviar'><input type='hidden' name='user' value='"
						+ message.getUserSender().getId()
						+ "'><input type='hidden' name='option' value='sendMessage'><input type='hidden' name='redirect' value='ShowMessages.jsp'></form></td><td><a href='/iFace/ManageMessage.jsp?option=delete&user="
						+ message.getUserSender().getId() + "&message="
						+ message.getId()
						+ "&redirect=ShowMessages.jsp'> Deletar </a></td></tr>");
			}

			out.print("</table></center>");
			out.print("</body></html>");

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
