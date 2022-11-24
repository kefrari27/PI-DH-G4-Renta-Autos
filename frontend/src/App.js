import RentaCarrosAPP from "./RentaCarrosApp";
import AutenticacionProvider from "./context/autenticacion/autenticacionProvider";
import ControlMobileProvider from "./context/controlMobile/controlMobileProvider";
import DataProductosProvider from "./context/dataProductos/dataProductosProvider";

import '../src/styles.css'

function App() {

  return (
    <DataProductosProvider>
      <ControlMobileProvider>
        <AutenticacionProvider>
          <RentaCarrosAPP />
        </AutenticacionProvider>
      </ControlMobileProvider>
    </DataProductosProvider>
  )
}

export default App;
