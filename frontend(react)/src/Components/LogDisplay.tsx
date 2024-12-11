import React from "react";

//Define the interface of the props
interface LogDisplayProps {
  logs: string[];
}

//Define the functional component
const LogDisplay: React.FC<LogDisplayProps> = ({ logs }) => {
  return (
    <div className="card">
      <div className="card-body">
        <h3 className="card-title mb-4">Ticket Transaction Log</h3>
        <div className="table-responsive">
          <ul className="list-group">
            {/* Map over the array to rendor each log meassage as a list item. */}
            {logs.map((log, index) => (
              <li key={index} className="list-group-item">
                {/* Display the log message */}
                {log}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default LogDisplay;
