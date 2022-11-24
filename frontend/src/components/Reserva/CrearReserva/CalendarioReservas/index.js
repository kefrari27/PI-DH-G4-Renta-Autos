import { useState, useContext } from "react";
import DatePicker from "react-datepicker";
import { addDays, add } from "date-fns";
import ControlMobileContext from "../../../../context/controlMobile/controlMobileContext";
import "../CalendarioReservas/styles.css";

const CalendarioReservas = ({actualizarFecha}) => {

    /* const contextoControlMobile = useContext(ControlMobileContext);
    const { esVersionMobileCalendario, setVersionMobileCalendario } = contextoControlMobile;

    const [fechaInicial, setFechaInicial] = useState(new Date());
    const [fechaFinal, setFechaFinal] = useState(null);
    const onChange = (fechas) => {
        const [fechaInicio, fechaFin] = fechas;
        setFechaInicial(fechaInicio);
        setFechaFinal(fechaFin);
    }; */
    const [fechaInicial, setFechaInicial] = useState(new Date());
    const [fechaFinal, setFechaFinal] = useState(null);
    const onChange = (fechas) => {
        const [fechaInicio, fechaFinal] = fechas;
        setFechaInicial(fechaInicio);
        setFechaFinal(fechaFinal);
        actualizarFecha(fechaInicio,fechaFinal)
    };
    console.log(fechaInicial);
    console.log(fechaFinal);

    return (
        <div className="calendario-reserva__contenedor">
            {/* {
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
            } */}
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