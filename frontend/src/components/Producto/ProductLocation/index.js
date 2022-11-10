import "./styles.css"

const ProductLocation = () => {

    return (
        <div className="producto-ubicacion">
            <div className="producto-ubicacion__informacion">
                <span>a</span>
                <p> Buenos Aires, Ciudad Autónoma de Buenos Aires, Argentina <br />
                    A 940 m del centro </p>
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