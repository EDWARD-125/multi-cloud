import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import cloudImage from "../assets/cloud.jpg";
import awsLogo from "../assets/aws.svg";
import azureLogo from "../assets/Azure.svg";
import gcpLogo from "../assets/gcp.svg";
import onpremLogo from "../assets/onprem.svg";

export default function Home() {
  const navigate = useNavigate();

  const providers = [
    { id: "aws", name: "AWS", logo: awsLogo },
    { id: "azure", name: "Azure", logo: azureLogo },
    { id: "gcp", name: "Google Cloud", logo: gcpLogo },
    { id: "onprem", name: "On-Premise", logo: onpremLogo },
  ];

  const handleClick = (provider) => {
    navigate(`/provision?provider=${provider.id}`, { state: { provider } });
  };

  return (
    <div
      className="min-h-[calc(100vh-160px)] flex items-center justify-center bg-cover bg-center bg-no-repeat"
      style={{ backgroundImage: `url(${cloudImage})` }}
    >
      <div className="bg-white/80 p-10 rounded-2xl shadow-xl max-w-5xl w-full text-center backdrop-blur-md">
        {/* HERO */}
        <motion.div
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
        >
          <h1 className="text-4xl md:text-5xl font-extrabold text-blue-800 mb-4 drop-shadow">
            🌩️ Multi-Cloud Provisioning Platform
          </h1>
          <p className="text-lg text-gray-700 mb-8">
            Gestiona y provisiona máquinas virtuales en AWS, Azure, GCP y entornos On-Premise con un solo clic.
          </p>
        </motion.div>

        {/* LOGOS */}
        <motion.div
          className="flex flex-wrap justify-center gap-12 mt-12"
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
                className="w-20 h-20 md:w-24 md:h-24 object-contain"
              />
              <p className="mt-2 text-gray-700 font-semibold text-lg">{provider.name}</p>
            </motion.div>
          ))}
        </motion.div>

        {/* TEXTO FINAL */}
        <motion.div
          className="mt-16"
          initial={{ opacity: 0, y: 30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.7, duration: 0.8 }}
        >
          <h2 className="text-2xl md:text-3xl font-bold text-blue-700 mb-2">
            Bienvenido a Multi-Cloud
          </h2>
          <p className="text-gray-600">Gestión centralizada de máquinas virtuales.</p>
        </motion.div>
      </div>
    </div>
  );
}
