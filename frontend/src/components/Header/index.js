import { useContext, useState } from 'react';
import autenticacionContext from '../../context/autenticacion/autenticacionContext';
import ControlMobileContext from '../../context/controlMobile/controlMobileContext';
import { Link, useNavigate } from "react-router-dom";
import '../Header/styles.css'

const Header = () => {
    const contextoAutenticacion = useContext(autenticacionContext);
    const contextoControlMobile = useContext(ControlMobileContext);
    const { estadoAutenticacion, setAutenticacionEstado, datosUsuario } = contextoAutenticacion;
    const {idUsuario, nombre, apellido, rol} = datosUsuario;
    const datosDeLocalStorage = localStorage.getItem('datosUsuario');
    const { esVersionMobileHeaderMenu, setVersionMobileHeaderMenu } = contextoControlMobile;

    const navigate = useNavigate();

    const nombreLetra = nombre ? nombre?.slice(0, 1) : JSON.parse(datosDeLocalStorage)?.nombre?.slice(0, 1);
    const apellidoLetra = apellido ? apellido?.slice(0, 1) : JSON.parse(datosDeLocalStorage)?.apellido?.slice(0, 1);

    const cerrarSesion = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('datosUsuario');
        setAutenticacionEstado(null)
    };

    const cerrarSesionMobile = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('datosUsuario');
        setAutenticacionEstado(null)
        setVersionMobileHeaderMenu()
    };

    const manejadorClickAvatar = () => {
        navigate(`/${idUsuario}/misReservas`);
    };

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
                    {JSON.parse(datosDeLocalStorage)?.rol?.id === 244 && <div className="header__administracion__label"><h3><Link to="/administracion">Administración</Link></h3></div>}
                    <div className="header__avatar" onClick={manejadorClickAvatar}><span>{nombreLetra}{apellidoLetra}</span></div>
                    <div className="header__usuario">
                        <p className="header__cerrar-sesion"><button className="header__cerrar-sesion-boton" onClick={cerrarSesion}>X</button></p>
                        <label>Hola,</label>
                        <p className="header__usuario-nombre">{nombre ? nombre : JSON.parse(datosDeLocalStorage)?.nombre} {apellido ? apellido : JSON.parse(datosDeLocalStorage)?.apellido}</p>
                    </div>
                </div>}
                <span className="header__botones-boton-mobile-icono"><button onClick={setVersionMobileHeaderMenu} className='header__botones-boton-mobile'></button></span>
            </div>
        </header> :
        <div className="menu__mobile">
            <header className="menu__mobile-header">
                <span><button className="menu__mobile-header-cerrar-sesion" onClick={cerrarSesionMobile}>X</button></span>
                {!estadoAutenticacion ?
                    <p className="menu__mobile-header-menu">Menú</p> :
                    <div className="menu__mobile-header__usuario">
                        <div className="menu__mobile-header__avatar" onClick={manejadorClickAvatar}><span>{nombreLetra}{apellidoLetra}</span></div>
                        <label>Hola,</label>
                        <p className="menu__mobile-header__usuario-nombre">{nombre ? nombre : JSON.parse(datosDeLocalStorage)?.nombre} {apellido ? apellido : JSON.parse(datosDeLocalStorage)?.apellido}</p>
                    </div>
                }
            </header>
            <div className="menu__mobile-seccion-botones">
                <div className="menu__mobile-botones">
                    <button><Link to="/crearCuenta">Crear Cuenta</Link></button>
                </div>
                <hr className="menu__mobile-separador-botones"/> 
                <div className="menu__mobile-botones">
                    <button><Link to="/inicioSesion">Iniciar sesión</Link></button>
                </div>                
            </div>
        </div>
       }
     </>
    )
}

export default Header;