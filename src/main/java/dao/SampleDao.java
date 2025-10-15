package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import model.Sample;

public class SampleDao {
	
	static final Connection con = DBConfig.getCon();
	static final String insertQuery = "INSERT INTO samples(sample_id, description, requested_by, status) VALUES (?, ?, ?, ?)";
	static final String get_all_sample = "SELECT sample_id, description, requested_by, status, created_date FROM samples";
	static final String get_sample_byid = "SELECT sample_id, description,requested_by, status, created_date FROM samples WHERE sample_id = ?";
	static final String update_sample_query = "UPDATE samples SET status = ? WHERE sample_id = ?";
	static final String delete_query = "DELETE FROM samples WHERE sample_id = ?";
	static final String get_sample_by_status = "SELECT sample_id, description,requested_by, status, created_date FROM samples WHERE status = ?";
	
	 // --- Inserting New SAMPLE ---
	public boolean createSample(Sample sample) {
            
            PreparedStatement psInsert = null;
            
			try {
				psInsert = con.prepareStatement(insertQuery);
				psInsert.setString(1, sample.getSampleId());
				psInsert.setString(2, sample.getDescription());
				psInsert.setString(3, sample.getRequestedBy());
				psInsert.setString(4, sample.getStatus());

				return psInsert.executeUpdate() == 1;
			} 
			
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return false;      
            
        }
    

	
	
    // View all samples
    public List<Sample> getAllSamples()  {
        
        List<Sample> list = new ArrayList<>();
        PreparedStatement ps;
		try {
			ps = con.prepareStatement(get_all_sample);
			ResultSet rs = ps.executeQuery();
	
	        while(rs.next())
	            {
	                Sample s = new Sample(
	                        rs.getString("sample_id"),
	                        rs.getString("description"),
	                        rs.getString("requested_by"),
	                        rs.getString("status"),
	                        rs.getTimestamp("created_date")
	                        );
	                list.add(s);
	            }
	        }
		
		
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        return list;
    }


    
 // Find sample by id
    public Sample getSampleById(String id) {
        
			try {
				PreparedStatement ps = con.prepareStatement(get_sample_byid);
				
				  ps.setString(1, id);
		          
		            ResultSet rs = ps.executeQuery();
		            
		            
		                if (rs.next()) 
		                {
		                    return new Sample(
		                            rs.getString("sample_id"),
		                            rs.getString("requested_by"),
		                            rs.getString("description"),
		                            rs.getString("status"),
		                            rs.getTimestamp("created_date"));
		                   
		                
		            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
          
        
        return null;
    }
    

    // Update the status of an existing sample
    public boolean updateStatus(String SampleId, String newStatus) throws SQLException {
       
      
        PreparedStatement ps = con.prepareStatement(update_sample_query);
        
        {
            ps.setString(1,newStatus);
            ps.setString(2,SampleId);
            return ps.executeUpdate() == 1;
        }
    }

    // Remove a sample using id
    public boolean deleteSample(String SampleId)  {
      
			try {
				PreparedStatement ps = con.prepareStatement(delete_query);
				ps.setString(1, SampleId);
		        return ps.executeUpdate() == 1;
		        
			} 
			catch (SQLException e) {	
				e.printStackTrace();
			}
			
			return false; 
           
        
    }

 // Get all samples by using status : useful for filtering by status
    public List<Sample> getSamplesByStatus(String status)  {
        
        List<Sample> list = new ArrayList<>();
        try {
        	PreparedStatement ps = con.prepareStatement(get_sample_by_status);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Sample s = new Sample(
                            rs.getString("sample_id"),
                            rs.getString("description"),
                            rs.getString("requested_by"),
                            rs.getString("status"),
                            rs.getTimestamp("created_date")
                    );
                    list.add(s);
                }
        }
  catch (SQLException e) {
      e.printStackTrace();
  }
        return list;
    }

}
