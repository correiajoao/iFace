package View;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR }					, 
		urlPatterns = {
				"/CreateCommunity.jsp", 
				"/DeleteUser.jsp", 
				"/EditProfile.jsp", 
				"/ManageMessage.jsp", 
				"/Iface.jsp", 
				"/Logout.jsp", 
				"/ManageCommunity.jsp", 
				"/ManageUser.jsp", 
				"/ShowCommunities.jsp", 
				"/ShowMessages.jsp", 
				"/ShowCommunity.jsp", 
				"/ShowUser.jsp", 
				"/ShowUsers.jsp",
				"/createCommunity.jsp", 
				"/deleteUser.jsp", 
				"/editProfile.jsp", 
				"/manageMessage.jsp", 
				"/iface.jsp", 
				"/logout.jsp", 
				"/manageCommunity.jsp", 
				"/manageUser.jsp", 
				"/showCommunities.jsp", 
				"/showMessages.jsp", 
				"/showCommunity.jsp", 
				"/showUser.jsp", 
				"/showUsers.jsp"
		})
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(false);
	
		System.out.println("Filtro de sessão");
		if (session != null){
			System.out.println("Passou");
			chain.doFilter(request, response);
		}else{
			System.out.println("Não passou");
			((HttpServletResponse) response).sendRedirect("index.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
