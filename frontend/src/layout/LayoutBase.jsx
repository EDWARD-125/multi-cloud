import Navbar from "./Navbar";
import Footer from "./Footer";

export default function LayoutBase({ children }) {
  return (
    <div className="flex flex-col min-h-screen bg-gray-100">
      {/* Navbar superior */}
      <Navbar />

      {/* Contenido principal */}
      <main className="flex-grow container mx-auto px-6 py-10">
        {children}
      </main>

      {/* Footer */}
      <Footer />
    </div>
  );
}
