import { useContext, useEffect, useState } from "react";
import DataProductosContext from "../../context/dataProductos/dataProducosContext";
import { getFetch, CONSTANTES } from "../../core/request";
import Buscador from "../Buscador";
import dataMock from '../CardList/data.json'
import Categorias from "../Categorias";
import Productos from "../Productos";

const Home = () => {

    const { CATEGORIAS_API_URL, PRODUCTOS_ALEATORIOS_API_URL } = CONSTANTES;
    
    const [dataCategorias, setdataCategorias] = useState(dataMock);
    const contextoDataProductos = useContext(DataProductosContext);
    const { dataProductos, setDataProductos } = contextoDataProductos;

    const consultarCategorias = async()=> {     
      const dataCategoriasRespuesta = await getFetch(CATEGORIAS_API_URL);
      const dataProductosRespuesta = await getFetch(`${PRODUCTOS_ALEATORIOS_API_URL}/4`);
      setDataProductos(dataProductosRespuesta);
      setdataCategorias(dataCategoriasRespuesta);
    }   

    useEffect(() => {
      consultarCategorias();          
    }, [])

    return (
        <>
            <Buscador />
            <Categorias dataCategorias={dataCategorias}/>
            {dataProductos.length > 0 && <Productos dataProductos={dataProductos}/>}
        </>
    );
};

export default Home;