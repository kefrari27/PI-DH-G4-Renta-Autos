import '../Footer/styles.css'

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer_copywrite">
                <span>©2021 Digital Booking</span>
            </div>
            <div className="header__redes_sociales">
                <span className='icon__facebook' />
                <span className='icon__linkedin' />
                <span className='icon__twitter' />
                <span className='icon__instagram' />
            </div>
        </footer>
    )
}

export default Footer;