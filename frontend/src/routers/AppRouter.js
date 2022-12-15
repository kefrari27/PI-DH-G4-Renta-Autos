import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import Footer from "../components/Footer";
import CrearCuenta from "../components/Formularios/CrearCuenta";
import CrearProducto from "../components/Formularios/CrearProductos";
import InicioSesion from "../components/Formularios/InicioSesion";
import Header from "../components/Header";
import Home from "../components/Home";
import Product from "../components/Producto";
import Reserva from "../components/Reserva";
import MisReservas from "../components/MisReservas";
import ProcesoExitoso from "../components/ProcesoExitoso";

const AppRouter = () => {
  const datosDeLocalStorage = localStorage.getItem('datosUsuario');

    return (
        <BrowserRouter>
        <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/crearCuenta" element={<CrearCuenta />} />
            <Route path="/inicioSesion" element={<InicioSesion />} />
            <Route path="/producto/:idProducto" element={<Product />} />
            <Route path="/producto/:idProducto/reserva" element={<Reserva />} />
            <Route path="/producto/:idProducto/reserva/procesoExitoso" element={<ProcesoExitoso />} />
            <Route path="/administracion" element={ JSON.parse(datosDeLocalStorage)?.rol && JSON.parse(datosDeLocalStorage)?.rol.id === 244 ? 
            <CrearProducto /> : <Home />} />
            <Route path="/producto/:idProducto/creacionProducto/procesoExitoso" element={<ProcesoExitoso descripcion='La creación del producto se ha realizo con éxito' />} />
            <Route path="/:idUsuario/misReservas" element={<MisReservas />} />
          </Routes>
        <Footer />
        </BrowserRouter>
      );
};

export default AppRouter;
