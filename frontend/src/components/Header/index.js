import '../Header/styles.css'

const Header = () => {
    return (
        <header className="header">
            <div className="header__container">
                <img alt='logo empresa' src='../../assets/logo_header.png' />
                <h2>Sentirte como en tu hogar</h2>
            </div>
            <div className="header__botones">
                <button>Boton uno</button>
                <button>Boton Dos</button>
            </div>
        </header>
    )
}

export default Header;