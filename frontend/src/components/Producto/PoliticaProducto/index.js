import "./styles.css"

const PoliticaProducto = ({ politicas }) => {

    const normas = politicas?.[0]?.normas?.split('\n');
    const saludSeguridad = politicas?.[0]?.saludYSeguridad?.split('\n');
    const cancelacion = politicas?.[0]?.cancelacion?.split('\n');

    return (
        <>
            <h2 className="politica-producto__titulo">¿Qué tienes que saber?</h2>
            <hr className="politica-producto-separador" />
            <div className="politica-producto">
                <div className="politica-producto__columna-uno politica-producto__columna-general">
                    <h3>Normas</h3>
                   { politicas && politicas.length > 0 && politicas?.[0]?.normas ? 
                    <ul>
                        { normas?.map(norma => {
                            return <li>{norma}</li>
                        })}
                    </ul> :
                    <ul>
                        <li>No usar objetos cortapunzantes dentro del vehiculo</li>
                        <li>Entregar el vehiculo limpio</li>
                        <li>Entregar el vehiculo en el horario establecido</li>
                    </ul> }
                    
                </div>
                <div className="politica-producto__columna-dos politica-producto__columna-general">
                    <h3>Seguridad</h3>
                   { politicas && politicas.length > 0 && politicas?.[0]?.saludYSeguridad ?
                    <ul>
                        { saludSeguridad?.map(norma => {
                            return <li>{norma}</li>
                        })}
                    </ul> : 
                    <ul>
                        <li>Usar el cinturon de seguridad en cada viaje</li>
                        <li>evitar fumar dentro del vehiculo</li>
                        <li>No coducir bajo estado de embriaguez</li>
                        <li>No use el celular mientras conduce</li>
                        <li>No realice ningún viaje sin el kit de herramientas y el kti de primeros auxilios</li>
                    </ul> }
                </div>
                <div className="politica-producto__columna-tres politica-producto__columna-general">
                    <h3>Cancelación</h3>
                    { politicas && politicas.length > 0 && politicas?.[0]?.cancelacion ?
                        <ul>
                            { cancelacion?.map(norma => {
                                return <li>{norma}</li>
                            })}
                        </ul> :
                        <ul>
                            <li>La cancelación sin costo es válida hasta con 12 horas de anticipación, 
                                de lo contrario, se le cobrará el 10% del valor de la renta total como penalidad.
                            </li>
                            <li>La empresa puede realizar la cancelación del arriendo del vehiculo si encuentra irregularidares
                                del historial penal del usuario.
                            </li>
                            <li>La empresa no arrienda vehiculos a menores de edad, personas sin documentos legales al dia,
                                extranjeros sin pasaporte o por cualquier razón que la compañia considere válida para no arrendar
                                el vehiculo
                            </li>
                        </ul>}
                </div>
            </div>
        </>
    )
};

export default PoliticaProducto;