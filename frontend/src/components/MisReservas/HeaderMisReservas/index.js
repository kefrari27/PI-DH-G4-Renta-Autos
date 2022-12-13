import { useNavigate } from "react-router-dom";
import "./styles.css"

const HeaderMisReservas = () => {
    const navigate = useNavigate();
    
    const onVolverAtras = () => {
        navigate(-1);
    }

    return (
        <div className="header-reserva">
            <div className="header-reserva__informacion">
                <h4>Mis Reservas</h4>
            </div>
            <div className="header-reserva__volver">
                <button onClick={onVolverAtras}/>
            </div>
        </div>
    )
};

export default HeaderMisReservas;