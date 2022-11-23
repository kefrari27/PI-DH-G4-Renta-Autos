import React from 'react';
import '../DetalleReserva/styles.css';

const DetalleReserva = () => {

  return (
    <>
      <section className="detalle-reserva__section">
        <div className='detalle-reserva__borde'>
          <h3>Detalle de reserva</h3>
          <div
            className="detalle-reserva-img__contenedor"
          >
            <img src="" alt=""/>
          </div>
          <div className='detalle-reserva-producto__contenedor'>
            <p>Categoría</p>
            <h4>Nombre del Vehiculo</h4>
            <span>estrellitas</span>
            <p><span>OO</span>Ubicación</p>
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