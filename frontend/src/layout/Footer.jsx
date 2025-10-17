export default function Footer() {
  return (
    <footer className="bg-blue-800 text-white py-4 mt-10">
      <div className="container mx-auto text-center text-sm">
        <p>© {new Date().getFullYear()} Multi-Cloud Platform — Todos los derechos reservados.</p>
        <p className="text-blue-200 mt-1">
          Desarrollado con ❤️ por el equipo de Ingeniería
        </p>
      </div>
    </footer>
  );
}
