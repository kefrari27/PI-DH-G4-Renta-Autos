import React from "react";
import '../ProcesoExitoso/styles.css';
import { Link } from 'react-router-dom';

const ProcesoExitoso = (props) => {

    return (
        <>
            <div className="proceso-exitoso__contenedor">
                <div className="proceso-exitoso__borde">
                    <span className="proceso-exitoso-img">0</span>
                    <h2>{props.titulo ? props.titulo : "¡Muchas Gracias!"}</h2>
                    <p>{props.descripcion ? props.descripcion : "Su reserva se ha realizo con éxito"}</p>
                    <Link to="/"><button type="submit" className="proceso-exitoso-btn">OK</button></Link>
                </div>
            </div>
        </>
    );

}

export default ProcesoExitoso;