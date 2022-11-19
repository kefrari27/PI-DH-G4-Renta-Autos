import CardList from "../CardList"

const Productos = ({dataProductos}) => {
    return (
      <div>
          <h2>Productos</h2>
          <CardList data={dataProductos} />
      </div>
    )
  }
  
  export default Productos