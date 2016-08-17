package View;

import java.io.IOException;

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
 * Servlet implementation class ManageMessage
 */
@WebServlet({ "/ManageMessage.jsp" , "/manageMessage.jsp" })
public class ManageMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageMessage() {
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
			String redirect = request.getParameter("redirect");

			int userInt = Integer.parseInt(userString);
			User userAux = Database.getInstance().readUser(userInt);

			if (option.equals("sendMessage")) {
				if (user.getFriends().contains(userAux)) {
					String content = request.getParameter("messageContent");
					Message message = new Message(0, content, user, userAux);

					userAux.addReceivedMessage(message);
					Database.getInstance().updateUser(userAux);
				}
			} else if (option.equals("delete")) {
				String messageString = request.getParameter("message");
				int messageInt = Integer.parseInt(messageString);

				Message message = Database.getInstance()
						.readMessage(messageInt);
				if (user.getReceivedMessages().contains(message)) {
					user.removeReceivedMessages(message);
					Database.getInstance().updateUser(user);
					Database.getInstance().deleteMessage(message);
				}
			}
			response.sendRedirect(redirect);
		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("Iface.jsp");
		} catch (NumberFormatException e){
			e.printStackTrace();
			response.sendRedirect("ShowMessages.jsp");
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
