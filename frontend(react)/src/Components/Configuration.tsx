import React, { useState } from "react";

//Define the ConfigurationData interface
interface ConfigurationData {
  totalTickets: string;
  ticketReleaseRate: string;
  customerRetrievalRate: string;
  maxTicketCapacity: string;
}

//Define the Configuration component and initialize its state
const Configuration: React.FC = () => {
  const [formData, setFormData] = useState<ConfigurationData>({
    totalTickets: "",
    ticketReleaseRate: "",
    customerRetrievalRate: "",
    maxTicketCapacity: "",
  });

  //Event handler for updating the form data when a change happens
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  //Event handler for submitting the form
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // Client-side validation
    const totalTickets = parseInt(formData.totalTickets);
    const ticketReleaseRate = parseInt(formData.ticketReleaseRate);
    const customerRetrievalRate = parseInt(formData.customerRetrievalRate);
    const maxTicketCapacity = parseInt(formData.maxTicketCapacity);

    //Validate the inputs
    if (
      totalTickets < 1 ||
      totalTickets > 20 ||
      ticketReleaseRate < 5 ||
      ticketReleaseRate > 10 ||
      customerRetrievalRate < 5 ||
      customerRetrievalRate > 10 ||
      maxTicketCapacity < 1 ||
      maxTicketCapacity > 20
    ) {
      alert("Please ensure all values are within the specified ranges.");
      return;
    }

    //Create a POST request to submit the configuration form
    fetch("http://localhost:8080/api/v1/configuration", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      //Handle server responses
      .then((response) => {
        if (response.ok) {
          alert("Configuration form submitted successfully!");
        } else {
          alert("Error submitting configuration!");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("An error occurred.");
      });
  };

  //Render the configuration form using bootstrap
  return (
    <div className="card shadow">
      <div className="card-body">
        <h3 className="card-title mb-4">Configuration Form</h3>
        <form onSubmit={handleSubmit}>
          {/* Form group for Total tickets */}
          <div className="mb-3">
            <label htmlFor="totalTickets" className="form-label">
              Total Number of Tickets (1-5):
            </label>
            <input
              type="number"
              className="form-control"
              id="totalTickets"
              name="totalTickets"
              value={formData.totalTickets}
              onChange={handleChange}
              required
              min="1"
              max="5"
            />
          </div>

          {/* Form group for Ticket Release Rate */}
          <div className="mb-3">
            <label htmlFor="ticketReleaseRate" className="form-label">
              Ticket Release Rate (5-10):
            </label>
            <input
              type="number"
              className="form-control"
              id="ticketReleaseRate"
              name="ticketReleaseRate"
              value={formData.ticketReleaseRate}
              onChange={handleChange}
              required
              min="5"
              max="10"
            />
          </div>

          {/* Form group for Customer retrieval rate */}
          <div className="mb-3">
            <label htmlFor="customerRetrievalRate" className="form-label">
              Customer Retrieval Rate (5-10):
            </label>
            <input
              type="number"
              className="form-control"
              id="customerRetrievalRate"
              name="customerRetrievalRate"
              value={formData.customerRetrievalRate}
              onChange={handleChange}
              required
              min="5"
              max="10"
            />
          </div>

          {/* Form group for Maximum ticket capacity */}
          <div className="mb-3">
            <label htmlFor="maxTicketCapacity" className="form-label">
              Maximum Ticket Capacity (1-20):
            </label>
            <input
              type="number"
              className="form-control"
              id="maxTicketCapacity"
              name="maxTicketCapacity"
              value={formData.maxTicketCapacity}
              onChange={handleChange}
              required
              min="1"
              max="20"
            />
          </div>

          {/* Submit button */}
          <button type="submit" className="btn btn-primary btn-lg w-100">
            Submit Configuration
          </button>
        </form>
      </div>
    </div>
  );
};

export default Configuration;
