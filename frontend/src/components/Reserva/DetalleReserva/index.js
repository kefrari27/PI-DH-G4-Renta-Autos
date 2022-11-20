import React from 'react'
import data from '../../CardList/data.json'
import CardReserva from '../CardReserva'
const DetalleReserva = () => {
const datos = data; 
    return (
  <>
    <CardReserva itemInfo={datos[0]}/> 
  </>
  )
}

export default DetalleReserva; 