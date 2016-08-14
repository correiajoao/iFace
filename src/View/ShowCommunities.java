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
 * Servlet implementation class ShowCommunities
 */
@WebServlet({"/ShowCommunities.jsp","/showCommunities.jsp"})
public class ShowCommunities extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowCommunities() {
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

			List<Community> yourCommmunities = user.getCommunities();
			List<Community> allCommunities = Database.getInstance()
					.readAllCommunities();

			out.print("<!DOCTYPE html><html><head>");
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

			out.print("<title>Todas as comunidades</title></head><body>");

			out.print("<center><h2> Suas comunidades </h2></center>");
			out.print("<center><table>");
			out.print("<tr><th>Comunidade</th><th>Ação</th><th>Ação</th></tr>");

			for (Community community : yourCommmunities) {
				out.print("<tr><td>"
						+ community.getName()
						+ "</td><td><a href='/iFace/ManageCommunity.jsp?community="
						+ community.getId()
						+ "&option=leave&redirect=ShowCommunities.jsp'> Deixar </a></td><td><a href='/iFace/ShowCommunity.jsp?community="
						+ community.getId()
						+ "'> Visitar comunidade </a></td></tr>");
			}

			out.print("</table></center>");
			out.print("<center><h2> Todas as comunidades </h2></center>");
			out.print("<center><table>");
			out.print("<tr><th>Comunidade</th><th>Ação</th><th>Ação</th></tr>");

			for (Community community : allCommunities) {
				if (!user.getCommunities().contains(community)) {
					out.print("<tr><td>"
							+ community.getName()
							+ "</td><td><a href='/iFace/ManageCommunity.jsp?community="
							+ community.getId()
							+ "&option=join&redirect=ShowCommunities.jsp'> Juntar-se </a></td><td></td></tr>");
				} else {
					out.print("<tr><td>" + community.getName()
							+ "</td><td> Participando </td><td></td></tr>");
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
		doGet(request, response);
	}

}
