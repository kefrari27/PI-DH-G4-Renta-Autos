import { useState, useEffect } from 'react';
import { getFetch } from '../../core/request';
import Card from '../Card';
import dataMock from './data.json'
import "./styles.css"

const CardList = () => {   

    const CATEGORIAS_API_URL = 'http://localhost:8080/api/v1/categorias';   
    
    const [dataCategorias, setdataCategorias] = useState(dataMock);     

    const consultarCategorias = async()=>{     
      const data = await getFetch(CATEGORIAS_API_URL);     
      setdataCategorias(data);
    }   

    useEffect(() => {       
      consultarCategorias();          
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