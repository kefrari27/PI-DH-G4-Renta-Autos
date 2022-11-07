
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

const consultarCaracteristicas = async()=>{
    const url = CARACTERISTICAS_API_URL;    
    const response = await fetch(url);
    const data = await response.json();
    console.log("caracteristicas");
    console.log(data);
    return data;
}

const consultarCategorias = async()=>{
    const url = CATEGORIAS_API_URL;    
    const response = await fetch(url);
    const data = await response.json();
    console.log("categorias");
    console.log(data);
    return data;
}

const consultarCiudades = async()=>{
    const url = CIUDADES_API_URL;    
    const response = await fetch(url);
    const data = await response.json();
    console.log("ciudades");
    console.log(data);
    return data;
}

const consultarImagenes = async()=>{
    const url = IMAGENES_API_URL;    
    const response = await fetch(url);
    const data = await response.json();
    console.log("imagenes");
    console.log(data);
    return data;
}

const consultarProductos = async()=>{
    const url = PRODUCTOS_API_URL;    
    const response = await fetch(url);
    const data = await response.json();
    console.log("productos");
    console.log(data);
    return data;
}




 

export{consultarCaracteristicas, consultarCategorias, consultarCiudades, consultarImagenes, consultarProductos,}