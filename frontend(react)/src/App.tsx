//Import React and useState
import React, { useState } from "react";

//Import components
import Message from "./Message";
import Configuration from "./Components/Configuration";
import TicketDisplay from "./Components/TicketDisplay";
import ControlPanel from "./Components/ControlPanel";
import LogDisplay from "./Components/LogDisplay";

//Import CSS file
import "./App.css";

//Define components and initialize their states
function App() {
  //Available tickets - Number of tickets left in the ticket pool
  //SetAvailableTickets - Function use to update the number of available tickets
  const [availableTickets, setAvailableTickets] = useState(0);

  //Logs - Array to store logs
  //SetLogs - Function use to update the logs
  const [logs, setLogs] = useState([]);

  //IsRunning - Boolean to check if the system is running
  const [isRunning, setIsRunning] = useState(false);

  //ShowLogs - Boolean to check if the logs should be displayed
  const [showLogs, setShowLogs] = useState(false);

  const handleStart = async () => {
    try {
      //Start the system by creating a POST request if user press start button
      const startResponse = await fetch(
        "http://localhost:8080/api/v1/ticketing-system/start",
        { method: "POST" }
      );

      if (!startResponse.ok) {
        throw new Error("Failed to start the system");
      }

      // Only proceed if start was successful
      setIsRunning(true);
      setShowLogs(false);
      alert("System started successfully!");

      //Catch any error and display an alert
    } catch (error) {
      console.error("Error:", error);
      setIsRunning(false);
      alert("An unexpected error occurred");
    }
  };

  //Function to stop the system if user press stop button
  const handleStop = () => {
    setIsRunning(false);
    setShowLogs(true);

    //Create a POST request to stop the system
    const stopSystem = fetch(
      "http://localhost:8080/api/v1/ticketing-system/stop",
      {
        method: "POST",
      }
    ).then((response) => {
      if (!response.ok) {
        throw new Error("Failed to stop the system");
      } else {
        alert("System stopped successfully!");
      }
    });

    //Create a GET request to get the logs from the server
    const getLogs = fetch(
      "http://localhost:8080/api/v1/ticketing-system/ticketLog",
      { method: "GET" }
    )
      .then((response) => response.json())
      .then((log) => setLogs(log))
      .catch((error) => {
        console.error("Error:", error);
        setIsRunning(true);
      });
  };

  //Function to check the number of available tickets
  //Create a GET request to get the number of available tickets from the server
  const handleCheck = async () => {
    try {
      const ticketCount = await fetch(
        "http://localhost:8080/api/v1/ticketing-system/ticketCount",
        { method: "GET" }
      );
      const data = await ticketCount.text();
      //Extract the number of available tickets from the response
      const availableTickets = parseInt(data.split(":")[1].trim());
      setAvailableTickets(availableTickets);

      //Catch any error and display an alert
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred while fetching ticket count.");
    }
  };

  //Render the components using bootstrap classes
  return (
    <div className="container-fluid">
      <Message />
      <div className="row">
        <div className="col-md-4">
          <Configuration />
        </div>
        <div className="col-md-8">
          {!showLogs ? (
            <TicketDisplay
              onCheck={handleCheck}
              availableTickets={availableTickets}
            />
          ) : (
            <LogDisplay logs={logs} />
          )}
        </div>
      </div>
      <div className="row mt-4">
        <div className="col-md-12 text-end">
          <ControlPanel onStart={handleStart} onStop={handleStop} />
        </div>
      </div>
    </div>
  );
}

export default App;
