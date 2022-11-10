import { useEffect, useState } from "react";
import { getFetch, CONSTANTES } from "../../core/request";
import Buscador from "../Buscador";
import dataMock from '../CardList/data.json'
import Categorias from "../Categorias";
import Productos from "../Productos";

const Home = () => {

    const { CATEGORIAS_API_URL, PRODUCTOS_API_URL } = CONSTANTES;
    
    const [dataCategorias, setdataCategorias] = useState(dataMock);
    const [dataProductos, setDataProductos] = useState([]);

    const consultarCategorias = async()=> {     
      const dataCategoriasRespuesta = await getFetch(CATEGORIAS_API_URL);
      const dataProductosRespuesta = await getFetch(PRODUCTOS_API_URL);
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
            <Productos dataProductos={dataProductos}/>
        </>
    );
};

export default Home;