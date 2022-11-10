import { useState } from "react";
import DatePicker from "react-datepicker";
import { addDays, add } from "date-fns";
import "./styles.css"

const CalendarioReservasProducto = () => {
    const [fechaInicial, setFechaInicial] = useState(new Date());
    const [fechaFinal, setFechaFinal] = useState(null);
    const onChange = (fechas) => {
        const [fechaInicio, fechaFin] = fechas;
        setFechaInicial(fechaInicio);
        setFechaFinal(fechaFin);
    };

    return (
        <div className="calendario-reserva-boton-reserva-contenedor">
            <h2 >Fechas disponibles</h2>
            <div className="calendario-reserva-boton-reserva">
                <DatePicker
                onChange={onChange}
                startDate={fechaInicial}
                endDate={fechaFinal}
                excludeDates={[addDays(new Date(), 1), addDays(new Date(), 5)]}
                excludeDateIntervals={[{start: add(new Date(), {months: 1}), end: add(new Date(), {months: 1, weeks: 1})}]}
                selectsRange={false}
                selectsDisabledDaysInRange={false}
                inline
                monthsShown={2}
                calendarClassName="calendario-reserva"
                locale="es"
                />
                <div className="boton-reserva-contenedor">
                    <p>Agrega tus fechas de viaje para obtener precios exactos</p>
                    <button>Iniciar reserva</button>
                </div>
            </div>
        </div>
    );
};

export default CalendarioReservasProducto;