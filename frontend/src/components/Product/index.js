import CaracteristicasProducto from "./CaracteristicasProducto";
import DescripcionProducto from "./DescripcionProducto";
import PoliticaProducto from "./PoliticaProducto";
import ProductHeader from "./ProductHeader";
import ProductLocation from "./ProductLocation";

const Product = () => {

    return (
        <>
          <ProductHeader />
          <ProductLocation />
          <DescripcionProducto />
          <CaracteristicasProducto />
          <PoliticaProducto />
        </>
    )
};

export default Product;