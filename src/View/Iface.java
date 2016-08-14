package View;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.User;

/**
 * Servlet implementation class Iface
 */
@WebServlet({"/Iface.jsp","/iface.jsp"})
public class Iface extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Iface() {
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
			session.setAttribute("user", user);
			user = Persistence.Database.getInstance().readUser(user.getId());

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

			out.print("<title>iFace</title></head><body>");
			out.print("<center><h1> iFace </h1></center>");
			out.print("<center><h2>Olá " + user.getProfile().getName()
					+ ", estavamos sentindo sua falta.</h2></center>");

			out.print("<h3>Esses são seus dados</h3>");
			out.print("<center><table>");
			out.print("<tr><th>Login</th><th>Nome</th><th>Status</th><th>Cidade atual</th><th>Sexo</th></tr>");
			out.print("<tr><td>" + user.getLogin() + "</td><td>"
					+ user.getProfile().getName() + "</td><td>"
					+ user.getProfile().getStatus() + "</td><td>"
					+ user.getProfile().getCity() + "</td><td>"
					+ user.getProfile().getGender() + "</td></tr>");

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
		doGet(request, response);
	}

}
