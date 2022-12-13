import "./styles.css"

const CardReserva = ({itemInfo}) => {
  // const contextoDataProductos = useContext(DataProductosContext);
  // const { PRODUCTOS_POR_CATEGORIA_API_URL } = CONSTANTES;
  // const { setDataProductos } = contextoDataProductos;
  // const navigate = useNavigate();

  // const onDetalles = () => {
  //   navigate(`/producto/${itemInfo.id}`)
  // }

  // const onObtenerProductos = async () => {
  //   const url = `${PRODUCTOS_POR_CATEGORIA_API_URL}/${itemInfo.id}`
  //   const productosPorCategoria = await getFetch(url);
  //   setDataProductos(productosPorCategoria);
  // }
  
    return (
      <div className="carCardReserva">
        <div
          className="imgCardReserva"
          style={{ backgroundImage: `url(${itemInfo?.producto.imagenes[0].urlImagen ? itemInfo?.producto.imagenes[0].urlImagen : itemInfo?.producto.categoria.urlImagen })` }}
        >
          <img src="" alt=""/>
        </div>
        <div className="infoCardReserva">
          <h3>Producto: {itemInfo.producto.titulo}</h3>            
          <h3>Categoría: {itemInfo.producto.categoria.titulo}</h3>
          <h3>Check-In: {itemInfo.fechaCheckIn}</h3>
          <h3>Check-Out: {itemInfo.fechaCheckOut}</h3>
        </div>
      </div>
    );
}

export default CardReserva;