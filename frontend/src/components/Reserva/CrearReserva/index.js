import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useForm from "../../../hooks/useFormulario";
import autenticacionContext from "../../../context/autenticacion/autenticacionContext";
import CalendarioReservas from './CalendarioReservas';
import './styles.css'

const CrearReserva = ({lecturaFecha, lecturaHora}) => {

    /* Listado horarios */
    const horarios = [
        { value: "00:00:00", label: "00:00 AM" },
        { value: "01:00:00", label: "01:00 AM" },
        { value: "02:00:00", label: "02:00 AM" },
        { value: "03:00:00", label: "03:00 AM" },
        { value: "04:00:00", label: "04:00 AM" },
        { value: "05:00:00", label: "05:00 AM" },
        { value: "06:00:00", label: "06:00 AM" },
        { value: "07:00:00", label: "07:00 AM" },
        { value: "08:00:00", label: "08:00 AM" },
        { value: "09:00:00", label: "09:00 AM" },
        { value: "10:00:00", label: "10:00 AM" },
        { value: "11:00:00", label: "11:00 AM" },
        { value: "12:00:00", label: "12:00 PM" },
        { value: "13:00:00", label: "01:00 PM" },
        { value: "14:00:00", label: "02:00 PM" },
        { value: "15:00:00", label: "03:00 PM" },
        { value: "16:00:00", label: "04:00 PM" },
        { value: "17:00:00", label: "05:00 PM" },
        { value: "18:00:00", label: "06:00 PM" },
        { value: "19:00:00", label: "07:00 PM" },
        { value: "20:00:00", label: "08:00 PM" },
        { value: "21:00:00", label: "09:00 PM" },
        { value: "22:00:00", label: "10:00 PM" },
        { value: "23:00:00", label: "11:00 PM" },
    ];
    const datosDeLocalStorage = JSON.parse(localStorage.getItem('datosUsuario'));
  
    const nombreCiudad = datosDeLocalStorage?.ciudad?.nombre;
    
    const contextoAutenticacion = useContext(autenticacionContext);
    const { login } = contextoAutenticacion;
    const [ formularioSubmitted, setFormularioSubmitted ] = useState(false);
    const [ formularioValido, setFormularioValido ] = useState(true);

    const [horaLlegada, setHoraLlegada] = useState();

    const navigate = useNavigate();

    const formularioDatosIniciales = {
        nombre: datosDeLocalStorage?.nombre,
        apellido: datosDeLocalStorage?.apellido,
        email: datosDeLocalStorage?.correo,
        ciudad: nombreCiudad,
    }

    const formularioValidaciones = {
        email: [(parametro) => parametro.includes('@'), 'El correo debe ser un email válido']
    }

    const { emailValido, onInputChange, EsValidoFormulario } = useForm(formularioDatosIniciales, formularioValidaciones);

    useEffect(() => {
        const esValidoFor = !EsValidoFormulario ? false : true;
        setFormularioValido(esValidoFor);
    }, [EsValidoFormulario])

    const onSubmit = (event) => {
        event.preventDefault();
        setFormularioSubmitted(true);

        if (formularioSubmitted && formularioValido){
            navigate("/");
            login();
        }
    };

    const onSeleccionarHora = (event) => {
        setHoraLlegada(event.target.value);
        console.log(horaLlegada);
        lecturaHora(horaLlegada);
    }

    return (
        <div className="seccion__crear-reserva">
            <form onSubmit={onSubmit} className="formulario__crear-reserva">
                <section className="formulario__crear-reserva-datos__seccion">
                    <h2>Completá tus datos</h2>
                    <div className="formulario__crear-reserva__container">
                        <div className="formulario__crear-reserva__bloque">
                            <div className="formulario__crear-reserva-nombre__row">
                                <label htmlFor="nombre">Nombre</label>
                                <input 
                                    className="input__nombre" 
                                    id="nombre"
                                    name="nombre"
                                    type="text"
                                    value={datosDeLocalStorage?.nombre}
                                    disabled
                                />
                            </div>
                            <div className="formulario__crear-reserva-apellido__row">
                                <label htmlFor="apellido">Apellido</label>
                                <input 
                                    className="input__apellido"
                                    type="text" 
                                    id="apellido" 
                                    name="apellido"
                                    disabled
                                    value={datosDeLocalStorage?.apellido}
                                />
                            </div>
                            <div className="formulario__crear-reserva-email__row" >
                                <label htmlFor="email">Correo electrónico</label>
                                <input 
                                    type="email" 
                                    id="email" 
                                    name="email"
                                    className="input__email"
                                    required
                                    value={datosDeLocalStorage?.correo}
                                    onChange={onInputChange}
                                    disabled
                                />
                                {formularioSubmitted && emailValido && <p>{emailValido}</p> }
                            </div>
                            <div className="formulario__crear-reserva-ciudad__row">
                                <label htmlFor="ciudad">Ciudad</label>
                                <input
                                    className="input__ciudad"
                                    type="text" 
                                    id="ciudad" 
                                    name="ciudad"
                                    value={nombreCiudad}
                                    disabled
                                />
                            </div>
                        </div>
                    </div>
                </section>
                <section className="formulario__crear-reserva-fecha__seccion">
                    <h2>Seleccioná tu fecha de reserva</h2>
                    <CalendarioReservas actualizarFecha={lecturaFecha}/>
                </section>
                <section className="formulario__crear-reserva-horario__seccion">
                    <h2>Tu horario de llegada</h2>
                    <div className="formulario__crear-reserva-horario__bloque">
                        <h4><span>00</span> Tu vehiculo va a estar disponible para que lo retires al momento que dispongas </h4>
                        <p>Indica tu horario estimado de llegada</p>
                        <select className="formulario__crear-reserva-horario" id="horaReserva" onChange={onSeleccionarHora}>
                            <option selected disabled value="">Selecciona tu horario</option>
                            {horarios.map((i) => <option value={i.value}>{i.label}</option>)}
                        </select>
                    </div>
                </section>
            </form>
        </div>
    )
}

export default CrearReserva;