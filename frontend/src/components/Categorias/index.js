import React from 'react'
import CardList from '../CardList'
import './styles.css'

const Categorias = ({dataCategorias}) => {
  return (
    <div>
        <h2>Categorias</h2>
        <CardList data={dataCategorias} />
    </div>
  )
}

export default Categorias