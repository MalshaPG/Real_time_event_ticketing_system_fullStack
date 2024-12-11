import React from "react";

//Define the ControlPanelProps interface
interface ControlPanelProps {
  onStart: () => void;
  onStop: () => void;
}

//Define the functional component
const ControlPanel: React.FC<ControlPanelProps> = ({ onStart, onStop }) => {
  return (
    <div className="d-flex justify-content-end">
      {/* Event handler call the onStart function when user clicks the Start button. */}
      <button className="btn btn-success btn-lg me-2" onClick={onStart}>
        Start
      </button>

      {/* Event handler calls onStop function when user clicks the Stop button. */}
      <button className="btn btn-danger btn-lg" onClick={onStop}>
        Stop
      </button>
    </div>
  );
};

export default ControlPanel;
