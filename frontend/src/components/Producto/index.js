import CaracteristicasProducto from "./CaracteristicasProducto";
import DescripcionProducto from "./DescripcionProducto";
import GaleriaImagenesProducto from "./GaleriaImagenesProducto";
import PoliticaProducto from "./PoliticaProducto";
import ProductHeader from "./ProductHeader";
import ProductLocation from "./ProductLocation";
import * as React from 'react';
import CalendarioReservasProducto from "./CalendarioReservasProducto";
import { CONSTANTES, getFetch } from "../../core/request";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import dataImagenesMock from "./GaleriaImagenesProducto/dataMock.json";

const Product = () => {

    const { PRODUCTOS_ID_API_URL, CIUDADES_ID_API_URL } = CONSTANTES;
      
    const [dataProducto, setDataProducto] = useState({});
    const [dataUbicacionProducto, setDataUbicacionProducto] = useState({});
    const { idProducto } = useParams();

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
        const idCiudad = ciudad.id;
        consultarCiudad(idCiudad)
      })
    }

    useEffect(() => {
      consultarProducto();
    }, []);
    
    return (
        <>
          <ProductHeader titulo={dataProducto?.titulo}/>
          <ProductLocation ubicacion={dataUbicacionProducto}/>
          <GaleriaImagenesProducto imagenes={dataProducto?.imagenes ? dataProducto?.imagenes : dataImagenesMock}/>
          <DescripcionProducto descripcion={dataProducto?.descripcion}/>
          <CaracteristicasProducto caracteristicas={dataProducto?.caracteristicas} />
          <CalendarioReservasProducto identificador={dataProducto?.id} />
          <PoliticaProducto politicas={dataProducto?.politicas} />
        </>
    )
};

export default Product;