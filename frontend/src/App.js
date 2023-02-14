import { Route, Routes } from "react-router-dom";
import Homepage from "./homepage";
import Register from "./register";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Homepage />} />
      <Route path="register" element={<Register />} />
    </Routes>
  );
}

export default App;
