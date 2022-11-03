import { useNavigate } from "react-router-dom";
import "./styles.css"

const ProductHeader = () => {
    const navigate = useNavigate();
    
    const onVolverAtras = () => {
        navigate(-1);
    }

    return (
        <div className="header-producto">
            <div className="header-producto__informacion">
                <h4>Vehiculo</h4>
                <h2>Wrangler rubicon 2022 4xe</h2>
            </div>
            <div className="header-producto__volver">
                <button onClick={onVolverAtras}/>
            </div>
        </div>
    )
};

export default ProductHeader;