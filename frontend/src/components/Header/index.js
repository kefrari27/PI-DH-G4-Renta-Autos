import '../Header/styles.css'

const Header = () => {
    return (
        <header className="header">
            <div className="header__container">
                <a href='https://www.google.com'><span className="header__container-imagen"></span></a>
                <a href='https://www.google.com'className='header__container-title'>Sentirte como en tu hogar</a>
            </div>
            <div className="header__botones">
                <button className='header__botones-boton'>Crear cuenta</button>
                <button className='header__botones-boton'>Iniciar sesión</button>
                <span className="header__botones-boton-mobile-icono"><button onClick={() => { console.log('hola')}} className='header__botones-boton-mobile'></button></span>
            </div>
        </header>
    )
}

export default Header;