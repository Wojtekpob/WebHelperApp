import React, { useEffect, useState } from "react";
import sendRequest from "../service/sendRequest";
import { useLocalState } from "../service/useLocalStorage";
import "./Assignments.css";
import { Link } from "react-router-dom";

const Assignments = () => {
  const [jwt, setJwt] = useLocalState("", "jwt");
  const [assignments, setAssignments] = useState(null);

  function getAssignments() {
    setAssignments(null);
    sendRequest("rest/assignments", "GET", jwt).then((response) => {
      setAssignments(response);
    });
  }

  useEffect(() => {
    getAssignments();
  }, []);

  return (
    <>
      <div id="assignments">
        {assignments ? (
          assignments.map((assginment) => (
            <div className="assignmentBox">
              <Link to={"/assignments/" + assginment.id}>
                {assginment.title}
              </Link>
              <br />
              {assginment.id}
            </div>
          ))
        ) : (
          <></>
        )}
      </div>
    </>
  );
};

export default Assignments;
