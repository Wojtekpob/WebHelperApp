import { Route, Routes } from "react-router-dom";
import Homepage from "./homepage";
import Login from "./login";
import PrivateRoute from "./privateRoute";
import Register from "./register";
import Assignments from "./assignments/Assignments";
import Assignment from "./assignment/Assignment";
import "bootstrap/dist/css/bootstrap.min.css";
import hasRole from "./service/getRolesFromJwt";
import { useLocalState } from "./service/useLocalStorage";
import TeacherAssignment from "./teacherAssignment/teacherAssignment";

function App() {
  const [jwt, setJwt] = useLocalState("", "jwt");
  return (
    <Routes>
      <Route path="/" element={<Homepage />} />
      <Route path="register" element={<Register />} />
      <Route path="login" element={<Login />} />
      <Route
        path="assignments"
        element={
          <PrivateRoute>
            <Assignments />
          </PrivateRoute>
        }
      ></Route>
      <Route
        path="assignments/:id"
        element={
          hasRole(jwt, "STUDENT") ? (
            <PrivateRoute>
              <Assignment />
            </PrivateRoute>
          ) : (
            <PrivateRoute>
              <TeacherAssignment />
            </PrivateRoute>
          )
        }
      ></Route>
    </Routes>
  );
}

export default App;
