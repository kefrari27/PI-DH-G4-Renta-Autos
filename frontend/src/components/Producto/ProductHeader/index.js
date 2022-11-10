import { useNavigate } from "react-router-dom";
import "./styles.css"

const ProductHeader = ({titulo}) => {
    const navigate = useNavigate();
    
    const onVolverAtras = () => {
        navigate(-1);
    }

    return (
        <div className="header-producto">
            <div className="header-producto__informacion">
                <h4>Vehiculo</h4>
                <h2>{titulo}</h2>
            </div>
            <div className="header-producto__volver">
                <button onClick={onVolverAtras}/>
            </div>
        </div>
    )
};

export default ProductHeader;