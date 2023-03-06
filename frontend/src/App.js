import { Route, Routes } from "react-router-dom";
import Homepage from "./homepage";
import Login from "./login";
import PrivatePage from "./privatePage";
import PrivateRoute from "./privateRoute";
import Register from "./register";
import Assignments from "./assignments/Assignments";
import Assignment from "./assignment/Assignment";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Homepage />} />
      <Route path="register" element={<Register />} />
      <Route path="login" element={<Login />} />
      <Route
        path="/private"
        element={
          <PrivateRoute>
            <PrivatePage />
          </PrivateRoute>
        }
      />
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
          <PrivateRoute>
            <Assignment />
          </PrivateRoute>
        }
      ></Route>
    </Routes>
  );
}

export default App;
