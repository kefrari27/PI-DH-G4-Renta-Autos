import { useContext } from 'react';
import contextAplicacion from '../../provider/contextAutenticacion';
import { Link } from "react-router-dom";
import '../Header/styles.css'

const Header = () => {
    const contextoGeneral = useContext(contextAplicacion);
    const { autenticacion, versionMobileHeaderMenu } = contextoGeneral;
    const { estadoAutenticacion, login } = autenticacion;
    const { esVersionMobileHeaderMenu, setVersionMobileHeaderMenu } = versionMobileHeaderMenu;

    return (
        <>
       {!esVersionMobileHeaderMenu ? 
        <header className="header">
            <div className="header__container">
                <Link to="/"><span className="header__container-imagen"></span></Link>
                <Link to="/" className='header__container-title'>Seguridad y confianza al volante</Link>
            </div>
            <div className="header__botones">
                {!estadoAutenticacion && <button className='header__botones-boton'><Link to="/crearCuenta">Crear Cuenta</Link></button>}
                {!estadoAutenticacion ? <button className='header__botones-boton'><Link to="/inicioSesion">Iniciar sesión</Link></button> : 
                <div className="header__usuario-logueado">
                    <div className="header__avatar"><span>MP</span></div>
                    <div className="header__usuario">
                        <p className="header__cerrar-sesion"><button className="header__cerrar-sesion-boton" onClick={login}>X</button></p>
                        <label>Hola,</label>
                        <p className="header__usuario-nombre">Maureen Parra</p>
                    </div>
                </div>}
                <span className="header__botones-boton-mobile-icono"><button onClick={setVersionMobileHeaderMenu} className='header__botones-boton-mobile'></button></span>
            </div>
        </header> :
        <div className="menu__mobile">
            <header className="menu__mobile-header">
                <span><button className="menu__mobile-header-cerrar-sesion" onClick={setVersionMobileHeaderMenu}>X</button></span>
                {!estadoAutenticacion ? 
                    <p className="menu__mobile-header-menu">Menú</p> :
                    <div className="menu__mobile-header__usuario">
                        <div className="menu__mobile-header__avatar"><span>MP</span></div>
                        <label>Hola,</label>
                        <p className="menu__mobile-header__usuario-nombre">Maureen Parra</p>
                    </div>
                }
            </header>
            <div className="menu__mobile-seccion-botones">
                <div className="menu__mobile-botones">
                    <button>Crear cuenta</button>
                </div>
                <hr className="menu__mobile-separador-botones"/> 
                <div className="menu__mobile-botones">
                    <button>Iniciar sesión</button>
                </div>                
            </div>
        </div>
       }
     </>
    )
}

export default Header;