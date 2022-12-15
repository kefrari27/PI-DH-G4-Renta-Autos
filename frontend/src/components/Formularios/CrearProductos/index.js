import { useEffect, useState } from "react";
import { getFetch, CONSTANTES, postFetch } from "../../../core/request";
import ProductHeader from "../../Producto/ProductHeader";
import '../CrearProductos/styles.css'
import useForm from "../../../hooks/useFormulario";
import { useNavigate } from "react-router-dom";

const CrearProducto = () => {
  const [listaCategorias, setListaCategorias] = useState([]);
  const [listaCiudades, setListaCiudades] = useState([]);
  const [listaCaracteristicas, setListaCaracteristicas] = useState([]);
  const [idCiudad, setIdCiudad] = useState('25');
  const [idCategoria, setIdCategoria] = useState('20');
  const [caracteristicas, setCaracteristicas] = useState([]);
  const [normasCasa, setNormasCasa] = useState("");
  const [saludSeguridad, setSaludSeguridad] = useState("");
  const [cancelacion, setCancelacion] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [urlImagen, setURLImagen] = useState("");
  const [indice, setIndice] = useState(1);
  const [creacionProductoFallido, setCreacionProductoFallido] = useState(false);

  const navigate = useNavigate();

  const formularioDatosIniciales = {
    nombreProducto: '',
    direccion: '',
    checkbox: false
  }

  const { nombreProducto, direccion, onInputChange } = useForm(formularioDatosIniciales);

  const { CIUDADES_API_URL, CATEGORIAS_API_URL, CARACTERISTICAS_API_URL, PRODUCTOS_CREAR_API_URL} = CONSTANTES;

  const consultarListas = async()=> {     
    const data = await getFetch(CIUDADES_API_URL);     
    const dataCategoriasRespuesta = await getFetch(CATEGORIAS_API_URL);
    const dataCaracteristicas = await getFetch(CARACTERISTICAS_API_URL);
    setListaCiudades(data);
    setListaCategorias(dataCategoriasRespuesta);
    setListaCaracteristicas(dataCaracteristicas);
  }   

  useEffect(() => {
    consultarListas();
  }, [])

  const onSeleccionarCiudad = (event) => {
    setIdCiudad(event.target.value);
  };

  const onSeleccionarCategoria = (event) => {
    setIdCategoria(event.target.value)
  };

  const onObtenerNormasCasa = (event) => {
    setNormasCasa(event.target.value)
  };

  const onObtenerSaludSeguridad = (event) => {
    setSaludSeguridad(event.target.value)
  };

  const onObtenerCancelacion = (event) => {
    setCancelacion(event.target.value)
  };

  const onObtenerDescripcion = (event) => {
    setDescripcion(event.target.value)
  };

  const onObtenerURLImagen = (event) => {
    setURLImagen(event.target.value)
  };


  const onObtenerValoresChecks = () => {
    let checkboxesSeleccionados = [];
    const checkboxes = document.querySelectorAll("input[type='checkbox']");
    checkboxes.forEach((checkbox) => {
      if (checkbox.checked) {
        checkboxesSeleccionados.push({id: Number(checkbox.value)});
        setCaracteristicas(checkboxesSeleccionados)
      }
    });
  };

  const onCrearInputImagen = () => {
    setIndice(indice + 1);
    const contenedorImagenes = document.getElementById('contenedorImagenes');
    const div = document.createElement("div");
    div.classList.add("formulario-producto__filas_imagen-input");
    const input = document.createElement("input");
    input.classList.add("input-url-imagen");
    input.addEventListener('change', (event) => {
      input.setAttribute('value', event.target.value);
    });
    input.setAttribute('id', indice);
    const button = document.createElement("button");
    button.setAttribute('id', indice);
    button.setAttribute('type', 'button');
    button.innerHTML = 'X';
    button.classList.add("formulario-producto__filas_imagen-input__button_eliminar");
    button.addEventListener('click', () => {
      const inputEliminar = document.getElementById(indice);
      inputEliminar.remove();
      const botonEliminar = document.getElementById(indice);
      botonEliminar.remove();
    });
    contenedorImagenes.appendChild(div);
    div.appendChild(input);
    div.appendChild(button);
  }

  const onSubmit = async (event) => {
    event.preventDefault();

    const urlImagenes = document.getElementsByClassName("input-url-imagen");

    let urlImagenesValues = [];
    Array.from(urlImagenes)?.forEach(url => {
      urlImagenesValues.push({
        titulo: `${descripcion} ${url.id}`,
        descripcion: `Imagen ${url.id}`,
        urlImagen: url.value,
        producto: {"id": `${url.id}`}
      });
    });

    const body = {
      productoDTO: {
        titulo: nombreProducto,
        categoria: { "id": Number(idCategoria) },
        ciudad: { "id": Number(idCiudad) },
        descripcion: descripcion,
        disponibilidad: "Inmediata"   
      },
      caracteristicasDTO: caracteristicas,
      imagenesDTO: urlImagenesValues,
      politicasDTO: [
        {
          "normas" : normasCasa,
          "saludYSeguridad" : saludSeguridad,
          "cancelacion" : cancelacion,
          "producto":{"id": indice}
        }
      ] 
    };

    const data = await postFetch(PRODUCTOS_CREAR_API_URL, body);
    if(data && data.id) {
      navigate(`/producto/${data.id}/creacionProducto/procesoExitoso`);
    } else {
      setCreacionProductoFallido(true);
    }
  };

  return (
    <div className="crear-producto">
      <ProductHeader titulo="Administración" />
      <h1>Crear producto</h1>
      <form onSubmit={onSubmit} className="formulario-producto">
        <div className="formulario-producto__filas">
          <div className="formulario-producto__bloque-input">
            <label className="input-label" htmlFor="nombreProducto">Nombre del producto</label>
            <input
                id="nombreProducto"
                name="nombreProducto"
                type="text"
                required
                value={nombreProducto}
                onChange={onInputChange}
            />
          </div>
          <div className="formulario-producto__bloque-selector">
              <label className="input-label" htmlFor="categoria">Categoria</label>
              <select value={idCategoria} onChange={onSeleccionarCategoria}>
                  {listaCategorias.map(element => (
                      <option id="buscador-formulario-ciudad-item" className="buscador-formulario-ciudad-item" key={element.id} value={element.id}>
                          <span className="city-select__item-name">{element.titulo}</span>
                      </option>
                  ))}
              </select>
          </div>
        </div>
        <div className="formulario-producto__filas">
          <div className="formulario-producto__bloque-input">
            <label className="input-label" htmlFor="direccion">Direccion</label>
            <input 
                className="" 
                id="direccion"
                name="direccion"
                type="text"
                required
                value={direccion}
                onChange={onInputChange}
            />
          </div>
          <div className="formulario-producto__bloque-selector">
              <label className="input-label" htmlFor="apellido">Ciudad</label>
              <select value={idCiudad} onChange={onSeleccionarCiudad}>
                  {listaCiudades.map(element => (
                      <option id="buscador-formulario-ciudad-item" className="buscador-formulario-ciudad-item" key={element.id} value={element.id}>
                          <span className="city-select__item-name">{element.nombre}</span>
                      </option>
                  ))}
              </select>
          </div>
        </div>

        <div className="formulario-producto__filas_text_area">
          <label className="input-label">Descripción</label>
          <textarea name="textarea" rows="10" cols="50" value={descripcion} onChange={onObtenerDescripcion}/>
        </div>

        <h3>Agregar atributos</h3>
        <div className="contenedor-checkbox">
          {
            listaCaracteristicas.map(caracteristica => (
            <div>
              <input className="checkbox" type="checkbox" name='checkbox' id={caracteristica.id} value={caracteristica.id} onChange={onObtenerValoresChecks}/>
              <label>{caracteristica.titulo}</label> 
            </div>
            ))
          }
        </div>

        <h3>Políticas del producto</h3>
        <div className="formulario-producto__filas formulario-producto__filas-contenedor">
          <div className="formulario-producto__filas-apartados">
            <h4>Normas del vehículo</h4>
            <p>Descripción</p>
            <textarea name="textarea" rows="10" cols="50" value={normasCasa} onChange={onObtenerNormasCasa}/>
          </div>
          <div className="formulario-producto__filas-apartados">
            <h4>Salud y seguridad</h4>
            <p>Descripción</p>
            <textarea name="textarea" rows="10" cols="50" value={saludSeguridad} onChange={onObtenerSaludSeguridad}/>
          </div>
          <div className="formulario-producto__filas-apartados">
            <h4>Política de cancelación</h4>
            <p>Descripción</p>
            <textarea name="textarea" rows="10" cols="50" value={cancelacion} onChange={onObtenerCancelacion}/>
          </div>
        </div>

        <h3>Cargar imágenes</h3>
        <div id='contenedorImagenes' className="formulario-producto__filas_imagen">
          <div className="formulario-producto__filas_imagen-input">
            <input
                id={0}
                name="first"
                className="input-url-imagen"
                type="text"
                required
                value={urlImagen}
                onChange={onObtenerURLImagen}
            />
            <button type="button" className="formulario-producto__filas_imagen-input__button_agregar" onClick={onCrearInputImagen}>+</button>
          </div>
        </div>
        { creacionProductoFallido ?
        <p className='detalle-reserva-fallida'>
          Lamentablemente el producto no ha podido crearse. Por favor intente más tarde.
        </p> : null }
        <button type="submit" className="formulario-producto__boton-crear">Crear</button>
      </form>
    </div>
  )

}

export default CrearProducto;