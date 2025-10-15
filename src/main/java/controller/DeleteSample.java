// While click Delete button on viewsample.jsp call DeleteSample to delete Sample from DB
// Once deleted , Page will get redirected to the ViewSample(Servlet), then ViewServlet 
// is dispatching the existing request to viewsample.jsp

/*http://localhost:8080/LabSampleManagement//DeleteSample?id=S-001
Post Request
In Param Tab automatically Key Value will be generated */

package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SampleDao;


@WebServlet("/DeleteSample")
public class DeleteSample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SampleDao sampledao = new SampleDao();
		String s_id = request.getParameter("id");

		boolean deleted = sampledao.deleteSample(s_id);
		
		if(deleted)
		{
			response.sendRedirect(request.getContextPath()+"/ViewSample");
		}

		
	}

}
