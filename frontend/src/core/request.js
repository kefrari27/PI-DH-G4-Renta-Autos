

// Información end-points

// const API_URL_LOCAL = 'http://localhost:8080/api/v1';
const API_URL_PRODUCCION = 'http://18.218.111.107:8080/api/v1';

const CATEGORIAS_API_URL = `${API_URL_PRODUCCION}/categorias`;
const CIUDADES_API_URL = `${API_URL_PRODUCCION}/ciudades`;
const IMAGENES_API_URL = `${API_URL_PRODUCCION}/imagenes`;
const PRODUCTOS_API_URL = `${API_URL_PRODUCCION}/productos`;
const USUARIOS_API_URL = `${API_URL_PRODUCCION}/usuarios`;
const RESERVAS_API_URL = `${API_URL_PRODUCCION}/reservas`;

const CONSTANTES = {

  CARACTERISTICAS_API_URL: `${API_URL_PRODUCCION}/caracteristicas`,
  CATEGORIAS_API_URL: `${API_URL_PRODUCCION}/categorias`,
  CIUDADES_API_URL: `${API_URL_PRODUCCION}/ciudades`,
  CIUDADESCONPROD_API_URL: `${API_URL_PRODUCCION}/ciudades/ciudadesConProductos`,
  IMAGENES_API_URL: `${API_URL_PRODUCCION}/imagenes`,
  PRODUCTOS_API_URL: `${API_URL_PRODUCCION}/productos`,
  USUARIOS_API_URL: `${API_URL_PRODUCCION}/usuarios`,
  AUTENTICACION_API_URL: `${API_URL_PRODUCCION}/autenticacion`,

  CATEGORIAS_ID_API_URL: `${CATEGORIAS_API_URL}/{id}`,
  CIUDADES_ID_API_URL: `${CIUDADES_API_URL}`,
  IMAGENES_ID_API_URL: `${IMAGENES_API_URL}{id}`,
  PRODUCTOS_ID_API_URL: `${PRODUCTOS_API_URL}`,
  USUARIOS_ID_API_URL: `${USUARIOS_API_URL}`,

  IMAGENES_POR_PRODUCTOID_API_URL: `${PRODUCTOS_API_URL}/imagenesByProductoId/{id}`,
  ADICIONAR_CARACTERISTICA_PRODUCTO_API_URL: `${PRODUCTOS_API_URL}/addCaracteristica`,
  PRODUCTOS_POR_PRODUCTOID_API_URL: `${PRODUCTOS_API_URL}/addCaracteristica`,
  PRODUCTOS_POR_CATEGORIA_API_URL: `${PRODUCTOS_API_URL}/productosByCategoria`,
  PRODUCTOS_POR_CIUDAD_API_URL: `${PRODUCTOS_API_URL}/productosByCiudad`,
  PRODUCTOS_ALEATORIOS_API_URL: `${PRODUCTOS_API_URL}/productosAleatorios`,
  PRODUCTOS_FECHAS_CIUDAD_API_URL: `${PRODUCTOS_API_URL}/filtroProductosByFechas`,
  PRODUCTOS_CREAR_API_URL: `${PRODUCTOS_API_URL}/crearProductoCompleto`,

  RESERVAS_POR_ID_USUARIO_API_URL: `${RESERVAS_API_URL}/reservasByUsuarioId`
}


const getFetch = async(url) => {
  const response = await fetch(url);
  const data = await response.json();
  return data;
}

const getFetchReservas = async(url) => {
  const token = localStorage.getItem('token');
  const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
        'Authorization': token ? `Bearer ${token}` : ''
      }
    });
    const data = await response.json();
    return data;
}

const postFetch = async(url, datos)=>{  
    const token = localStorage.getItem('token');
    const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json;charset=utf-8',
          'Authorization': token ? `Bearer ${token}` : ''
        },
        body: JSON.stringify(datos)
      });
      const data = response.status === 200 || response.status === 201 ? await response.json() : response;
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


export{getFetch, getFetchReservas, postFetch, putFetch, deleteFetch, CONSTANTES}