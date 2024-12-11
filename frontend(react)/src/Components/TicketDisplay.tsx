import React from "react";
import ticket from "../assets/ticket-icon.svg";

// Define interface for TicketDisplayProps
interface TicketDisplayProps {
  availableTickets: number;
  onCheck: () => void;
}

// Define functional component
const TicketDisplay: React.FC<TicketDisplayProps> = ({
  availableTickets,
  onCheck,
}) => {
  // Return JSX
  return (
    <div className="card">
      <div className="card-body">
        <h3 className="card-title">Current Ticket Availability</h3>
        <div className="d-flex flex-wrap justify-content-start align-items-center mb-3">
          {/* Create an array which's length is equal to available ticket 
          and display ticket image for each available ticket */}
          {Array.from({ length: availableTickets }).map((_, i) => (
            <div key={i} className="Ticket m-1">
              <img src={ticket} alt="ticket" className="img-fluid" />
            </div>
          ))}
        </div>
        <p className="card-text fs-4">Available Tickets: {availableTickets}</p>
      </div>

      {/* Button to check available tickets */}
      <button className="btn btn-primary btn-lg" onClick={onCheck}>
        See Available Tickets
      </button>
    </div>
  );
};

export default TicketDisplay;
