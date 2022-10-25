import "./CardStyle.css"

const Card = ({itemInfo}) => {
   

    return (
      <>
        <div className="carCard">
          <div
            className="imgCard"
            style={{ backgroundImage: `url(${itemInfo.img})` }}
          ></div>

          <div className="infoCard">
            <h3>{itemInfo.product.title}</h3>
            <p>
             
              {itemInfo.product.location}
            </p>
            <p>
                {itemInfo.product.category}
            </p>
            <p>
                {itemInfo.product.description}
            </p>
          
          </div>
        </div>
      </>
    );
}

export default Card;