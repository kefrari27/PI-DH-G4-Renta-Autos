import React from 'react'
import { useParams } from "react-router-dom";
import CrearReserva from './CrearReserva';
import DetalleReserva from './DetalleReserva'; 
import HeaderReserva from './HeaderReserva';
import '../Reserva/styles.css';
import { CONSTANTES, getFetch } from "../../core/request";
import { useState, useEffect } from "react";
import CrearCuenta from '../Formularios/CrearCuenta';
import PoliticaProducto from '../Producto/PoliticaProducto';

const Reserva = () => {
    const { idProducto } = useParams();
    const { PRODUCTOS_ID_API_URL, CIUDADES_ID_API_URL } = CONSTANTES;
   
    const [dataProducto, setDataProducto] = useState({});
    const [dataUbicacionProducto, setDataUbicacionProducto] = useState({});
    
    let [fechaReservaInicial,setFechaReservaInicial]=useState();
    let [fechaReservaFinal,setFechaReservaFinal]=useState();
    const tomarFecha = (fechaInicio,fechaFin)=>{
      setFechaReservaInicial(fechaInicio.toDateString());
      setFechaReservaFinal(fechaFin.toDateString());
    }

    let [horaLlegada, setHoraLlegada] = useState();
    const tomarHora = (hora) => {
      setHoraLlegada(hora);
    }
    
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
    }, []) 
  
    const datosDeLocalStorage =JSON.parse( localStorage.getItem('datosUsuario'));
  
    return (
       <div>{datosDeLocalStorage ? ( <>
        <HeaderReserva titulo={dataProducto?.titulo}/>
        <div className='reserva__contenedor'>
          <CrearReserva lecturaFecha={tomarFecha} lecturaHora={tomarHora}/>
          <DetalleReserva 
            titulo={dataProducto?.titulo} 
            categoria={dataProducto?.categoria?.descripcion} 
            imagen={dataProducto && dataProducto?.imagenes && dataProducto?.imagenes[0]?.urlImagen}
            ubicacion={dataUbicacionProducto}
            fechaResIni={fechaReservaInicial}
            fechaResFin={fechaReservaFinal}
            hora={horaLlegada}
          />
        </div> 
          <PoliticaProducto />
        </> ): 
        <CrearCuenta/>} </div>  
          

         
        /* <>
          <HeaderReserva titulo={dataProducto?.titulo}/>
          <div className='reserva__contenedor'>
            <CrearReserva lecturaFecha={tomarFecha}/>
            <DetalleReserva 
              titulo={dataProducto?.titulo} 
              categoria={dataProducto?.categoria?.descripcion} 
              imagen={dataProducto && dataProducto?.imagenes && dataProducto?.imagenes[0]?.urlImagen}
              ubicacion={dataUbicacionProducto}
              fechaResIni={fechaReservaInicial}
              fechaResFin={fechaReservaFinal}
              />
          </div>
        </> */
    )
};

export default Reserva;
