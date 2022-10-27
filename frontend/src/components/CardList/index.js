import Card from '../Card';
import Data from'./data.json'
import "./CardList.css"
const CardList = () => {
   
    const infoItems= Data;

    return (
        <>
        
        <div className='container'>
      {
        infoItems.map((item,index)=>
          
           <Card key={index}  itemInfo={item} /> 
        )
      }
      
    </div>
        </>
    )
}

export default CardList;