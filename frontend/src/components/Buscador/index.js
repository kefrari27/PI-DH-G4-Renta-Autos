import React from "react";
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

    return (
        <nav className='buscador'>
            <div className='buscador-titulo__contenedor'>
                <h1>Encuentra las mejores ofertas en alquiler de autos y mucho más</h1>
                <p></p>
            </div>
            <div className='buscador-formulario__contenedor'>
                <form className='buscador-formulario'>
                    <div className='buscador-formulario-ciudad__contenedor'>
                        <select className='buscador-formulario-ciudad' name='ciudades' id="city-select">
                            <option disabled selected><span className="select-placeholder">¿A dónde vamos?</span></option>
                            {ciudades.map(element => (
                                <option id="buscador-formulario-ciudad-item" className="buscador-formulario-ciudad-item" key={element.id}>
                                    <span class="icon__location"/>
                                    <span className="city-select__item-name">{element.name}</span>
                                    <span className="city-select__item-country">{element.country}</span>
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