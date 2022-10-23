import React from 'react';

const contextAplicacion = React.createContext(
    {estadoAutenticacion:false,login:()=>{}}
);

export default contextAplicacion;