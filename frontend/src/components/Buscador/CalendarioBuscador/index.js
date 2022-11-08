import DatePicker, {registerLocale} from "react-datepicker";
import React, {useState} from "react";
import "react-datepicker/dist/react-datepicker.css";
import es from "date-fns/locale/es";

registerLocale("es", es);

const CalendarioBuscador = () => {
    const [rango, setRango] = useState([null, null]);
    const [fechaInicio, fechaFinal] = rango;
    // console.log(rango);

    return (
        <div className="calendario-buscador__contenedor" id="calendario-buscador__contenedor">
            <DatePicker
                placeholderText="Check In - Check out"
                selectsRange={true}
                startDate={fechaInicio}
                endDate={fechaFinal}
                minDate={new Date()}
                dateFormat="dd 'de' MMM"
                onChange={(update)=>{
                    setRango(update);
                }}
                isClearable={true}
                locale="es"
                monthsShown={2}
            />
        </div>
    );
}

export default CalendarioBuscador;