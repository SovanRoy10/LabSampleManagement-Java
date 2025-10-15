// While Update a Sample Status, After clicking Update Sample button in updatesamplestatus.jsp, 
// it call UpdateSampleStatus(Servlet) to update Sample status on DB
// Then After successfully updating sample status one msg variable is set with a custom value ,, 
// then ViewSample(Servlet) is called automatically and check msg variable and Set a custom message
// and redirected to the viewsample.jsp page and display the custom message as JS alert.

/*http://localhost:8080/LabSampleManagement/UpdateSampleStatus
Post Request : status , sampleid
Provide Key Value in Body -> x-www-form-urlencoded*/

package controller;

import java.io.IOException;

import java.sql.SQLException;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SampleDao;



@WebServlet("/UpdateSampleStatus")
public class UpdateSampleStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SampleDao sampledao = new SampleDao();
		String sid = request.getParameter("sampleid");
		String newStatus = request.getParameter("status");
		
		try 
		{
			boolean updated = sampledao.updateStatus(sid, newStatus);
			
			if(updated)
				{
					response.sendRedirect(request.getContextPath()+"/ViewSample?msg=success");
				}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
