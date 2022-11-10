import Card from '../Card';
import "./styles.css"

const CardList = ({data}) => {
  
    return (
      <div className='container'>
        {
          data.map((item,index)=>
            <Card key={index}  itemInfo={item}/> 
          )
        }        
      </div>       
    )
}

export default CardList;