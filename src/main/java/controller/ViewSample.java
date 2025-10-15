/*While click View All Samples button on sampleops.jsp , it call ViewSample(Servlet) to fetch all 
Samples from DB & show it on viewsample.jsp 
From viewsample.jsp ,, After Deleting a Sample by DeleteSample(Servlet) and 
Updating Sample Status by updatesamplestatus.jsp -> UpdateSampleStatus(Servlet) 
again ViewSample(Servlet) is called and viewsample.jsp is loaded*/

/*ViewSample(Servlet) will also check if any status is selected by user or not to get filtered 
view of sample by status in viewsample.jsp. If status is selected, filtered data will be displayed 
else all data will be displayed */

/*ViewSample(Servlet) will also check if  any msg is coming from UpdateSampleStatus(Servlet) 
Or not. If Data get updated successfully it will set the customized alert message and send it 
to viewsample.jsp*/

/*http://localhost:8080/LabSampleManagement/ViewSample
Get Request 
See the output in Body -> Preview */

/*http://localhost:8080/LabSampleManagement/ViewSample?status=In+Progress
Get Request
In Param Tab automatically Key Value will be generated */




package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;


import dao.SampleDao;
import model.Sample;


@WebServlet("/ViewSample")
public class ViewSample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final SampleDao sampledao = new SampleDao();
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String msg = request.getParameter("msg");
		
        	if ("success".equals(msg)) 
        	{
            	request.setAttribute("alertMsg", "Sample updated successfully!");
        	} 
		
        
		String status = request.getParameter("status"); // filter status
	    List<Sample> allSamples = null;

	       //  filter by status
	       if (status != null && !status.isEmpty()) {
	           allSamples = sampledao.getSamplesByStatus(status);
	       }
	       
	       // show all samples
	       else {
	           allSamples = sampledao.getAllSamples();
	       }

	       // attach samples to request
	       request.setAttribute("samples", allSamples);
	      

	       RequestDispatcher rd = request.getRequestDispatcher("/viewsample.jsp");
	       rd.forward(request, response);

	   
	}
	
		
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Post will not work as we are fetching/getting the data from DB 
	}
}
