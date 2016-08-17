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
import Model.Post;
import Model.User;
import Persistence.Database;

/**
 * Servlet implementation class viewCommunity
 */
@WebServlet({"/ShowCommunity.jsp","/showCommunity.jsp"})
public class ShowCommunity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowCommunity() {
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
			response.setContentType("text/html; charset=utf-8");
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			user = Database.getInstance().readUser(user.getId());

			String communityString = request.getParameter("community");
			int communityInt = Integer.parseInt(communityString);

			Community community = Database.getInstance().readCommunity(
					communityInt);
			List<User> members = community.getMembers();
			List<Post> posts = community.getPosts();

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

			out.print("<title> " + community.getName()
					+ " </title></head><body>");

			out.print("<center><h2> " + community.getName() + " </h2></center>");
			out.print("<center><h4> " + community.getDescription()
					+ " - Administrador: "
					+ community.getUserAdmin().getProfile().getName()
					+ "</h4></center>");

			out.print("<center><table>");
			out.print("<tr><th>Usuário</th><th>Status</th></tr>");

			for (User member : members) {
				out.print("<tr><td>" + member.getProfile().getName()
						+ "</td><td>" + member.getProfile().getStatus()
						+ "</td></tr>");
			}

			out.print("<tr><td colspan='2'><center> <form method='get' action='/iFace/ManageCommunity.jsp'> <input type='text' name='content' placeholder='Escreva sua postagem'><input type='hidden' name='option' value='writePost'><input type='hidden' name='community' value='"
					+ community.getId()
					+ "'><input type='hidden' name='user' value='"
					+ user.getId()
					+ "'><input type='hidden' name='redirect' value='ShowCommunity.jsp?community="
					+ community.getId()
					+ "'><input type='submit' value='Postar'> </form></center></td></tr>");
			out.print("</table></center>");

			out.print("<br><center><h2> Posts </h2></center>");
			out.print("<center><table>");
			out.print("<tr><th>Usuário</th><th>Postagem</th></tr>");

			for (Post post : posts) {
				out.print("<tr><td>" + post.getUser().getProfile().getName()
						+ "</td><td>" + post.getContent() + "</td></tr>");
			}

			out.print("</table></center>");
			out.print("</body></html>");
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("Iface.jsp");
		} catch (NumberFormatException e){
			e.printStackTrace();
			response.sendRedirect("ShowCommunities.jsp");
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
