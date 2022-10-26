import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import useForm from "../../../hooks/useFormulario";
import contextAplicacion from "../../../provider/contextAutenticacion";

const InicioSesion = () => {

    const contextoGeneral = useContext(contextAplicacion);
    const { autenticacion } = contextoGeneral;
    const { login } = autenticacion;

    const [ formularioSubmitted, setFormularioSubmitted ] = useState(false);
    const [ esValidasCredenciales, setEsValidasCredenciales ] = useState(true);
    const navigate = useNavigate();

    const formularioDatosIniciales = {
        email: '',
        contrasenia: ''
    }

    const formularioValidaciones = {
        email: [(parametro) => parametro.includes('@'), 'El correo debe ser un email válido'],
        contrasenia: [(parametro) => parametro.length > 6, 'La contraseña debe tener minimo 6 caracteres']
    }

    const { email, emailValido, contrasenia, contraseniaValido, onInputChange, EsValidoFormulario } = useForm(formularioDatosIniciales, formularioValidaciones);

    const onSubmit = (event) => {
        event.preventDefault();
        setFormularioSubmitted(true);

        const objetoDePrueba = {
            email: "maureen@gmail.com",
            contrasenia: '1234567'
        }

        const esValidasCredenciales = objetoDePrueba.email === email && objetoDePrueba.contrasenia === contrasenia ? true : false;
        setEsValidasCredenciales(esValidasCredenciales)
        if (EsValidoFormulario && esValidasCredenciales ){
            navigate("/");
            login();
        }
    };

    return (
        <div className="seccion__crear-cuenta">
            <h1>Iniciar sesión</h1>
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
                            className={`${contraseniaValido ? 'invalidInput' : 'validInput'}`}
                            required
                            value={contrasenia}
                            onChange={onInputChange}
                        />
                        <button onClick={() => {console.log('boton ojo')}}><span/></button>
                    </div>
                    {formularioSubmitted && contraseniaValido && <p>{contraseniaValido}</p> }
                </div>
                { !esValidasCredenciales || (formularioSubmitted && !EsValidoFormulario) ? <p className="formulario__crear-cuenta-invalido">Por favor vuelva a intentarlo, sus credenciales son inválidas!</p> : null }
                <div className="formulario__crear-cuenta__row-boton">
                    <button type="submit" className='formulario__crear-cuenta__boton'>Ingresar</button>
                    <p className="">¿Aún no tienes cuenta? <Link to="/crearCuenta">Registrate</Link></p>
                </div>
            </form>
        </div>
    )

}

export default InicioSesion;