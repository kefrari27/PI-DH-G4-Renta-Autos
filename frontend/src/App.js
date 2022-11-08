import { useState } from "react";
import RentaCarrosAPP from "./RentaCarrosApp";
import contextAplicacion from "./provider/contextAutenticacion";

import '../src/styles.css'

function App() {

  /** refactorizar el contexto para que sea escalable */
  
  const [autenticacionEstado, setAutenticacionEstado] = useState(false);
  const [esVersionMobileHeaderMenu, setEsVersionMobileHeaderMenu] = useState(false);
  const login = () => {
    setAutenticacionEstado(!autenticacionEstado);
  };

  const setVersionMobileHeaderMenu = () => {
    setEsVersionMobileHeaderMenu(!esVersionMobileHeaderMenu);
  };

  const contextValue = {
    autenticacion:
      { 
        estadoAutenticacion: autenticacionEstado, 
        login
      },
    versionMobileHeaderMenu: {
      esVersionMobileHeaderMenu,
      setVersionMobileHeaderMenu
    }
  }

  return (
    <contextAplicacion.Provider value={contextValue}>
      <RentaCarrosAPP />
    </contextAplicacion.Provider>
  )
}

export default App;
