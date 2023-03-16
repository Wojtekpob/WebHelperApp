import React, { useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import sendRequest from "../service/sendRequest";
import { useLocalState } from "../service/useLocalStorage";

const Login = () => {
  //"" is default value in useState function
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [jwt, setJwt] = useLocalState("", "jwt");
  function sendLoginRequest() {
    //default request is get
    //logowanie do stworzonego użytkownika

    const requestBody = {
      username: username,
      password: password,
    };

    sendRequest("rest/auth/login", "POST", "", requestBody)
      .then((data) => {
        console.log(data);
        setJwt(data.jwtToken);
        window.location.href = "assignments";
      })
      .catch((message) => {
        alert(message);
      });
  }

  return (
    <>
      <Container className="mt-3">
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Username</Form.Label>
          <Form.Control
            className="sm-2 w-50"
            type="username"
            id="username"
            placeholder="Enter username"
            value={username}
            onChange={(usernameEvent) =>
              setUsername(usernameEvent.target.value)
            }
          />
        </Form.Group>
        <Form.Group className="mb-3 w-50" controlId="formBasicEmail">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            id="password"
            placeholder="Enter password"
            value={password}
            onChange={(passwordEvent) =>
              setPassword(passwordEvent.target.value)
            }
          />
        </Form.Group>
        <Row>
          <Col>
            <Button
              className="m-2"
              size="lg"
              id="submit"
              type="button"
              onClick={() => sendLoginRequest()}
              variant="success"
            >
              Login
            </Button>
            <a href="register">Register</a>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Login;
