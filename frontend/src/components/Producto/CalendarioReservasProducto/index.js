import { useState } from "react";
import DatePicker, {registerLocale} from "react-datepicker";
import es from "date-fns/locale/es";
//import { addDays } from "date-fns";
import "./styles.css";
import { useNavigate } from "react-router-dom";

registerLocale("es", es);

const CalendarioReservasProducto = ({identificador}) => {
   const [fechaInicial, setFechaInicial] = useState(null);
    const [fechaFinal, setFechaFinal] = useState(null);
    const onChange = (fechas) => {
        const [fechaInicio, fechaFin] = fechas;
        setFechaInicial(fechaInicio);
        setFechaFinal(fechaFin);
    };
    const navigate = useNavigate();

    const onIniciarReserva = () => {
      navigate(`/producto/${identificador}/reserva`)
    }
  /* const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(null);
  const onChange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  }; */
  return (
    <div className="calendario-reserva-boton-reserva-contenedor">
        <h2>Fechas disponibles</h2>
        <div className="calendario-reserva-boton-reserva">
            {/*    <DatePicker
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
                    /> */}
            <DatePicker
                // selected={fechaInicial}
                onChange={onChange}
                startDate={fechaInicial}
                endDate={fechaFinal}
                minDate={new Date()}
                // dateFormat="dd 'de' MMM"
                //showDisabledMonthNavigation
                selectsRange
                //selectsDisabledDaysInRange
                inline
                calendarClassName="calendario-reserva"
                locale="es"
                monthsShown={2}
            />
            <div className="boton-reserva__contenedor">
              <div className="boton-reserva__borde">
                  <p>Agrega tus fechas de viaje para obtener precios exactos</p>
                  <button onClick={onIniciarReserva}>Iniciar reserva</button>
              </div>
            </div>
        </div>
    </div>
  );
};

export default CalendarioReservasProducto;
