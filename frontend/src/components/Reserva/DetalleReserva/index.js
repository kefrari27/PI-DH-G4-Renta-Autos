import React from 'react';
import '../DetalleReserva/styles.css';

const DetalleReserva = ({titulo,categoria,imagen,ubicacion,fechaResIni,
  fechaResFin}) => {
  
  /* Renderizar ubicación del producto */
  const { pais, nombre, provincia} = ubicacion;
  
  return (
    <>
      <section className="detalle-reserva__section">
        <div className='detalle-reserva__borde'>
            <h3>Detalle de reserva</h3>
          <div className='detalle-reserva-img-datos__contenedor'>
            <div
              className="detalle-reserva-img__contenedor"
              style={{ backgroundImage: `url(${imagen })` }} 
            >
              <img src="" alt=""/>
            </div>
            <div className='detalle-reserva-datos__contenedor'>
              <div className='detalle-reserva-producto__contenedor'>
                <p>{categoria}</p>
                <h4>{titulo}</h4>
                <ul>
                  <li><span>aa</span></li>
                  <li><span>aa</span></li>
                  <li><span>aa</span></li>
                  <li><span>aa</span></li>
                  <li><span>aa</span></li>
                </ul>
                <div className="producto-ubicacion__informacion">
                  <span>o</span>
                  {(pais && nombre && provincia) ? <p>{pais}, {provincia}, {nombre}</p> : <p>La ubicación no está disponible en este momento</p>}
                </div>
              </div>
              <hr className="detalle-reserva-separador"/>
              <div className='detalle-reserva-check'>
                <h5>Check in</h5>
                <p>{fechaResIni ? fechaResIni : "__ /__ /__"}</p>
              </div>
              <hr className="detalle-reserva-separador"/>
              <div className='detalle-reserva-check'>
                <h5>Check out</h5>
                <p>{fechaResFin ? fechaResFin : "__ /__ /__"}</p>
              </div>
              <hr className="detalle-reserva-separador"/>
              <div className="detalle-reserva-btn__contenedor">
                <button>Confirmar reserva</button>
              </div>
            </div>
          </div>
        </div>
      </section> 
    </>
  );
}

export default DetalleReserva; 