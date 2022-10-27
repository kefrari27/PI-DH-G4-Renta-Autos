import "./CardStyle.css"

const Card = ({itemInfo}) => {   
  
    return (
      <>
        <div className="carCard">
          <div
            className="imgCard"
            style={{ backgroundImage: `url(${itemInfo.urlImagen})` }}
          ></div>

          <div className="infoCard">
            <h3>{itemInfo.titulo}</h3>            
            <p>
                {itemInfo.descripcion}
            </p>
          
          </div>
        </div>
      </>
    );
}

export default Card;