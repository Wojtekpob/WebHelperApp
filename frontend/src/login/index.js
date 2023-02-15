import React, { useState } from "react";
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
    fetch("rest/auth/login", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "post",
      body: JSON.stringify(requestBody),
    })
      .then((response) => {
        if (response.status === 200) return response.json();
        else return Promise.reject("Invalid login or password");
      })
      .then((data) => {
        setJwt(data.jwtToken);
        window.location.href = "assignments";
      })
      .catch((message) => {
        alert(message);
      });
  }

  return (
    <>
      <div>
        <label htmlFor="username">Username</label>
        <input
          type="username"
          id="username"
          value={username}
          onChange={(usernameEvent) => setUsername(usernameEvent.target.value)}
        />
      </div>
      <div>
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(passwordEvent) => setPassword(passwordEvent.target.value)}
        />
      </div>
      <div>
        <button id="submit" type="button" onClick={() => sendLoginRequest()}>
          Login
        </button>
      </div>
      <a href="register">Register</a>
    </>
  );
};

export default Login;
