import React, { useState } from "react";
import sendRequest from "../service/sendRequest";
import { useLocalState } from "../service/useLocalStorage";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [jwt, setJwt] = useLocalState("", "jwt");

  function sendRegistrationRequest() {
    const requestBody = {
      name: name,
      surname: surname,
      username: username,
      password: password,
    };
    sendRequest("rest/auth/register", "POST", jwt, requestBody)
      .then(() => {
        window.location.href = "login";
      })
      .catch((message) => {
        window.alert(message);
      });
    // fetch("rest/auth/register", {
    //   headers: {
    //     "Content-Type": "application/json",
    //   },
    //   method: "POST",
    //   body: JSON.stringify(requestBody),
    // })
    //   .then((response) => {
    //     if (response.status === 200) return response.json();
    //     else return Promise.reject("Invalid Data");
    //   })
    //   .then(() => {
    //     window.location.href = "login";
    //   })
    //   .catch((message) => {
    //     window.alert(message);
    //   });
  }

  return (
    <>
      <div>
        <label htmlFor="name">Name</label>
        <input
          id="name"
          value={name}
          onChange={(event) => setName(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="surname">Surname</label>
        <input
          id="surname"
          value={surname}
          onChange={(event) => setSurname(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="username">Username</label>
        <input
          id="username"
          value={username}
          onChange={(event) => setUsername(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
      </div>
      <div>
        <button
          id="submit"
          type="button"
          onClick={() => sendRegistrationRequest()}
        >
          Register
        </button>
      </div>
    </>
  );
};

export default Register;
