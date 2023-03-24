import React, { useEffect, useState } from "react";
import sendRequest from "../service/sendRequest";
import { useLocalState } from "../service/useLocalStorage";
import "./Assignments.css";
import { Link, useNavigate } from "react-router-dom";
import hasRole from "../service/getRolesFromJwt";
import { Button, Card, Col, Form, InputGroup, Row } from "react-bootstrap";

const Assignments = () => {
  const [jwt, setJwt] = useLocalState("", "jwt");
  const [assignments, setAssignments] = useState(null);
  const [isTeacher, setIsTeacher] = useState(false);
  const [title, setTitle] = useState("");
  const [username, setUsername] = useState("");

  function getAssignments() {
    setAssignments(null);
    sendRequest("rest/assignments", "GET", jwt).then((response) => {
      setAssignments(response);
      setIsTeacher(hasRole(jwt, "TEACHER"));
    });
  }

  const navigate = useNavigate();

  function sendAssignmentCreationRequest() {
    let body = {
      username: username,
      title: title,
    };
    sendRequest("rest/assignments/create", "POST", jwt, body).catch((msg) =>
      alert(msg)
    );
  }

  const navigateToContacts = (id) => {
    navigate("/assignments/" + id);
  };

  useEffect(() => {
    getAssignments();
  }, []);

  return (
    <>
      <div id="assignments">
        {assignments ? (
          assignments.map((assginment) => (
            <div className="assignmentBox">
              <p className="h4 m-3">{assginment.title}</p>

              <br />
              <Col xs="auto">
                <Button
                  type="button"
                  className="mb-2 allign-items-center m-4"
                  onClick={() => navigateToContacts(assginment.id)}
                >
                  View Assignment
                </Button>
              </Col>
            </div>
          ))
        ) : (
          <></>
        )}
      </div>
      <div>
        {isTeacher ? (
          <Card className="align-items-center" style={{ width: "18rem" }}>
            <Card.Body>
              <Card.Title>Create an assignment</Card.Title>

              <Form>
                <Row className="align-items-center">
                  <Col xs="auto">
                    <Form.Label htmlFor="inlineFormInput" visuallyHidden>
                      Name
                    </Form.Label>
                    <Form.Control
                      className="mb-2"
                      id="inlineFormInput"
                      placeholder="Student username"
                      value={username}
                      onChange={(event) => setUsername(event.target.value)}
                    />
                  </Col>
                  <Col xs="auto">
                    <Form.Label htmlFor="inlineFormInputGroup" visuallyHidden>
                      Username
                    </Form.Label>
                    <InputGroup className="mb-2">
                      <Form.Control
                        id="inlineFormInputGroup"
                        placeholder="Title of the assignment"
                        value={title}
                        onChange={(event) => setTitle(event.target.value)}
                      />
                    </InputGroup>
                  </Col>
                  <Col xs="auto">
                    <Button
                      type="submit"
                      className="mb-2 allign-items-center"
                      onClick={() => sendAssignmentCreationRequest()}
                    >
                      Send
                    </Button>
                  </Col>
                </Row>
              </Form>
            </Card.Body>
          </Card>
        ) : (
          <></>
        )}
      </div>
    </>
  );
};

export default Assignments;
