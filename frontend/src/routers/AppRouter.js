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
import Product from "../components/Product";

const AppRouter = () => {
    return (
        <BrowserRouter>
        <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/crearCuenta" element={<CrearCuenta />} />
            <Route path="/inicioSesion" element={<InicioSesion />} />
            <Route path="/producto" element={<Product />} />
          </Routes>
        <Footer />
        </BrowserRouter>
      );
};

export default AppRouter;
