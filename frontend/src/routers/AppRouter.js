import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import Footer from "../components/Footer";
import CrearCuenta from "../components/Formularios/CrearCuenta";
import InicioSesion from "../components/Formularios/InicioSesion";
import Header from "../components/Header";
import Home from "../components/Home";
import Product from "../components/Producto";
import Reserva from "../components/Reserva";

const AppRouter = () => {
    return (
        <BrowserRouter>
        <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/crearCuenta" element={<CrearCuenta />} />
            <Route path="/inicioSesion" element={<InicioSesion />} />
            <Route path="/producto/:idProducto" element={<Product />} />
            <Route path="/producto/:idProducto/reserva" element={<Reserva />} />
          </Routes>
        <Footer />
        </BrowserRouter>
      );
};

export default AppRouter;
