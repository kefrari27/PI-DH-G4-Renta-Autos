import React from "react";
import '../Browser/styles.css'

/* JSON con ciudades de prueba --> Nota: Este codigo será borrado cuando se consuma de la API respectiva*/
const ciudades = [
    {
        "id": 1,
        "name": "San Carlos de Bariloche",
        "country": "Argentina",
    },
    {
        "id": 2,
        "name": "Buenos Aires",
        "country": "Argentina",
    },
    {
        "id": 3,
        "name": "Mendoza",
        "country": "Argentina",
    },
    {
        "id": 4,
        "name": "Córdoba",
        "country": "Argentina",
    },
];

const Browser = () => {

    return (
        <nav className='browser'>
            <div className='h1-browser__container'>
                <h1>Busca ofertas en hoteles, casas y mucho más</h1>
                <p></p>
            </div>
            <div className='form-browser__container'>
                <form className='form'>
                    <div className='city-select__container'>
                        <select className='city-select' name='cities'>
                            <option disabled selected><span className="location-icon">i</span><span className="select-placeholder">¿A dónde vamos?</span></option>
                            {ciudades.map(element => (
                                <option key={element.id}>
                                    <span>i</span>
                                    <span>{element.name}</span>
                                    <span>{element.country}</span>
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className='calendar__container'>
                        <input type='date' className="calendar-element"/>
                    </div>
                    <div className='btn-form__container'>
                        <button className='btn-form-browser' type='submit'>Buscar</button>
                    </div>
                </form>
            </div>
        </nav>
    )
}

export default Browser;