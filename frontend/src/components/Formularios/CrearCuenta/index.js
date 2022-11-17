import { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import useForm from "../../../hooks/useFormulario";
import autenticacionContext from "../../../context/autenticacion/autenticacionContext";
import '../CrearCuenta/styles.css'

const CrearCuenta = () => {

    const contextoAutenticacion = useContext(autenticacionContext);
    const { login } = contextoAutenticacion;

    const [ confirmarContraseniaEstado, setConfirmarContraseniaEstado ] = useState('');
    const [ esConfirmarContraseniaEstado, setEsConfirmarContraseniaEstado ] = useState(true);
    const [ formularioSubmitted, setFormularioSubmitted ] = useState(false);
    const [ esContraseniasValidas, setEsContraseniasValidas ] = useState(false);
    const [ formularioValido, setFormularioValido ] = useState(true);

    const navigate = useNavigate();

    const formularioDatosIniciales = {
        nombre: '',
        apellido: '',
        email: '',
        contrasenia: '',
        confirmarContrasenia: ''
    }

    const formularioValidaciones = {
        email: [(parametro) => parametro.includes('@'), 'El correo debe ser un email válido'],
        contrasenia: [(parametro) => parametro.length > 6, 'La contraseña debe tener minimo 6 caracteres']
    }

    const { email, emailValido, contrasenia, contraseniaValido, onInputChange, EsValidoFormulario } = useForm(formularioDatosIniciales, formularioValidaciones);


    const onValidarConfirmarContrasenia = (e) => {
        const valorIngresado = e.target.value;
        setConfirmarContraseniaEstado(valorIngresado);
        let esConfirmarContraseniaValido = true;

        if (valorIngresado === contrasenia){
            esConfirmarContraseniaValido = false;
            setEsContraseniasValidas(true)
        }else {
            setEsContraseniasValidas(false)
        }
        setEsConfirmarContraseniaEstado(esConfirmarContraseniaValido);
    };

    useEffect(() => {
        const esValidoFor = !EsValidoFormulario || !esContraseniasValidas ? false : true;
        setFormularioValido(esValidoFor);
    
    }, [esContraseniasValidas, EsValidoFormulario])


    const onSubmit = (event) => {
        event.preventDefault();
        setFormularioSubmitted(true);

        if (formularioSubmitted && formularioValido){
            navigate("/");
            login();
        }
    };

    return (
        <div className="seccion__crear-cuenta">
            <h1>Crear cuenta</h1>
            <form onSubmit={onSubmit} className="formulario__crear-cuenta">
                <div className="formulario__crear-cuenta__bloque">
                    <div className="formulario__crear-cuenta__row formulario__crear-cuenta__row-nombre-apellido">
                        <label htmlFor="nombre">Nombre</label>
                        <input 
                            className="input__nombre-apellido" 
                            id="nombre"
                            name="nombre"
                            type="text"
                            required
                        />
                    </div>
                    <div className="formulario__crear-cuenta__row formulario__crear-cuenta__row-nombre-apellido">
                        <label htmlFor="apellido">Apellido</label>
                        <input 
                            className="input__nombre-apellido"
                            type="text" 
                            id="apellido" 
                            name="apellido"
                            required
                        />
                    </div>
                </div>
                <div className="formulario__crear-cuenta__row" >
                    <label htmlFor="email">Correo electrónico</label>
                    <input 
                        type="email" 
                        id="email" 
                        name="email"
                        className={`${emailValido ? 'invalidInput' : 'validInput'}`}
                        required
                        value={email}
                        onChange={onInputChange}
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
                <div className="formulario__crear-cuenta__row">
                    <label htmlFor="confirmarContrasenia">Confirmar contraseña</label>
                    <input 
                        type="password" 
                        id="confirmarContrasenia" 
                        name="confirmarContrasenia"
                        className={`${esConfirmarContraseniaEstado ? 'invalidInput' : 'validInput'}`}
                        required
                        value={confirmarContraseniaEstado}
                        onChange={onValidarConfirmarContrasenia}
                    />
                    {formularioSubmitted && esConfirmarContraseniaEstado && <p>Las contraseñas deben ser iguales</p> }
                </div>
                {formularioSubmitted && !formularioValido && <p className="formulario__crear-cuenta-invalido">Por favor vuelva a intentarlo, sus credenciales son inválidas!</p> }
                <div className="formulario__crear-cuenta__row-boton">
                    <button type="submit" className='formulario__crear-cuenta__boton'>Crear cuenta</button>
                    <p>¿Ya tienes una cuenta? <Link to="/inicioSesion">Iniciar sesión</Link></p>
                </div>
            </form>
        </div>
    )
}

export default CrearCuenta;