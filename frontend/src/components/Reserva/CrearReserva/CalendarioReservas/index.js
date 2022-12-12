import { useState, useContext } from "react";
import DatePicker from "react-datepicker";
//import { addDays, add } from "date-fns";
import "../CalendarioReservas/styles.css";

const CalendarioReservas = ({actualizarFecha}) => {

    const [fechaInicial, setFechaInicial] = useState(null);
    const [fechaFinal, setFechaFinal] = useState(null);
    const onChange = (fechas) => {
        const [fechaInicio, fechaFinal] = fechas;
        setFechaInicial(fechaInicio);
        setFechaFinal(fechaFinal);
        actualizarFecha(fechaInicio,fechaFinal);
    };
    console.log(fechaInicial);
    console.log(fechaFinal);

    return (
        <div className="calendario-reserva__contenedor">
            <DatePicker
                selected={fechaInicial}
                onChange={onChange}
                startDate={fechaInicial}
                endDate={fechaFinal}
                minDate={new Date()}
                dateFormat="dd 'de' MMM"
                //showDisabledMonthNavigation
                selectsRange
                //selectsDisabledDaysInRange
                inline
                calendarClassName="calendario-reserva"
                monthsShown={2}
            />
        </div>
    );
};

export default CalendarioReservas;