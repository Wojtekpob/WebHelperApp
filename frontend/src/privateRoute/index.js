import React, { useState } from "react";
import { useLocalState } from "../service/useLocalStorage";
import { Navigate } from "react-router-dom";
import sendRequest from "../service/sendRequest";

const PrivateRoute = ({ children }) => {
  const [jwt, setJwt] = useLocalState("", "jwt");
  const [loading, setLoading] = useState(true);
  const [isValid, setIsValid] = useState(null);

  if (jwt) {
    sendRequest(`/rest/auth/validate?token=${jwt}`, "GET", jwt).then(
      (isValid) => {
        setIsValid(isValid);
        setLoading(false);
      }
    );
  } else {
    return <Navigate to="/login" />;
  }
  return loading ? (
    <div>Loading</div>
  ) : isValid ? (
    children
  ) : (
    <Navigate to="/login" />
  );
};

export default PrivateRoute;
