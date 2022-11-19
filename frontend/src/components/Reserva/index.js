import React from 'react'
import { useParams } from "react-router-dom";
import CalendarioReservasProducto from '../Producto/CalendarioReservasProducto';
import CrearReserva from './CrearReserva';
import DetalleReserva from './DetalleReserva';
const Reserva = () => {
        const { idProducto } = useParams();
  /*   const { PRODUCTOS_ID_API_URL } = CONSTANTES;
      
    const [dataProducto, setDataProducto] = useState();
    

    const consultarProducto = async()=> {     
      const url = `${PRODUCTOS_ID_API_URL}/${idProducto}`
      const data = await getFetch(url);
      setDataProducto(data);
    }   

    useEffect(() => {
      consultarProducto();          
    }, []) */

    return (
        <>
          {/* <ProductHeader titulo={dataProducto?.titulo}/>
          <ProductLocation />
          <GaleriaImagenesProducto imagenes={dataProducto?.imagenes ? dataProducto?.imagenes : dataImagenesMock}/>
          <DescripcionProducto descripcion={dataProducto?.descripcion}/>
          <CaracteristicasProducto />
          
          <PoliticaProducto /> */}
          <CrearReserva/>
           <CalendarioReservasProducto />
          <DetalleReserva/>

        </>
    )
};

export default Reserva;
