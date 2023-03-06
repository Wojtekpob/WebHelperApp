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
      <div>
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
  //   const nameInput = React.useRef(null);
  //   const [isCircle, setIsCircle] = useState(true);
  //   const ref = React.useRef(null);
  //   function xdxd() {
  //     setIsCircle(!isCircle);
  //     // console.log(ref.current);
  //     // ref.innerHtml = "<div>XDXDDXXD</div>";
  //   }
  //   function clearInput() {
  //     nameInput.current.value = "";
  //   }
  //   return (
  //     <>
  //       <main>
  //         <button onClick={xdxd}>Chane shape</button>
  //         <div className={isCircle ? "circle" : "square"} ref={ref}></div>
  //         <input
  //           type="text"
  //           placeholder="Write your name..."
  //           ref={nameInput}
  //         ></input>
  //         <button onClick={clearInput}> Submit</button>
  //       </main>
  //     </>
  //   );
};

export default Assignments;
