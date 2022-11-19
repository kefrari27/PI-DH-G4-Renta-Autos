import { useState } from "react";
import AutenticacionContext from "./autenticacionContext";

const AutenticacionProvider = ({children}) => {

    const [autenticacionEstado, setAutenticacionEstado] = useState(false);
    const login = () => {
      setAutenticacionEstado(!autenticacionEstado);
    };

    const autenticacionContextValue = {
        estadoAutenticacion: autenticacionEstado, 
        login
    }

    return (
        <AutenticacionContext.Provider value={autenticacionContextValue}>
            {children}
        </AutenticacionContext.Provider>
    )

};

export default AutenticacionProvider;