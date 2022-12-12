import "./styles.css"

const CaracteristicasProducto = ({ caracteristicas }) => {

    return (
        <>
            <h2 className="caracteristica-producto-titulo">¿Qué ofrece este vehiculo?</h2>
            <hr className="caracteristica-producto-separador" />
            <div className="caracteristica-producto">
                <div className="caracteristica-producto__columna-uno caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[0]?.titulo ? caracteristicas?.[0]?.titulo : 'Motor 1.6 Lts'}</p>
                </div>
                <div className="caracteristica-producto__columna-dos caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[1]?.titulo ? caracteristicas?.[1]?.titulo : 'Aire acondicionado'}</p>
                </div>
                <div className="caracteristica-producto__columna-tres caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[2]?.titulo  ? caracteristicas?.[2]?.titulo : 'Cambios manuales'}</p>
                </div>
                <div className="caracteristica-producto__columna-cuatro caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[3]?.titulo ? caracteristicas?.[3]?.titulo : 'Herramientas y repuestos'}</p>
                </div>
                <div className="caracteristica-producto__columna-cinco caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[4]?.titulo ? caracteristicas?.[4]?.titulo : 'Motor hibrido eléctrico'}</p>
                </div>
                <div className="caracteristica-producto__columna-seis caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[5]?.titulo ? caracteristicas?.[5]?.titulo : 'Turbo cargador eléctrico'}</p>
                </div>
                <div className="caracteristica-producto__columna-siete caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[6]?.titulo ? caracteristicas?.[6]?.titulo : 'Acelerador turbo'}</p>
                </div>
                <div className="caracteristica-producto__columna-ocho caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[7]?.titulo ? caracteristicas?.[7]?.titulo : 'Equipo de seguridad'}</p>
                </div>
                <div className="caracteristica-producto__columna-nueve caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[8]?.titulo ? caracteristicas?.[8]?.titulo :  'Silla ergonomica'}</p>
                </div>
                <div className="caracteristica-producto__columna-diez caracteristica-producto__columna-general">
                    <span />
                    <p>{caracteristicas && caracteristicas.length > 0 && caracteristicas?.[9]?.titulo ? caracteristicas?.[9]?.titulo : 'Frenos de disco'}</p>
                </div>
            </div>
        </>
    )
};

export default CaracteristicasProducto;