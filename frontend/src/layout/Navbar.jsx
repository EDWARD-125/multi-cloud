import { Link, NavLink } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="bg-blue-700 text-white shadow-lg">
      <div className="container mx-auto px-6 py-3 flex justify-between items-center">
        {/* Logo */}
        <Link to="/" className="text-2xl font-bold tracking-wide">
           Multi-Cloud
        </Link>

        {/* Menú de navegación */}
        <div className="space-x-6">
          <NavLink
            to="/"
            className={({ isActive }) =>
              isActive
                ? "font-semibold border-b-2 border-white pb-1"
                : "hover:text-blue-200 transition"
            }
          >
            Inicio
          </NavLink>

          <NavLink
            to="/provision"
            className={({ isActive }) =>
              isActive
                ? "font-semibold border-b-2 border-white pb-1"
                : "hover:text-blue-200 transition"
            }
          >
            Provisionar VM
          </NavLink>
        </div>
      </div>
    </nav>
  );
}
