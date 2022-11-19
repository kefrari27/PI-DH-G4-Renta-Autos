import React, { useEffect, useState } from "react";
import { getFetch, CONSTANTES } from "../../core/request";
import CalendarioBuscador from "./CalendarioBuscador";
import './styles.css'

/* JSON con ciudades de prueba --> Nota: Este codigo será borrado cuando se consuma de la API respectiva*/
const ciudades = [
    {
        "id": 1,
        "name": "San Carlos de Bariloche\n",
        "country": "Argentina",
    },
    {
        "id": 2,
        "name": "Buenos Aires\n",
        "country": "Argentina",
    },
    {
        "id": 3,
        "name": "Mendoza\n",
        "country": "Argentina",
    },
    {
        "id": 4,
        "name": "Córdoba\n",
        "country": "Argentina",
    },
];

const Buscador = () => {

    const { CIUDADES_API_URL, PRODUCTOS_POR_CIUDAD_API_URL } = CONSTANTES;
    
    const [listaCiudades, setListaCiudades] = useState(ciudades);
    const [idCiudad, setIdCiudad] = useState();    

    const consultarCiudades = async()=> {     
      const data = await getFetch(CIUDADES_API_URL);     
      setListaCiudades(data);
    }   

    useEffect(() => {
        consultarCiudades();          
    }, [])

    const onSeleccionarCiudad = (event) => {
        setIdCiudad(event.target.value)
    };

    const onSubmit = async (event) => {
        event.preventDefault();
        const url = `${PRODUCTOS_POR_CIUDAD_API_URL}/${idCiudad}`
        const productosCiudad = await getFetch(url);        
        console.log("🚀 ~ file: index.js ~ line 54 ~ onSubmit ~ productosCiudad", productosCiudad)
    };

    return (
        <nav className='buscador'>
            <div className='buscador-titulo__contenedor'>
                <h1>Encuentra las mejores ofertas en alquiler de autos y mucho más</h1>
                <p></p>
            </div>
            <div className='buscador-formulario__contenedor'>
                <form className='buscador-formulario' onSubmit={onSubmit}>
                    <div className='buscador-formulario-ciudad__contenedor'>
                        <select className='buscador-formulario-ciudad' name='ciudades' id="city-select" onChange={onSeleccionarCiudad}>
                            <option disabled selected><span className="select-placeholder">¿A dónde vamos?</span></option>
                            {listaCiudades.map(element => (
                                <option id="buscador-formulario-ciudad-item" className="buscador-formulario-ciudad-item" key={element.id} value={element.id}>
                                    <span class="icon__location"/>
                                    <span className="city-select__item-name">{element.nombre}</span>
                                    <span className="city-select__item-country">{element.pais}</span>
                                </option>
                            ))}
                        </select>
                        <label for="buscador-formulario-ciudad"><span class="icon__location-oscuro">0I</span></label>
                    </div>
                    <div className='buscador-formulario-calendario__contenedor'>
                        <CalendarioBuscador />
                    </div>
                    <div className='buscador-formulario-btn__contenedor'>
                        <button className='buscador-formulario-btn' type='submit'>Buscar</button>
                    </div>
                </form>
            </div>
        </nav>
    )
}

export default Buscador;