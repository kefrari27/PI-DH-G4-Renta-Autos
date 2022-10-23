import { useContext } from 'react';
import contextAplicacion from '../../provider/contextAutenticacion';
import '../Footer/styles.css'

const Footer = () => {
    const contextoGeneral = useContext(contextAplicacion);

    const { autenticacion, versionMobileHeaderMenu } = contextoGeneral;
    const { estadoAutenticacion } = autenticacion;
    const { esVersionMobileHeaderMenu } = versionMobileHeaderMenu;

    return (
        <>
        {!esVersionMobileHeaderMenu ?
            <footer className="footer">
                <div className="footer_copywrite">
                    <span>©2021 Digital Booking</span>
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