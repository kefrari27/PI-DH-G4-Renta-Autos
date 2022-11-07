import { useState, useEffect } from 'react';
import { consultarProductos, consultarCaracteristicas, consultarCiudades, consultarImagenes } from '../../core/request';
import Card from '../Card';
import Data from'./data2.json'
import "./styles.css"
const CardList = () => {
   
    const infoItems= Data;
    const [dataCategorias, setdataCategorias] = useState(infoItems);

    const CATEGORIAS_API_URL = 'http://localhost:8080/api/v1/categorias';
    const consultarCategorias = async()=>{
      const url = CATEGORIAS_API_URL;    
      const response = await fetch(url);
      const data = await response.json();
      setdataCategorias(data);
    }

    useEffect(() => {
       
      consultarCategorias();       
      
      //const caracteristicas = consultarCaracteristicas();
      // const ciudades = consultarCiudades();
      // const imagenes = consultarImagenes();
      // const productos = consultarProductos(); 
         
           
    }, [])

    
    return (
      <>
        <h2>Categorias</h2>  
        <div className='container'>
          {
            dataCategorias.map((item,index)=>
              <Card key={index}  itemInfo={item}/> 
            )
          }        
        </div>        
      </>
    )
}

export default CardList;