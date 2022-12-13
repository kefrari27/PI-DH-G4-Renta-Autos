import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import useForm from "../../../hooks/useFormulario";
import autenticacionContext from "../../../context/autenticacion/autenticacionContext";
import { postFetch, getFetch, CONSTANTES } from "../../../core/request"

const InicioSesion = () => {
    const contextoAutenticacion = useContext(autenticacionContext);
    const { setAutenticacionEstado, setDatosUsuario } = contextoAutenticacion;

    const { AUTENTICACION_API_URL, USUARIOS_ID_API_URL } = CONSTANTES;
    const [ formularioSubmitted, setFormularioSubmitted ] = useState(false);
    const [ logueadoCorrecto, setLogueadoCorrecto ] = useState(true);
    const navigate = useNavigate();

    const formularioDatosIniciales = {
        email: '',
        contrasenia: ''
    }

    const formularioValidaciones = {
        email: [(parametro) => parametro.includes('@'), 'El correo debe ser un email válido'],
        contrasenia: [(parametro) => parametro.length > 6, 'La contraseña debe tener minimo 7 caracteres']
    }

    const { email, emailValido, contrasenia, contraseniaValido, onInputChange, EsValidoFormulario } = useForm(formularioDatosIniciales, formularioValidaciones);

    const onSubmit = async (event) => {
        event.preventDefault();
        setFormularioSubmitted(true);

        const body = {
            usuario: email,
            clave: contrasenia
        }

        const data = await postFetch(AUTENTICACION_API_URL, body);

        if (EsValidoFormulario && data.jwtToken){
            localStorage.setItem('token', data.jwtToken);
            localStorage.setItem('uid', data.usuarioId);
            setAutenticacionEstado(data.jwtToken);
            const url = `${USUARIOS_ID_API_URL}/${data.usuarioId}`
            const dataUsuario = await getFetch(url);
            localStorage.setItem('datosUsuario', JSON.stringify(dataUsuario));
            setDatosUsuario(dataUsuario)
            navigate("/");
        } else {
            setLogueadoCorrecto(false)
        }
    };

    return (
        <div className="seccion__crear-cuenta">
            <h1>Iniciar sesión</h1>
            { !logueadoCorrecto && <div className="bloque__informativo">
                <p>Lamentablemente no ha podido iniciar sesión. Por favor intente más tarde!</p>
            </div>}
            <form onSubmit={onSubmit} className="formulario__crear-cuenta">
                <div className="formulario__crear-cuenta__row">
                    <label htmlFor="email">Correo electrónico</label>
                    <input 
                        type="email" 
                        id="email" 
                        name="email"
                        required
                        onChange={onInputChange}
                        value={email}
                    />
                    {formularioSubmitted && emailValido && <p>{emailValido}</p> }
                </div>
                <div className="formulario__crear-cuenta__row">
                    <label htmlFor="contrasenia">Contraseña</label>
                    <div className="formulario__crear-cuenta__row-password">
                        <input 
                            type="password" 
                            id="contrasenia" 
                            name="contrasenia"
                            className={`${contraseniaValido && contrasenia.length > 0 ? 'invalidInput': 'validInput'}`}
                            required
                            value={contrasenia}
                            onChange={onInputChange}
                        />
                        <button onClick={() => {console.log('boton ojo')}}><span/></button>
                    </div>
                    {formularioSubmitted && contraseniaValido && <p>{contraseniaValido}</p> }
                </div>
                { (formularioSubmitted && !EsValidoFormulario) ? <p className="formulario__crear-cuenta-invalido">Por favor vuelva a intentarlo, sus credenciales son inválidas!</p> : null }
                <div className="formulario__crear-cuenta__row-boton">
                    <button type="submit" className='formulario__crear-cuenta__boton'>Ingresar</button>
                    <p className="">¿Aún no tienes cuenta? <Link to="/crearCuenta">Registrate</Link></p>
                </div>
            </form>
        </div>
    )

}

export default InicioSesion;