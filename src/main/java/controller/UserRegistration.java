package controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

@SuppressWarnings("unused")
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UserRegistration() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		insertUser(request, response);

		
		//	Debugging purpose 
		//	PrintWriter out = response.getWriter();
		// 	out.println("Inserted");
		 
	}

	void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String un = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		User user = new User(un, email, password, phone);

		UserDao udao = new UserDao();
		try {
				if(!(udao.existsByEmail(email))) 
				{
				udao.insertUser(user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				}
				else
				{
					//Debugging purpose 
					//PrintWriter out = response.getWriter();
					//out.println("Not Inserted");
					
					HttpSession session = request.getSession();
					session.setAttribute("message","Email already exist, Please Try other Email ID");
					
					getServletContext().log("Email already exist " + email);
					
					response.sendRedirect(request.getContextPath()+"/register.jsp");
				}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
