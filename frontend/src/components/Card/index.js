import { useNavigate } from "react-router-dom";
import "./styles.css"

const Card = ({itemInfo}) => {
  const navigate = useNavigate();

  const onDetalles = () => {
    navigate("/producto")
  }
  
    return (
      <div className="carCard">
        <div
          className="imgCard"
          style={{ backgroundImage: `url(${itemInfo.urlImagen})` }}
        ></div>

        <div className="infoCard">
          <h3>{itemInfo.titulo}</h3>            
          <p>
              {itemInfo.descripcion}
          </p>
        </div>

        <button onClick={onDetalles}>Detalles</button>
      </div>
    );
}

export default Card;