import Card from '../Card';
import Data from'./data.json'
import "./CardList.css"
const CardList = () => {
   
    const infoItems= Data;

    return (
      <>
        <h2>Categorias</h2>  
        <div className='container'>
          {
            infoItems.map((item,index)=>
              <Card key={index}  itemInfo={item}/> 
            )
          }        
        </div>
        <h2>Categorias</h2>  
      </>
    )
}

export default CardList;