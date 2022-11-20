import { useNavigate } from "react-router-dom";
import IniciarReserva from "../../Producto/IniciarReserva";
import "./styles.css"

const CardReserva = ({itemInfo}) => {
  const navigate = useNavigate();

  const onDetalles = () => {
    navigate(`/producto/${itemInfo.id}`)
  }
/*   Título: “Detalle de reserva”
Imagen principal del producto.
Categoría de producto.
Título del producto.
Ubicación del producto.
Check-in
Check-out
Botón de confirmación de reserva del formulario de reserva. */

    return (
      <div className="carCard">
        <h3>Detalle de reserva</h3>                   
        <div
          className="imgCard"
          style={{ backgroundImage: `url(${itemInfo?.urlImagen ? itemInfo?.urlImagen : itemInfo?.imagenes[0]?.urlImagen })` }}
        >
          <img src="" alt=""/>
        </div>
        <div>
          <p>
            {/* {itemInfo.categoria} */}
            Categoría
          </p>
          <h4>{itemInfo.titulo}</h4>
          <p>{/* {itemInfo.ubicacion} */}
            Ubicación
          </p>
        </div>
        <div>
          <h5>Check in</h5>
          <p>hora</p>
        </div>
        <div>
          <h5>Check out</h5>
          <p>hora</p>
        </div>
      <IniciarReserva/>
      </div>
    );
}

export default CardReserva;