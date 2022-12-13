import React, {useState, useEffect} from "react";
import { Link } from "react-router-dom";
import HeaderMisReservas from "./HeaderMisReservas";
import { CONSTANTES, getFetchReservas } from "../../core/request";
import "../MisReservas/styles.css";
import CardReserva from "./CardReserva";

const MisReservas = () => {

    const { RESERVAS_POR_ID_USUARIO_API_URL } = CONSTANTES;
    const [reservas, setReservas] = useState([]);

    const datosDeLocalStorage =JSON.parse( localStorage.getItem('datosUsuario'));
    const idUsuario = datosDeLocalStorage.id;

    const consultarReservas = async() => {
        const url = `${RESERVAS_POR_ID_USUARIO_API_URL}/${idUsuario}`
        await getFetchReservas(url).then(respuesta => {
            setReservas(respuesta);
        })
    }
  
    useEffect(() => {
        consultarReservas();
    }, []);

    if(reservas.length > 0) {
        console.log(reservas)
        return (
            <>
                <HeaderMisReservas />
                <div className="mis-reservas-listado__contenedor">
                    {
                        reservas.map((reserva, index)=>
                            <CardReserva key={index}  itemInfo={reserva}/> 
                        )
                    }
                </div>
            </>
        );
    } else {
        return (
            <>
                <HeaderMisReservas />
                <div className="mis-reservas-mensaje__contenedor">
                    <div className="mis-reservas-mensaje__borde">
                        <span className="mis-reservas-icono-no-reservas">OO</span>
                        <h2>Aún no has efectuado ninguna reserva</h2>
                        <Link to="/">
                            <button className="mis-reservas-boton-regresar" type="submit">Volver al inicio</button>
                        </Link>
                    </div>
                </div>
            </>
        );
    }
}

export default MisReservas;
