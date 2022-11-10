import { useNavigate } from "react-router-dom";
import "./styles.css"

const Card = ({itemInfo}) => {
  const navigate = useNavigate();

  const onDetalles = () => {
    navigate(`/producto/${itemInfo.id}`)
  }
  
    return (
      <div className="carCard">
        <div
          className="imgCard"
          style={{ backgroundImage: `url(${itemInfo?.urlImagen ? itemInfo?.urlImagen : itemInfo?.imagenes[0]?.urlImagen })` }}
        >
          <img src="" alt=""/>
        </div>

        <div className="infoCard">
          <h3>{itemInfo.titulo}</h3>            
          <p>
              {itemInfo.descripcion}
          </p>
          {!itemInfo.urlImagen && <button onClick={onDetalles}>Detalles del vehiculo</button>}
        </div>
      </div>
    );
}

export default Card;