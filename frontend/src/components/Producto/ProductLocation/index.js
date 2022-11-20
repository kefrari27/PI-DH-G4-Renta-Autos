import "./styles.css"

const ProductLocation = ({ubicacion}) => {
    const { pais, nombre, provincia} = ubicacion;

    return (
        <div className="producto-ubicacion">
            <div className="producto-ubicacion__informacion">
                <span>a</span>
                {(pais && nombre && provincia) ? <p>{pais}, {provincia}, {nombre}</p> : <p>La ubicación no está disponible en este momento</p>}
            </div>
            <div className="producto-ubicacion__calificacion">
                <div className="producto-ubicacion__calificacion__general">
                    <p>Muy Bueno</p>
                    <ul>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
                    </ul>
                </div>
                <div className="producto-ubicacion__calificacion__general-numerica">
                    <p>8</p>
                </div>
            </div>
        </div>
    )
};

export default ProductLocation;