import React from 'react'
import { useParams } from "react-router-dom";
import CrearReserva from './CrearReserva';
import DetalleReserva from './DetalleReserva'; 
import HeaderReserva from './HeaderReserva';
import '../Reserva/styles.css';
import { CONSTANTES, getFetch } from "../../core/request";
import { useState, useEffect } from "react";

const Reserva = () => {
    const { idProducto } = useParams();
    const { PRODUCTOS_ID_API_URL, CIUDADES_ID_API_URL } = CONSTANTES;
   
    const [dataProducto, setDataProducto] = useState({});
    console.log("🚀 ~ file: index.js ~ line 15 ~ Reserva ~ dataProducto", dataProducto)
    const [dataUbicacionProducto, setDataUbicacionProducto] = useState({});
    /* const datos_reserva = data */
     
    
    const consultarCiudad = async(ciudad) => {
      if(typeof(ciudad) === 'string' || typeof(ciudad) === 'number') {
        const url = `${CIUDADES_ID_API_URL}/${ciudad}`
        const data = await getFetch(url);
        setDataUbicacionProducto(data);
      }
    };
    const consultarProducto = async() => {
      const url = `${PRODUCTOS_ID_API_URL}/${idProducto}`
      await getFetch(url).then(respuesta => {
        setDataProducto(respuesta);
        const { ciudad } = respuesta;
        consultarCiudad(ciudad)
      })
    }

    useEffect(() => {
      consultarProducto();          
      console.log("🚀 ~ file: index.js ~ line 37 ~ useEffect ~ consultarProducto")
    }, []) 
  
    
  
    return (
        <>
          <HeaderReserva titulo={dataProducto?.titulo}/>
          <div className='reserva__contenedor'>
            <CrearReserva />
            <DetalleReserva 
            titulo={dataProducto?.titulo} 
            categoria={dataProducto?.categoria?.descripcion} 
            imagen={ dataProducto && dataProducto?.imagenes && dataProducto?.imagenes[0]?.urlImagen}
            />
          </div>
        </>
    )
};

export default Reserva;
