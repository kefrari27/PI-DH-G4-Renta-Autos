import React, { useContext, useEffect, useState } from "react";
import DataProductosContext from "../../context/dataProductos/dataProducosContext";
import { getFetch, CONSTANTES, postFetch } from "../../core/request";
import CalendarioBuscador from "./CalendarioBuscador";
import { format } from 'date-fns'
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

    const { CIUDADES_API_URL, PRODUCTOS_FECHAS_CIUDAD_API_URL, CIUDADESCONPROD_API_URL } = CONSTANTES;

    const contextoDataProductos = useContext(DataProductosContext);
    const { setDataProductos } = contextoDataProductos;
    const [listaCiudades, setListaCiudades] = useState(ciudades);
    const [rangoAPadre, setRangoAPadre] = useState([null, null]);
    const [idCiudad, setIdCiudad] = useState();

    const fechaIngresoFormateada = rangoAPadre?.[0] ? format(new Date(rangoAPadre?.[0]), 'yyyy-MM-dd'): "";
    const fechaSalidaFormateada = rangoAPadre?.[1] ? format(new Date(rangoAPadre?.[1]), 'yyyy-MM-dd') : "";
     
    const consultarCiudades = async()=> {     
      const data = await getFetch(CIUDADESCONPROD_API_URL);     
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
        const body = {
            ciudad: {
                id: idCiudad ? idCiudad : 0
            },
            fechaCheckIn: fechaIngresoFormateada,
            fechaCheckOut: fechaSalidaFormateada
        }
        const productosCiudad = await postFetch(PRODUCTOS_FECHAS_CIUDAD_API_URL, body);
        setDataProductos(productosCiudad);
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
                                    <span className="city-select__item-name">{element.nombre} </span>
                                    <span className="city-select__item-country">{element.pais}</span>
                                </option>
                            ))}
                        </select>
                        <label for="buscador-formulario-ciudad"><span class="icon__location-oscuro">0I</span></label>
                    </div>
                    <div className='buscador-formulario-calendario__contenedor'>
                        <CalendarioBuscador setRangoAPadre={setRangoAPadre}/>
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