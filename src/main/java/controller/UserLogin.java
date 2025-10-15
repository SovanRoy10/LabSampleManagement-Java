// From index.jsp after click login button UserLogin called for match credentials (email & password)
// from DB ,, using getUserNameByEmail(email) we are fetching UserName. And setting this Username 
// existing request and redirected to sampleops.jsp page



package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import dao.UserDao;


@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserLogin() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		loginUser(request, response);

	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		UserDao userdao = new UserDao();
		User user = new User(email, pass);

		try 
		{
			if (userdao.login(user)) 	// login method of UserDao return true if when user found
			{
				String uname = userdao.getUserNameByEmail(email);
				HttpSession session = request.getSession();
				session.setAttribute("username", uname.toUpperCase());
				
				// Debugging Purposes
				// PrintWriter out = response.getWriter();
				// out.println("Inserted");
				
				response.sendRedirect(request.getContextPath() + "/sampleops.jsp");
			}
			

			else 		// if (userdao.login(user) ==  false)
			{
				// login method of UserDao return false if when user not found
				HttpSession session = request.getSession();
				session.setAttribute("message", "Invalid Password or Username!");

				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		}
		
		

		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}
