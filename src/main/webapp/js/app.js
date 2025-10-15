function handleAddSample(event) {
  event.preventDefault(); // stop normal form submission , prevent loading 

  const sample_id = document.getElementById("sampleid").value.trim();
  const description = document.getElementById("description").value.trim();
  const requestedBy = document.getElementById("requestedBy").value.trim();
  const status = document.getElementById("status").value.trim();

  // URLSearchParams() is used to create form-like data that can be sent in an HTTP POST request.  
  // Each .append() adds a key-value pair, like: sampleid=S-001&description=Blood Test&requestedBy=Samik&status=Created
  const formData = new URLSearchParams();
		  formData.append("sampleid", sample_id);
		  formData.append("description", description);
		  formData.append("requestedBy", requestedBy);
		  formData.append("status", status);

  // Send to servlet
  fetch("AddSample", {
		    method: "POST",
		    headers: { "Content-Type": "application/x-www-form-urlencoded" },
		    body: formData.toString()
  })
  
  
  // This portion will work after servlet 
  .then(response => {
		    if (response.status === 201) {
		      alert("Sample added successfully!");  
		    } 
			else if (response.status === 400) {
		      alert("Failed to add sample: Duplicate Sample ID or invalid data.");
		    } 
			else {
		      alert("Unexpected error: " + response.status);
		    }
			event.target.reset();	// So that all Sample Login page reset after clicking Add Sample button
  })
  
  //   event.target refers to the element that triggered the event — in this case, the HTML form "addSampleForm"
  // reset() clears all the input fields in the form, returning them to their initial values (usually blank).
  // Without event.target.reset(), your form would keep showing the old data even after the sample is successfully added.
  
  
  .catch(error => {
		    console.error("Error:", error);
		    alert("Could not connect to server.");
  });
}