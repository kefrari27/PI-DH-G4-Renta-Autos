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

    const { PRODUCTOS_ID_API_URL } = CONSTANTES;
      
    const [dataProducto, setDataProducto] = useState();
    const { idProducto } = useParams();

    const consultarProducto = async()=> {     
      const url = `${PRODUCTOS_ID_API_URL}/${idProducto}`
      const data = await getFetch(url);
      setDataProducto(data);
    }   

    useEffect(() => {
      consultarProducto();          
    }, [])

    return (
        <>
          <ProductHeader titulo={dataProducto?.titulo}/>
          <ProductLocation />
          <GaleriaImagenesProducto imagenes={dataProducto?.imagenes ? dataProducto?.imagenes : dataImagenesMock}/>
          <DescripcionProducto descripcion={dataProducto?.descripcion}/>
          <CaracteristicasProducto />
          <CalendarioReservasProducto />
          <PoliticaProducto />
        </>
    )
};

export default Product;