import React from 'react';
import '../DetalleReserva/styles.css';

const DetalleReserva = ({titulo,categoria,imagen}) => {
  
  return (
    <>
      <section className="detalle-reserva__section">
        <div className='detalle-reserva__borde'>
          <h3>Detalle de reserva</h3>
          <div
            className="detalle-reserva-img__contenedor"
            style={{ backgroundImage: `url(${imagen })` }} >
          
            <img src="" alt=""/>
          </div>
          <div className='detalle-reserva-producto__contenedor'>
            <p>Categoría: {categoria}</p>
            <h4>{titulo}</h4>
            <ul>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
                        <li><span>aa</span></li>
            </ul>
            <p><span>o</span>Ubicación</p>
          </div>
          <hr className="detalle-reserva-separador"/>
          <div className='detalle-reserva-check'>
            <h5>Check in</h5>
            <p>__ /__ /__</p>
          </div>
          <hr className="detalle-reserva-separador"/>
          <div className='detalle-reserva-check'>
            <h5>Check out</h5>
            <p>__ /__ /__</p>
          </div>
          <hr className="detalle-reserva-separador"/>
          <div className="detalle-reserva-btn__contenedor">
            <button>Confirmar reserva</button>
          </div>
        </div>
      </section>
    </>
  );
}

export default DetalleReserva; 