import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import Footer from "../components/Footer";
import CrearCuenta from "../components/Formularios/CrearCuenta";
import InicioSesion from "../components/Formularios/InicioSesion";
import Header from "../components/Header";
import Browser from "../components/Browser";
import Home from "../components/Home";

const AppRouter = () => {
    return (
        <BrowserRouter>
        <Header />
        <Browser /> 
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/crearCuenta" element={<CrearCuenta />} />
            <Route path="/inicioSesion" element={<InicioSesion />} />
          </Routes>
        <Footer />
        </BrowserRouter>
      );
};

export default AppRouter;
