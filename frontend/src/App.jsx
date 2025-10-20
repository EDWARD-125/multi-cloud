// frontend/src/App.jsx
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import ProvisionVM from "./pages/ProvisionVM";
import NotFound from "./pages/NotFound";
import LayoutBase from "./layout/LayoutBase";
import TemplateEditor from "./pages/TemplateEditor"; // 👈 IMPORTANTE

function App() {
  return (
    <Router>
      <LayoutBase>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/provision" element={<ProvisionVM />} />

          {/* 👇 NUEVA RUTA */}
          <Route path="/editor/:id" element={<TemplateEditor />} />

          <Route path="*" element={<NotFound />} />
        </Routes>
      </LayoutBase>
    </Router>
  );
}

export default App;
