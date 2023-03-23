import React, { useEffect, useState } from "react";
import sendRequest from "../service/sendRequest";
import { useLocalState } from "../service/useLocalStorage";
import { Link, useParams } from "react-router-dom";
import { Card } from "react-bootstrap";

const TeacherAssignment = () => {
  const [jwt, setJwt] = useLocalState("", "jwt");
  const [title, setTitle] = useState("");
  const [comments, setComments] = useState("");
  const [username, setUsername] = useState("");

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
        setUsername(response.assignedTo.name);
      }
    );
  }
  return (
    <>
      <div id="contener">
        <Card>
          <Card.Header as="h5">Title: {title}</Card.Header>
          <Card.Header as="h7">Assigned to: {username}</Card.Header>
          <Card.Title>Comments:</Card.Title>
          <Card.Body>{comments}</Card.Body>
        </Card>
      </div>
    </>
  );
};

export default TeacherAssignment;
