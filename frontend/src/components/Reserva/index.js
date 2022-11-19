import React from 'react'
import { useParams } from "react-router-dom";
import CrearReserva from './CrearReserva';
import DetalleReserva from './DetalleReserva'; 
import HeaderReserva from './HeaderReserva';
import '../Reserva/styles.css';

const Reserva = () => {
  /*      const { idProducto } = useParams();
     const { PRODUCTOS_ID_API_URL } = CONSTANTES;
      
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
          <HeaderReserva/>
          <div className='reserva__contenedor'>
            <CrearReserva />
            <DetalleReserva />
          </div>
        </>
    )
};

export default Reserva;
