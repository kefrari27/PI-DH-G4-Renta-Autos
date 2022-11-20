import { useState, useContext } from "react";
import DatePicker from "react-datepicker";
import { addDays, add } from "date-fns";
import ControlMobileContext from "../../../../context/controlMobile/controlMobileContext";
import "../CalendarioReservas/styles.css";

const CalendarioReservas = () => {

    const contextoControlMobile = useContext(ControlMobileContext);
    const { esVersionMobileCalendario, setVersionMobileCalendario } = contextoControlMobile;

    const [fechaInicial, setFechaInicial] = useState(new Date());
    const [fechaFinal, setFechaFinal] = useState(null);
    const onChange = (fechas) => {
        const [fechaInicio, fechaFin] = fechas;
        setFechaInicial(fechaInicio);
        setFechaFinal(fechaFin);
    };

    return (
        <div className="calendario-reserva__contenedor">
            {
                !esVersionMobileCalendario ?
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
                /> :
                <DatePicker
                    onChange={onChange}
                    startDate={fechaInicial}
                    endDate={fechaFinal}
                    excludeDates={[addDays(new Date(), 1), addDays(new Date(), 5)]}
                    excludeDateIntervals={[{start: add(new Date(), {months: 1}), end: add(new Date(), {months: 1, weeks: 1})}]}
                    selectsRange={false}
                    selectsDisabledDaysInRange={false}
                    inline
                    monthsShown={1}
                    calendarClassName="calendario-reserva"
                    locale="es"
                />
            }
        </div>
    );
};

export default CalendarioReservas;