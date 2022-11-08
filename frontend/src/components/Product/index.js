import CaracteristicasProducto from "./CaracteristicasProducto";
import DescripcionProducto from "./DescripcionProducto";
import GaleriaImagenesProducto from "./GaleriaImagenesProducto";
import PoliticaProducto from "./PoliticaProducto";
import ProductHeader from "./ProductHeader";
import ProductLocation from "./ProductLocation";import * as React from 'react';

const Product = () => {

    return (
        <>
          <ProductHeader />
          <ProductLocation />
          <GaleriaImagenesProducto />
          <DescripcionProducto />
          <CaracteristicasProducto />
          <PoliticaProducto />
        </>
    )
};

export default Product;