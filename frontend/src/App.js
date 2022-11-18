import RentaCarrosAPP from "./RentaCarrosApp";
import AutenticacionProvider from "./context/autenticacion/autenticacionProvider";
import ControlMobileProvider from "./context/controlMobile/controlMobileProvider";

import '../src/styles.css'

function App() {

  return (
    <ControlMobileProvider>
      <AutenticacionProvider>
        <RentaCarrosAPP />
      </AutenticacionProvider>
    </ControlMobileProvider>
  )
}

export default App;
