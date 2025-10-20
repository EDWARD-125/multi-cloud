import React from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";
import { TemplateRegistryProvider } from "./contexts/TemplateRegistry.jsx";

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <TemplateRegistryProvider>
      <App />
    </TemplateRegistryProvider>
  </React.StrictMode>
);
