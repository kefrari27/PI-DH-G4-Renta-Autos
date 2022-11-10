import "./styles.css"

const DescripcionProducto = ({ descripcion }) => {

    return (
        <div className="descripcion-producto">
            <h2>Descripción general del producto</h2>
            <p>{descripcion}</p>
        </div>
    )
};

export default DescripcionProducto;