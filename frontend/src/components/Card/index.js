import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import DataProductosContext from "../../context/dataProductos/dataProducosContext";
import { getFetch, CONSTANTES } from '../../core/request';
import "./styles.css"

const Card = ({itemInfo}) => {
  const contextoDataProductos = useContext(DataProductosContext);
  const { PRODUCTOS_POR_CATEGORIA_API_URL } = CONSTANTES;
  const { setDataProductos } = contextoDataProductos;
  const navigate = useNavigate();

  const onDetalles = () => {
    navigate(`/producto/${itemInfo.id}`)
  }

  const onObtenerProductos = async () => {
    const url = `${PRODUCTOS_POR_CATEGORIA_API_URL}/${itemInfo.id}`
    const productosPorCategoria = await getFetch(url);
    setDataProductos(productosPorCategoria);
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
          {!itemInfo.urlImagen ? <button onClick={onDetalles}>Detalles del vehiculo</button> : 
          <button onClick={onObtenerProductos}>Ver productos</button>}
        </div>
      </div>
    );
}

export default Card;