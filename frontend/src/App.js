import { Route, Routes } from "react-router-dom";
import Homepage from "./homepage";
import Login from "./login";
import PrivatePage from "./privatePage";
import PrivateRoute from "./privateRoute";
import Register from "./register";

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
    </Routes>
  );
}

export default App;
