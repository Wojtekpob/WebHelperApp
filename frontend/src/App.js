import { Route, Routes } from "react-router-dom";
import Homepage from "./homepage";
import Login from "./login";
import Register from "./register";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Homepage />} />
      <Route path="register" element={<Register />} />
      <Route path="login" element={<Login />} />
    </Routes>
  );
}

export default App;
