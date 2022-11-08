import ImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "./styles.css"

const GaleriaImagenesProducto = () => {

    const images = [
        {
          original: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1r9s%25WBXaBKFmfKSLC9gIQALMc6UhVk3GBfM9IW2VRkr72kVTd9pQswXGXQpMTV1rUe1g6OQCckPquBhS1U%25jzbTllxA0uvDV%250QFmwpXkpd2UrBoM4ljYZ7yTG3d&&pov=fronthero&width=1944&height=1052&bkgrnd=transparent&resp=png",
          thumbnail: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1r9s%25WBXaBKFmfKSLC9gIQALMc6UhVk3GBfM9IW2VRkr72kVTd9pQswXGXQpMTV1rUe1g6OQCckPquBhS1U%25jzbTllxA0uvDV%250QFmwpXkpd2UrBoM4ljYZ7yTG3d&&pov=fronthero&width=1944&height=1052&bkgrnd=transparent&resp=png",
          originalHeight: 500
        },
        {
            original: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1r9s%25WBXaBKFmfKSLC9gIQALMc6UhVk3GBfM9IW2VRkr72kVTd9pQswXGXQpMTV1rUe1g6OQCckPquBhS1U%25jzbTllxA0uvDg%250QFmwppkpd2UoBoM&&pov=inthero&width=1450&height=1010&bkgrnd=&resp=png",
            thumbnail: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1r9s%25WBXaBKFmfKSLC9gIQALMc6UhVk3GBfM9IW2VRkr72kVTd9pQswXGXQpMTV1rUe1g6OQCckPquBhS1U%25jzbTllxA0uvDg%250QFmwppkpd2UoBoM&&pov=inthero&width=1450&height=1010&bkgrnd=&resp=png",
            originalHeight: 500
        },
        {
            original: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1r9s%25WBXaBKFmfKSLC9gIQALMc6UhVk3GBfM9IW2VRkr72kVTd9pQswXGXQpMTV1rUe1g6OQCckPquBhS1U%25jzbTllxA0uvDg%250QFmwppkpd2UoBoMsX0&&pov=position11&width=1944&height=1052&bkgrnd=white&resp=png",
            thumbnail: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1r9s%25WBXaBKFmfKSLC9gIQALMc6UhVk3GBfM9IW2VRkr72kVTd9pQswXGXQpMTV1rUe1g6OQCckPquBhS1U%25jzbTllxA0uvDg%250QFmwppkpd2UoBoMsX0&&pov=position11&width=1944&height=1052&bkgrnd=white&resp=png",
            originalHeight: 500
        },
        {
            original: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1ryKIw0XaBckEKH3dJvNR2kSXstdzJTmtJdCQS%25aP0DNoin7BNV9wU7wuDfRMe2DoR3gf6zDyEH8763YsvEIoJalGZXRJfbGr0m0VbNt0zCi5Eth7&&height=700&width=700",
            thumbnail: "https://www.jeep.com/mediaserver/iris?COSY-EU-100-1713uLDEMTV1ryKIw0XaBckEKH3dJvNR2kSXstdzJTmtJdCQS%25aP0DNoin7BNV9wU7wuDfRMe2DoR3gf6zDyEH8763YsvEIoJalGZXRJfbGr0m0VbNt0zCi5Eth7&&height=700&width=700",
            originalHeight: 500
        }
      ];

    return (
        <div className="galeria-imagenes-producto">
            <ImageGallery
            items={images}
            showBullets={false}
            showIndex
            showThumbnails
            showPlayButton={false}
            showFullscreenButton
            slideDuration={3000}
            thumbnailPosition="right"
            autoPlay
            thumbnailStyle={{
                backgroundColor: 'red'
            }}
            />
        </div>
    );

};
export default GaleriaImagenesProducto;