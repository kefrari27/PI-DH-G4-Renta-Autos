import { useState } from "react";
import ControlMobileContext from "./controlMobileContext";

const ControlMobileProvider = ({children}) => {

    const [esVersionMobileHeaderMenu, setEsVersionMobileHeaderMenu] = useState(false);
    const setVersionMobileHeaderMenu = () => {
      setEsVersionMobileHeaderMenu(!esVersionMobileHeaderMenu);
    };
  
    const contextValue = {
        esVersionMobileHeaderMenu,
        setVersionMobileHeaderMenu
    }
  
    return (
        <ControlMobileContext.Provider value={contextValue}>
            {children}
        </ControlMobileContext.Provider>
    )

};

export default ControlMobileProvider;