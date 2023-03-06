import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import sendRequest from "../service/sendRequest";
import { useLocalState } from "../service/useLocalStorage";
import "./Assignment.css";

const Assignment = () => {
  const [jwt, setJwt] = useLocalState("", "jwt");
  const [title, setTitle] = useState("");
  const [comments, setComments] = useState("");

  let params = useParams();
  let assignmentId = params.id;

  useEffect(() => {
    getAssignment(assignmentId);
  }, []);

  function getAssignment(id) {
    sendRequest("/rest/assignments/assignment?id=" + id, "GET", jwt).then(
      (response) => {
        setTitle(response.title);
        setComments(response.comments);
      }
    );
  }

  function submitChanges() {
    let body = {
      comments: comments,
      assignmentId: assignmentId,
    };
    sendRequest("/rest/assignments/update", "PATCH", jwt, body);
  }

  return (
    <>
      <div id="contener">
        <div>{title}</div>
        <textarea
          type="textarea"
          id="comment_box"
          value={comments}
          placeholder="Comments..."
          onChange={(commentsEvent) => setComments(commentsEvent.target.value)}
        />
        <button onClick={() => submitChanges()}>Submit Assignment</button>
      </div>
    </>
  );
};

export default Assignment;
