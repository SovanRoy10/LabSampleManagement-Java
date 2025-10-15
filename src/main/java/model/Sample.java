package model;

import java.sql.Timestamp;

public class Sample {
	
    private String sampleId;
    private String description;
    private String requestedBy;
    private String status;
    private Timestamp createdDate;

    // Default constructor
    public Sample() {}

    // Constructor without createdDate (for insert)
    public Sample(String sampleId, String description, String requestedBy, String status) {
        this.sampleId = sampleId;
        this.description = description;
        this.requestedBy = requestedBy;
        this.status = status;
    }

    // Full constructor (for non-insert activities)
    public Sample(String sampleId, String description, String requestedBy, String status, Timestamp createdDate) {
        this.sampleId = sampleId;
        this.description = description;
        this.requestedBy = requestedBy;
        this.status = status;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public String getSampleId() {
        return sampleId;
    }
    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public String getRequestedBy() {
        return requestedBy;
    }
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}