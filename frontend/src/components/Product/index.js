import CaracteristicasProducto from "./CaracteristicasProducto";
import DescripcionProducto from "./DescripcionProducto";
import GaleriaImagenesProducto from "./GaleriaImagenesProducto";
import PoliticaProducto from "./PoliticaProducto";
import ProductHeader from "./ProductHeader";
import ProductLocation from "./ProductLocation";import * as React from 'react';
import CalendarioReservasProducto from "./CalendarioReservasProducto";

const Product = () => {

    return (
        <>
          <ProductHeader />
          <ProductLocation />
          <GaleriaImagenesProducto />
          <DescripcionProducto />
          <CaracteristicasProducto />
          <CalendarioReservasProducto />
          <PoliticaProducto />
        </>
    )
};

export default Product;