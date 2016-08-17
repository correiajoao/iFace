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
import Persistence.Database;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet({"/EditProfile.jsp","/editProfile.jsp"})
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfile() {
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

			out.print("<title>Editar perfil</title></head><body>");
			out.print("<h1> Edite atributos do seu perfil </h1>");
			out.print("<form action='EditProfile.jsp' method='post'>");
			out.print("<h4>Seu Login: " + user.getLogin() + "</h4>");
			out.print("Senha: <input type='password' name='password' value='"
					+ user.getPassword() + "'><br>");
			out.print("Nome: <input type='text' name='name' value='"
					+ user.getProfile().getName() + "'><br>");

			if (user.getProfile().getGender() != null) {
				if (user.getProfile().getGender().contains("masculino")) {
					out.print("Sexo: <input type='radio' name='gender' value='masculino' checked='checked'> Masculino");
				} else {
					out.print("Sexo: <input type='radio' name='gender' value='masculino'> Masculino");
				}

				if (user.getProfile().getGender().contains("feminino")) {
					out.print("<input type='radio' name='gender' value='feminino' checked='checked'> Feminino<br>");
				} else {
					out.print("<input type='radio' name='gender' value='feminino'> Feminino<br>");
				}
			} else {
				out.print("Sexo: <input type='radio' name='gender' value='masculino'> Masculino");
				out.print("<input type='radio' name='gender' value='feminino'> Feminino<br>");
			}

			out.print("Cidade: <input type='text' name='city' value='"
					+ user.getProfile().getCity() + "'><br>");
			out.print("Status: <input type='text' name='status' value='"
					+ user.getProfile().getStatus() + "'><br>");
			out.print("<input type='submit' value='Finalizar'>");
			out.print("</form></center>");
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
		try {
			HttpSession session = request.getSession(false);

			User user = (User) session.getAttribute("user");

			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String city = request.getParameter("city");
			String gender = request.getParameter("gender");
			String status = request.getParameter("status");
			
			if(!password.equals("")){
				user = Database.getInstance().readUser(user.getLogin());
				user.setPassword(password);
				user.getProfile().setCity(city);
				user.getProfile().setGender(gender);
				user.getProfile().setName(name);
				user.getProfile().setStatus(status);
				
				Database.getInstance().updateUser(user);
				session.setAttribute("user", user);
				response.sendRedirect("Iface.jsp");
			}else{
				response.sendRedirect("invalidOperation.html");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("invalidOperation.html");
		}
	}
}
