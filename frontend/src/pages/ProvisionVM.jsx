import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { motion } from "framer-motion";
import axios from "axios";

// Logos de proveedores
import awsLogo from "../assets/aws.svg";
import azureLogo from "../assets/Azure.svg";
import gcpLogo from "../assets/gcp.svg";
import onpremLogo from "../assets/onprem.svg";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080/api/v1",
  timeout: 10000,
});

const logos = {
  aws: awsLogo,
  azure: azureLogo,
  gcp: gcpLogo,
  onprem: onpremLogo,
};

export default function ProvisionVM() {
  const location = useLocation();
  const [selectedProvider, setSelectedProvider] = useState("");

  // Estado del formulario
  const [formData, setFormData] = useState({
    proveedor: "",
    nombreVM: "",
    cpu: "",
    memoria: "",
    almacenamiento: "",
    region: "",
    keyPairName: "",
    firewallRules: "",
    publicIP: false,
    iops: "",
    memoryOptimization: false,
    diskOptimization: false,
  });

  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  // Detectar proveedor desde la URL
  useEffect(() => {
    const query = new URLSearchParams(location.search);
    const fromUrl = query.get("provider");
    if (fromUrl) {
      setSelectedProvider(fromUrl);
      setFormData((prev) => ({
        ...prev,
        proveedor: fromUrl,
      }));
    }
  }, [location]);

  // Regiones por proveedor
  const regiones = {
    aws: ["us-east-1", "us-west-2", "eu-west-1"],
    azure: ["eastus", "westeurope", "southamerica"],
    gcp: ["us-central1", "europe-west1", "southamerica-east1"],
    onprem: ["datacenter-local"],
  };

  // Manejo de cambios del formulario
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => {
      const newData = {
        ...prev,
        [name]: type === "checkbox" ? checked : value,
      };
      if (name === "proveedor") setSelectedProvider(value);
      return newData;
    });
  };

  // Envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setResult(null);

    try {
      setLoading(true);
      const payload = {
        ...formData,
        cpu: parseInt(formData.cpu),
        memoria: parseInt(formData.memoria),
        almacenamiento: parseInt(formData.almacenamiento),
        iops: formData.iops ? parseInt(formData.iops) : 0,
        firewallRules: formData.firewallRules
          ? formData.firewallRules.split(",").map((r) => r.trim())
          : [],
      };

      const response = await api.post("/provision", payload);
      setResult(response.data);
    } catch (err) {
      console.error(err);
      setError(
        err.response?.data?.detalle ||
          "Error al crear la máquina virtual. Verifica los datos o el servidor."
      );
    } finally {
      setLoading(false);
    }
  };

  // --- UI ---
  return (
    <div className="container mx-auto p-6 max-w-2xl">
      {/* LOGO Y ENCABEZADO */}
      {selectedProvider && (
        <motion.div
          initial={{ opacity: 0, y: -15 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
          className="flex flex-col items-center mb-6"
        >
          <img
            src={logos[selectedProvider]}
            alt={selectedProvider}
            className="h-20 mb-2"
          />
          <h1 className="text-3xl font-bold text-blue-700 text-center">
            Aprovisionar en {selectedProvider.toUpperCase()}
          </h1>
        </motion.div>
      )}

      {/* FORMULARIO */}
      <form
        onSubmit={handleSubmit}
        className="space-y-4 bg-white p-6 shadow rounded-xl"
      >
        {/* Proveedor */}
        <div>
          <label className="block font-medium">Proveedor *</label>
          <select
            name="proveedor"
            value={formData.proveedor}
            onChange={handleChange}
            className="border rounded p-2 w-full"
            required
          >
            <option value="">Seleccione proveedor</option>
            <option value="aws">AWS</option>
            <option value="azure">Azure</option>
            <option value="gcp">Google Cloud</option>
            <option value="onprem">On-Premise</option>
          </select>
        </div>

        {/* Nombre de la VM */}
        <div>
          <label className="block font-medium">Nombre de la VM *</label>
          <input
            type="text"
            name="nombreVM"
            value={formData.nombreVM}
            onChange={handleChange}
            className="border rounded p-2 w-full"
            placeholder="Ej: vm-produccion-01"
            required
          />
        </div>

        {/* CPU / Memoria / Disco */}
        <div className="grid grid-cols-3 gap-3">
          <div>
            <label className="block font-medium">CPU *</label>
            <input
              type="number"
              name="cpu"
              min="1"
              value={formData.cpu}
              onChange={handleChange}
              className="border rounded p-2 w-full"
              placeholder="Ej: 2"
              required
            />
          </div>
          <div>
            <label className="block font-medium">Memoria (GB) *</label>
            <input
              type="number"
              name="memoria"
              min="1"
              value={formData.memoria}
              onChange={handleChange}
              className="border rounded p-2 w-full"
              placeholder="Ej: 8"
              required
            />
          </div>
          <div>
            <label className="block font-medium">Disco (GB) *</label>
            <input
              type="number"
              name="almacenamiento"
              min="1"
              value={formData.almacenamiento}
              onChange={handleChange}
              className="border rounded p-2 w-full"
              placeholder="Ej: 100"
              required
            />
          </div>
        </div>

        {/* Región */}
        <div>
          <label className="block font-medium">Región *</label>
          <select
            name="region"
            value={formData.region}
            onChange={handleChange}
            className="border rounded p-2 w-full"
            required
            disabled={!formData.proveedor}
          >
            <option value="">Seleccione región</option>
            {formData.proveedor &&
              regiones[formData.proveedor]?.map((r) => (
                <option key={r} value={r}>
                  {r}
                </option>
              ))}
          </select>
        </div>

        {/* Par de claves */}
        <div>
          <label className="block font-medium">Nombre del par de claves</label>
          <input
            type="text"
            name="keyPairName"
            value={formData.keyPairName}
            onChange={handleChange}
            className="border rounded p-2 w-full"
            placeholder="Ej: aws-default-key"
          />
        </div>

        {/* Firewall rules */}
        <div>
          <label className="block font-medium">
            Reglas de firewall (separadas por coma)
          </label>
          <input
            type="text"
            name="firewallRules"
            value={formData.firewallRules}
            onChange={handleChange}
            className="border rounded p-2 w-full"
            placeholder="SSH, HTTP, HTTPS"
          />
        </div>

        {/* IOPS y Public IP */}
        <div className="grid grid-cols-2 gap-3">
          <div>
            <label className="block font-medium">IOPS</label>
            <input
              type="number"
              name="iops"
              min="0"
              value={formData.iops}
              onChange={handleChange}
              className="border rounded p-2 w-full"
              placeholder="Ej: 3000"
            />
          </div>
          <div className="flex items-center gap-2 mt-6">
            <input
              type="checkbox"
              name="publicIP"
              checked={formData.publicIP}
              onChange={handleChange}
            />
            <label>Asignar IP Pública</label>
          </div>
        </div>

        {/* Optimizaciones */}
        <div className="flex gap-4 items-center mt-4">
          <label className="font-medium">Optimizaciones:</label>
          <div className="flex gap-3">
            <label className="flex items-center gap-2">
              <input
                type="checkbox"
                name="memoryOptimization"
                checked={formData.memoryOptimization}
                onChange={handleChange}
              />
              Memoria
            </label>
            <label className="flex items-center gap-2">
              <input
                type="checkbox"
                name="diskOptimization"
                checked={formData.diskOptimization}
                onChange={handleChange}
              />
              Disco
            </label>
          </div>
        </div>

        {/* Botón */}
        <button
          type="submit"
          disabled={loading}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 w-full mt-4"
        >
          {loading ? "Creando..." : "Crear Máquina Virtual"}
        </button>
      </form>

      {/* MENSAJES */}
      {error && (
        <div className="bg-red-100 text-red-700 p-3 mt-4 rounded">
          ❌ {error}
        </div>
      )}

      {/* RESULTADO FINAL */}
      {result && (
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
          className="mt-8 bg-green-50 border border-green-300 rounded-2xl shadow-md p-6"
        >
          <div className="flex flex-col items-center mb-4">
            {selectedProvider && (
              <img
                src={logos[selectedProvider]}
                alt={selectedProvider}
                className="h-16 mb-2"
              />
            )}
            <h2 className="text-2xl font-bold text-green-800">
              ✅ Máquina Virtual Provisionada Correctamente
            </h2>
            <p className="text-sm text-gray-700">
              Proveedor:{" "}
              <span className="font-semibold uppercase">
                {result.proveedor}
              </span>
            </p>
          </div>

          <div className="grid grid-cols-2 gap-3 text-gray-800">
            <p><strong>VM:</strong> {result.nombreVM}</p>
            <p><strong>Región:</strong> {result.region || "—"}</p>
            <p><strong>CPU:</strong> {result.cpu} vCPUs</p>
            <p><strong>Memoria:</strong> {result.memoria} GB</p>
            <p><strong>Disco:</strong> {result.almacenamiento} GB</p>
          </div>

          <hr className="my-4 border-green-200" />

          <h3 className="text-lg font-semibold text-green-800 mb-2">
            Componentes creados:
          </h3>
          <ul className="list-disc list-inside text-gray-800 space-y-1">
            {result.network && <li><strong>Red:</strong> {result.network}</li>}
            {result.diskType && (
              <li>
                <strong>Tipo de Disco:</strong> {result.diskType}{" "}
                {result.iops && <span>(IOPS: {result.iops})</span>}
              </li>
            )}
            {result.so && <li><strong>Sistema Operativo:</strong> {result.so}</li>}
            {result.storage && (
              <li><strong>Almacenamiento:</strong> {result.storage}</li>
            )}
            {result.firewallRules?.length > 0 && (
              <li>
                <strong>Reglas de Firewall:</strong>{" "}
                {result.firewallRules.join(", ")}
              </li>
            )}
          </ul>

          <hr className="my-4 border-green-200" />

          <h3 className="text-lg font-semibold text-green-800 mb-2">
            Configuraciones Avanzadas:
          </h3>
          <div className="grid grid-cols-2 gap-3 text-gray-800">
            <p>
              <strong>Optimizaciones:</strong>{" "}
              {formData.memoryOptimization || formData.diskOptimization
                ? `${formData.memoryOptimization ? "Memoria " : ""}${
                    formData.diskOptimization ? "Disco" : ""
                  }`
                : "Ninguna"}
            </p>
            <p>
              <strong>IP Pública:</strong>{" "}
              {formData.publicIP ? "Asignada" : "No asignada"}
            </p>
            {formData.keyPairName && (
              <p>
                <strong>Par de claves:</strong> {formData.keyPairName}
              </p>
            )}
          </div>

          {result.detalle && (
            <div className="mt-4 text-gray-700 italic text-center">
              {result.detalle}
            </div>
          )}

          <div className="mt-3 text-green-800 font-semibold text-center">
            {result.estado || "Provisionamiento exitoso"}
          </div>
        </motion.div>
      )}
    </div>
  );
}
