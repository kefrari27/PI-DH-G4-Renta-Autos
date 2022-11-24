import { useState } from "react";
import DataProductosContext from "./dataProducosContext";

const DataProductosProvider = ({children}) => {

    const [dataProductos, setDataProductos] = useState([]);

    const dataProductosValue = {
        dataProductos,
        setDataProductos
    }

    return (
        <DataProductosContext.Provider value={dataProductosValue}>
            {children}
        </DataProductosContext.Provider>
    )

};

export default DataProductosProvider;