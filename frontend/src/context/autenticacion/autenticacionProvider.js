import { useState } from "react";
import AutenticacionContext from "./autenticacionContext";

const AutenticacionProvider = ({children}) => {

    const [autenticacionEstado, setAutenticacionEstado] = useState(localStorage.getItem('token') || null);
    const [datosUsuario, setDatosUsuario] = useState({});

    const autenticacionContextValue = {
        estadoAutenticacion: autenticacionEstado, 
        setAutenticacionEstado,
        setDatosUsuario,
        datosUsuario
    }

    return (
        <AutenticacionContext.Provider value={autenticacionContextValue}>
            {children}
        </AutenticacionContext.Provider>
    )

};

export default AutenticacionProvider;