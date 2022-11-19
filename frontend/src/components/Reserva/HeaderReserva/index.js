import { useNavigate } from "react-router-dom";
import "./styles.css"

const HeaderReserva = ({titulo}) => {
    const navigate = useNavigate();
    
    const onVolverAtras = () => {
        navigate(-1);
    }

    return (
        <div className="header-reserva">
            <div className="header-reserva__informacion">
                <h4>Vehiculo</h4>
                <h2>{titulo}</h2>
            </div>
            <div className="header-reserva__volver">
                <button onClick={onVolverAtras}/>
            </div>
        </div>
    )
};

export default HeaderReserva;