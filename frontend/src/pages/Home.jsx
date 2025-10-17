import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import cloudImage from "../assets/cloud.jpg";
import awsLogo from "../assets/aws.svg";
import azureLogo from "../assets/Azure.svg";
import gcpLogo from "../assets/gcp.svg";
import onpremLogo from "../assets/onprem.svg";

export default function Home() {
  const navigate = useNavigate();

  // Definimos los proveedores
  const providers = [
    { id: "aws", name: "AWS", logo: awsLogo },
    { id: "azure", name: "Azure", logo: azureLogo },
    { id: "gcp", name: "Google Cloud", logo: gcpLogo },
    { id: "onprem", name: "On-Premise", logo: onpremLogo },
  ];

  const handleClick = (provider) => {
    // 🔹 Redirige al formulario con el proveedor en la URL y en el estado
    navigate(`/provision?provider=${provider.id}`, { state: { provider } });
  };

  return (
    <section
      className="flex flex-col items-center text-center py-20 min-h-[80vh] bg-cover bg-center bg-no-repeat"
      style={{ backgroundImage: `url(${cloudImage})` }}
    >
      <div className="bg-white/70 p-10 rounded-2xl shadow-lg max-w-4xl backdrop-blur-md">
        {/* HERO PRINCIPAL */}
        <motion.div
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
        >
          <h1 className="text-5xl font-extrabold text-blue-800 mb-4 drop-shadow">
            🌩️ Multi-Cloud Provisioning Platform
          </h1>
          <p className="text-lg text-gray-700 mb-8">
            Gestiona y provisiona máquinas virtuales en AWS, Azure, GCP y entornos On-Premise con un solo clic.
          </p>
        </motion.div>

        {/* LOGOS CON ENLACES */}
        <motion.div
          className="flex flex-wrap justify-center gap-10 mt-16"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5, duration: 0.8 }}
        >
          {providers.map((provider, index) => (
            <motion.div
              key={index}
              className="flex flex-col items-center cursor-pointer hover:scale-110 transition-transform"
              whileHover={{ scale: 1.1 }}
              onClick={() => handleClick(provider)}
            >
              <img
                src={provider.logo}
                alt={provider.name}
                className="w-20 h-20 object-contain mx-auto"
              />
              <p className="mt-2 text-gray-700 font-medium">{provider.name}</p>
            </motion.div>
          ))}
        </motion.div>

        {/* SECCIÓN INFERIOR */}
        <motion.div
          className="mt-20"
          initial={{ opacity: 0, y: 30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.7, duration: 0.8 }}
        >
          <h2 className="text-3xl font-bold text-blue-700 mb-2">
            Bienvenido a Multi-Cloud
          </h2>
          <p className="text-gray-600">
            Gestión centralizada de máquinas virtuales.
          </p>
        </motion.div>
      </div>
    </section>
  );
}
