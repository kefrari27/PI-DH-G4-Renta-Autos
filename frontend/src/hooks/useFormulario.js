import { useEffect, useMemo, useState } from 'react';

const useForm = ( estadoInicialFormulario = {}, validacionesRealizar = {} ) => {
  
    const [ formularioEstado, setFormularioEstado ] = useState( estadoInicialFormulario );
    /** Cuando requerimos volver a renderizar por cambios en el 
     * estado debemos usar un hook que maneje el estado */
    const [ formularioValidaciones, setFormularioValidaciones ] = useState({});

    useEffect(() => {
        crearValidaciones();

    // eslint-disable-next-line
    }, [formularioEstado]);

    const EsValidoFormulario = useMemo(() =>{
        for (const valorFormulario of Object.keys(formularioValidaciones)) {
            if( formularioValidaciones[valorFormulario] !== null) {
                return false;
            }
        }
        return true;
    }, [formularioValidaciones]);

    const onInputChange = ({ target }) => {
        const { name, value } = target;
        setFormularioEstado({
            ...formularioEstado,
            [ name ]: value
        });
    }

    const onResetForm = () => {
        setFormularioEstado( estadoInicialFormulario );
    }

    const crearValidaciones = () => {
        const formularioValoresRevisados = {};
        for (const formularioCampo of Object.keys(validacionesRealizar)) {
            /**  Devuelve cada arreglo de cada propiedad (email, contrasenia)*/
            const [ funcionValidacion, mensajeError = 'Este valor es requerido' ] = validacionesRealizar[formularioCampo];
            /** creación de variable dinámica dentro del arreglo para cada propiedad (email, contrasenia), 
             * según resultado de la función, si es válido no devuelve nada porque no hay error pero si hay error devuelve el mensaje
             * de error.
             */
            formularioValoresRevisados[`${ formularioCampo}Valido`] = funcionValidacion(formularioEstado[formularioCampo]) ? null : mensajeError;
        }
        setFormularioValidaciones(formularioValoresRevisados);
    }


    return {
        ...formularioEstado,
        formularioEstado,
        onInputChange,
        onResetForm,
        ...formularioValidaciones,
        EsValidoFormulario
    }
}

export default useForm;