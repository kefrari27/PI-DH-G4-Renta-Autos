import ImageGallery from "react-image-gallery";
import "react-image-gallery/styles/css/image-gallery.css";
import "./styles.css"

const GaleriaImagenesProducto = ({imagenes}) => {

    const imagenesGaleria = imagenes?.map(imagen => {
        return (
            {
                original: imagen.urlImagen,
                thumbnail: imagen.urlImagen,
                originalHeight: 500,
                originalClass: 'galeria-imagenes-producto-imagen'
            }
        )
    });

    return (
        <div className="galeria-imagenes-producto">
            <ImageGallery
            additionalClass="galeria-imagenes-producto-componente"
            items={imagenesGaleria}
            showBullets={false}
            showIndex
            showThumbnails
            showPlayButton={false}
            showFullscreenButton
            slideDuration={3000}
            thumbnailPosition="right"
            autoPlay
            />
        </div>
    );

};
export default GaleriaImagenesProducto;