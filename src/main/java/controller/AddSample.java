// In UserLogin(Servlet) we are fetching the Username by email provided by the user and setting that
// Username with session.setAttribute() .. Then we are sending it to sampleops.jsp catching this 
// username and distributing it to addsample.jsp ..  addsample.jsp catching the username again here 
// and auto-filling username in the Requested By column. 

// After that Add a Sample through addsample.jsp (Add Sample Button) call this AddSample(Servlet) 
// to insert the Sample Details on DB


/*API Path : http://localhost:8080/LabSampleManagement/AddSample
(Key - copy from addsample.jsp file )
Post Request : sampleid , description , requestedBy , status*/

package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.SampleDao;
import model.Sample;



@WebServlet("/AddSample")
public class AddSample extends HttpServlet {
	private static final long serialVersionUID = 1L;  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json");
	       
	        try{
	        	SampleDao sampledao = new SampleDao();
	        	
	        	String s_id = request.getParameter("sampleid");
	    		String s_desc = request.getParameter("description");
	    		String s_requester = request.getParameter("requestedBy");
	    		String s_status = request.getParameter("status");

	    		Sample sample = new Sample(s_id, s_desc, s_requester, s_status);
	            
	    		
	            // create sample in DB
	            boolean isCreated = sampledao.createSample(sample);
	            if(isCreated)
	            {
	                response.setStatus(HttpServletResponse.SC_CREATED); // 201 created       
	            }
	            
	            else 
	            {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 status         
	            }
	        }
	        
	        
	        catch(Exception e)
	        {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	           
	        }
	}

}
