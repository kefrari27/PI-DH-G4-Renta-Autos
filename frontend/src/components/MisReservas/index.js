import React, {useState, useEffect} from "react";
import { Link } from "react-router-dom";
import HeaderMisReservas from "./HeaderMisReservas";
import "../MisReservas/styles.css";

const MisReservas = () => {

    const [reservas, setReservas] = useState([]);
    const [tieneReservas, setTieneReservas] = useState(false);

    const datosDeLocalStorage =JSON.parse( localStorage.getItem('datosUsuario'));

    if(datosDeLocalStorage) {
        if(tieneReservas) {
            return (
                <>
                    <HeaderMisReservas />
                    <div className="mis-reservas-listado__contenedor">
                        //renderiza cards de reservas
                        <table className="mis-reservas-tabla">
                            <tr className="mis-reservas-tabla-titulos">
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Categoría</th>
                                <th>Fecha Check in</th>
                                <th>Fecha Check out</th>
                            </tr>
                            {reservas.map(reserva => {
                                return (
                                    <tr key={reserva.id} className="mis-reservas-tabla-datos">
                                        <td>No. {reserva.id}</td>
                                        <td>Nombre de producto</td>
                                        <td>Nombre de producto</td>
                                        <td>Nombre de producto</td>
                                        <td>Nombre de producto</td>
                                        <td>Nombre de producto</td>
                                    </tr>
                                )
                            })}
                        </table>
                    </div>
                </>
            );
        } else {
            return (
                <>
                    <HeaderMisReservas />
                    <div className="mis-reservas-mensaje__contenedor">
                        <span className="mis-reservas-icono-no-reservas">OO</span>
                        <h2>Aún no has efectuado ninguna reserva</h2>
                        <Link to="/">
                            <button className="mis-reservas-boton-regresar" type="submit">Volver al inicio</button>
                        </Link>
                    </div>
                </>
            );
        }
    } else {
        return (
            <>
                <HeaderMisReservas />
                <div className="mis-reservas-mensaje__contenedor">
                    <span className="mis-reservas-icono-no-reservas">OO</span>
                    <h2>Debes iniciar sesión para visualizar tus reservas</h2>
                    <Link to="/">
                        <button className="mis-reservas-boton-regresar" type="submit">Volver al inicio</button>
                    </Link>
                </div>
            </>
        );
    }
}

export default MisReservas;
