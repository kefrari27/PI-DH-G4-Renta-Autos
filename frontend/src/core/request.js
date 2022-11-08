
/* 
Información end-points

const API_URL = 'http://localhost:8080/api/v1';
const CARACTERISTICAS_API_URL = `${API_URL}/caracteristicas`;
const CATEGORIAS_API_URL = `${API_URL}/categorias`;
const CIUDADES_API_URL = `${API_URL}/ciudades`;
const IMAGENES_API_URL = `${API_URL}/imagenes`;
const PRODUCTOS_API_URL = `${API_URL}/productos`;

const CATEGORIAS_ID_API_URL = `${ CATEGORIAS_API_URL}/{id}`;
const CIUDADES_ID_API_URL = `${CIUDADES_API_URL}/{id}`;
const IMAGENES_ID_API_URL = `${IMAGENES_API_URL}{id}`;
const PRODUCTOS_ID_API_URL = `${PRODUCTOS_API_URL}/{id}`;

const IMAGENES_POR_PRODUCTOID_API_URL = `${PRODUCTOS_API_URL}/imagenesByProductoId/{id}`;
const ADICIONAR_CARACTERISTICA_PRODUCTO_API_URL = `${PRODUCTOS_API_URL}/addCaracteristica`;
const PRODUCTOS_POR_PRODUCTOID_API_URL = `${PRODUCTOS_API_URL}/addCaracteristica`;
const PRODUCTOS_POR_CATEGORIA_API_URL = `${PRODUCTOS_API_URL}/productosByCategoria/{id}`;
const PRODUCTOS_POR_CIUDAD_API_URL = `${PRODUCTOS_API_URL}/productosByCiudad/{id}`;
const PRODUCTOS_ALEATORIOS_API_URL = `${PRODUCTOS_API_URL}/productosAleatorios/{cantidad}`; 

*/


const getFetch = async(url)=>{      
    const response = await fetch(url);
    const data = await response.json();
    return data;
}

const postFetch = async(url, datos)=>{  
    const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(datos)
      });      
      const data = await response.json();
      return data;
}

const putFetch = async(url, datos)=>{  
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(datos)
      });      
      const data = await response.json();
      return data;
}

const deleteFetch = async(url)=>{  
    const response = await fetch(url, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },       
      });      
      const data = await response.json();
      return data;
}


export{getFetch, postFetch, putFetch, deleteFetch}