import { useContext } from 'react';
import autenticacionContext from '../../context/autenticacion/autenticacionContext';
import ControlMobileContext from '../../context/controlMobile/controlMobileContext';
import '../Footer/styles.css'

const Footer = () => {
    const contextoAutenticacion = useContext(autenticacionContext);
    const contextoControlMobile = useContext(ControlMobileContext);
    const { estadoAutenticacion } = contextoAutenticacion;
    const { esVersionMobileHeaderMenu } = contextoControlMobile;

    return (
        <>
        {!esVersionMobileHeaderMenu ?
            <footer className="footer">
                <div className="footer_copywrite">
                    <span>©2022 Digital Booking</span>
                </div>
                <div className="footer__redes_sociales">
                    <span className='icon__facebook' />
                    <span className='icon__linkedin' />
                    <span className='icon__twitter' />
                    <span className='icon__instagram' />
                </div>
            </footer> :
            <footer className="footer__mobile">
                { estadoAutenticacion && 
                    <div className="footer__mobile-separador">
                        <a href='https://www.google.com'>¿Deseas <span>cerrar sesion</span>?</a>
                        <hr />
                    </div>
                }
                <div className="footer__redes_sociales-mobile">
                    <span className='icon__facebook_oscuro' />
                    <span className='icon__linkedin_oscuro' />
                    <span className='icon__twitter_oscuro' />
                    <span className='icon__instagram_oscuro' />
                </div>
            </footer>
        }
        </>
    )
}

export default Footer;