package com.dh.backend_G4.controller;


import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.Caracteristica;
import com.dh.backend_G4.model.FiltroProductoReq;
import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.modelDTO.*;
import com.dh.backend_G4.service.interfaceService.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {
    public static Logger logger = Logger.getLogger(ProductoController.class);
    private final IProductoService productoService;
    private final IReservaService reservaService;
    private final ICaracteristicaService caracteristicaService;
    private final IImagenService imagenService;
    private final IPoliticaService politicaService;
    private final ObjectMapper mapper;

    public ProductoController(IProductoService productoService, IReservaService reservaService, ICaracteristicaService caracteristicaService, IImagenService imagenService, IPoliticaService politicaService, ObjectMapper mapper) {
        this.productoService = productoService;
        this.reservaService = reservaService;
        this.caracteristicaService = caracteristicaService;
        this.imagenService = imagenService;
        this.politicaService = politicaService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Set<ProductoDTO>> getProductos() throws ResourceNotFoundException {
        logger.info("Listando Productos");
        Set<ProductoDTO> productos = productoService.listar();
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }else{
            throw new ResourceNotFoundException("No hay Productos para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Producto por id " + id);
        ResponseEntity<ProductoDTO> response = null;
        if(id !=0){
            ProductoDTO productoDTOById = productoService.buscar(id);
            if(productoDTOById != null){
                response = ResponseEntity.ok(productoDTOById);
            }else{
                throw new ResourceNotFoundException("Producto no encontrado");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) throws ResourceNotFoundException{
        logger.info("Agregando Producto");
        productoService.guardar(productoDTO);
        ProductoDTO productoCreado = productoService.buscarProductoByTituloAndCategoriaAndCiudad(productoDTO.getTitulo(), productoDTO.getCategoria().getId(), productoDTO.getCiudad().getId());
        if(productoCreado != null){
            return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
        }else{
            throw new ResourceNotFoundException("El producto no pudo ser almacenado");
        }
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoDTO productoDTO) throws ResourceNotFoundException{
        ResponseEntity<ProductoDTO> response = null;
        if(productoDTO.getId() != null && productoService.buscar(productoDTO.getId()) != null){
            logger.info("Actualizando Producto con id "+productoDTO.getId());
            response = ResponseEntity.ok(productoService.actualizar(productoDTO));
        }else{
            throw new ResourceNotFoundException("Producto no encontrado para ser actualizado");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && productoService.buscar(id) != null){
            logger.info("Eliminando Producto con id "+id);
            productoService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Producto no encontrado para ser eliminado");
        }
        return response;
    }

    @GetMapping("/imagenesByProductoId/{id}")
    public ResponseEntity<Set<ImagenDTO>> getImagenesByProductoId(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Imagenes por ProductoId " + id);
        ResponseEntity<Set<ImagenDTO>> response = null;
        if(id !=0){
            Set<ImagenDTO> imagenDTOByProductoId = productoService.listarImagenesByProducto(id);
            if(!imagenDTOByProductoId.isEmpty()){
                response = ResponseEntity.ok(imagenDTOByProductoId);
            }else{
                throw new ResourceNotFoundException("Imagen no encontrada para producto id = "+id);
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @DeleteMapping("/imagenesByProductoId/{id}")
    public ResponseEntity<HttpStatus> deleteImagenesByProducto(@PathVariable("id") Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && productoService.buscar(id) != null){
            logger.info("Eliminando Imagenes de Producto con id "+id);
            productoService.eliminarImagenesByProducto(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Producto no encontrado para ser eliminado");
        }
        return response;
    }

    @PostMapping("/addCaracteristica")
    public ResponseEntity<ProductoDTO> addCaracteristica(@RequestBody AddCaracteristicaDTO addCaracteristicaDTO) throws ResourceNotFoundException{
        logger.info("Agregando Caracteristica");
        return new ResponseEntity<>(productoService.addCaracteristica(addCaracteristicaDTO), HttpStatus.OK);
    }

    @GetMapping("/productosByCategoria/{id}")
    public ResponseEntity<Set<ProductoDTO>> getProductosByCategoriaId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.info("Listando Productos por Categoria");
        Set<ProductoDTO> productos = productoService.listarProductosByCategoria(id);
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }else{
            throw new ResourceNotFoundException("No hay Productos para listar para la Categoria seleccionada");
        }
    }

    @GetMapping("/productosByCiudad/{id}")
    public ResponseEntity<Set<ProductoDTO>> getProductosByCiudadId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.info("Listando Productos por Ciudad");
        Set<ProductoDTO> productos = productoService.listarProductosByCiudad(id);
        if(!productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }else{
            throw new ResourceNotFoundException("No hay Productos para listar para la Ciudad seleccionada");
        }
    }

    @GetMapping("/productosAleatorios/{cantidad}")
    public ResponseEntity<Set<ProductoDTO>> getProductosAleatorios(@PathVariable("cantidad") Integer cantidad) throws ResourceNotFoundException{
        logger.info("Generando " + cantidad + " productos aleatorios");
        Set<ProductoDTO> productosAleatorios = productoService.listarProductosAleatorios(cantidad);
        if(!productosAleatorios.isEmpty()){
            return ResponseEntity.ok(productosAleatorios);
        }else{
            throw new ResourceNotFoundException("No hay Productos para mostrar aleatoriamente");
        }
    }

    @PostMapping("/filtroProductosByFechas")
    public Set<ProductoDTO> getProductosByFechas(@RequestBody FiltroProductoReq filtro) throws ResourceNotFoundException{
        logger.info("Filtrando Productos con fechaCheckIn = "+filtro.getFechaCheckIn()+" y fechaCheckOut = "+filtro.getFechaCheckOut());

        //Estructuras
        Set<ProductoDTO> productos = new HashSet<>();
        Set<ReservaDTO> reservasDTOS = new HashSet<>();
        List<Boolean> isRango = new ArrayList<>();
        Set<ProductoDTO> productosDisponibles = new HashSet<>();
        Long ciudadId = filtro.getCiudad().getId();

        if(filtro.getFechaCheckIn().isEmpty() || filtro.getFechaCheckOut().isEmpty()){
            productos = productoService.listarProductosByCiudad(ciudadId);
            for (ProductoDTO producto:productos) {
                productosDisponibles.add(producto);
            }
        }else{
            LocalDate fechaCheckIn = LocalDate.parse(filtro.getFechaCheckIn());
            LocalDate fechaCheckOut = LocalDate.parse(filtro.getFechaCheckOut());

            if(ciudadId != 0){
                //Se obtiene la lista de productos por ciudad
                productos = productoService.listarProductosByCiudad(ciudadId);
            }else{
                productos = productoService.listar();
            }

            //Se buscan las reservas por cada uno de los productos
            for (ProductoDTO productoDTO:productos) {
                //Se consulta si existen reservas
                reservasDTOS = reservaService.buscarReservabyProducto(productoDTO.getId());
                //Si no hay reservas
                if(reservasDTOS.isEmpty()){
                    //Se agrega al Set de productos disponibles
                    productosDisponibles.add(productoDTO);
                }else{
                    //Se consulta cada una de las reservas
                    for (ReservaDTO reservaDTO:reservasDTOS) {
                        //Se verifica si las reservas existentes están en el rango de fechaCheckin y fechaCheckout y se agrega a un array de booleanos
                        isRango.add(reservaService.comprobarDisponibilidadFechaNuevaReserva(reservaDTO, fechaCheckIn, fechaCheckOut));
                    }
                    //Si el arreglo isRango no contiene false, indica que no hay reservas en ese rango
                    if(!isRango.contains(false)){
                        //Se agrega el producto al listado de productos disponibles
                        productosDisponibles.add(productoDTO);
                    }
                }
            }
        }
        return productosDisponibles;
    }

    @GetMapping("/FechasReservasByProducto/{id}")
    public List<FechasReservas> getFechasReservasByProducto(@PathVariable("id") Long id) throws ResourceNotFoundException {
        logger.info("Listando Reservas de Productos");
        List<FechasReservas> rangoFechas = new ArrayList<>();
        if(id != 0 && productoService.buscar(id) != null) {
            Set<ReservaDTO> reservasDTOS = reservaService.buscarReservabyProducto(id);
            if(!reservasDTOS.isEmpty()){
                for (ReservaDTO reservaDTO:reservasDTOS) {
                    System.out.println("FechaCheckIn = "+reservaDTO.getFechaCheckIn());
                    System.out.println("FechaCheckOut = "+reservaDTO.getFechaCheckOut());
                    FechasReservas fechas = new FechasReservas();
                    fechas.setFechaCheckIn(reservaDTO.getFechaCheckIn());
                    fechas.setFechaCheckOut(reservaDTO.getFechaCheckOut());
                    System.out.println("fechas = "+fechas.toString());
                    rangoFechas.add(fechas);
                    System.out.println("rangoFechas = "+rangoFechas);
                }
            }
            return rangoFechas;
        }else{
            throw new ResourceNotFoundException("El id no pertenece a un producto");
        }
    }

    @PostMapping("/crearProductoCompleto")
    public ResponseEntity<ProductoDTO> createProductoCompleto(@RequestBody ProductoCompletoDTO productoCompletoDTO) throws ResourceNotFoundException{
        logger.info("Agregando Producto");
        ResponseEntity<ProductoDTO> productoCreado = null;
        //Se genera un nuevo objeto de ProductoDTO y se carga con los valores que ingresan
        ProductoDTO productoDTO1 = new ProductoDTO();
        productoDTO1 = productoCompletoDTO.getProductoDTO();

        //Se crea el producto
        ResponseEntity<ProductoDTO> response = createProducto(productoDTO1);

        if(response.getStatusCode().value() == 201){
            //Caracteristicas
            if(!productoCompletoDTO.getCaracteristicasDTO().isEmpty()){
                //Se obtienen las caracteristicas
                Set<CaracteristicaDTO> caracteristicas = productoCompletoDTO.getCaracteristicasDTO();
                //Si poseen caracteristicas
                if(!caracteristicas.isEmpty()){

                    //Se crea caracteristicaDTO para relacionar producto con caracteristicas
                    AddCaracteristicaDTO addCaracteristicaDTO = new AddCaracteristicaDTO();
                    addCaracteristicaDTO.setProductoId(response.getBody().getId());
                    Set<Caracteristica> caracteristicas1 = new HashSet<>();
                    Set<CaracteristicaDTO> caracteristicasDTO = productoCompletoDTO.getCaracteristicasDTO();
                    for (CaracteristicaDTO caracteristicaDTO:caracteristicasDTO) {
                        caracteristicas1.add(mapper.convertValue(caracteristicaDTO, Caracteristica.class));
                    }
                    addCaracteristicaDTO.setCaracteristicas(caracteristicas1);
                    //Se almacena
                    productoService.addCaracteristica(addCaracteristicaDTO);
                }
            }
            //Imagenes
            if(!productoCompletoDTO.getImagenesDTO().isEmpty()){
                //Se obtienen las imagenes
                Set<ImagenDTO> imagenes = productoCompletoDTO.getImagenesDTO();
                int cont = 0;
                //Se recorren cada una de ellas
                for (ImagenDTO imagen:imagenes) {
                    cont += 1;
                    //Se agrega el producto a la imagen
                    Producto productoImagen = imagen.getProducto();
                    productoImagen.setId(response.getBody().getId());
                    imagen.setProducto(productoImagen);
                    imagen.setTitulo("imagen "+response.getBody().getTitulo()+" "+String.valueOf(cont));
                    imagen.setDescripcion("imagen "+ String.valueOf(cont));

                    //Se guarda la imagen
                    imagenService.guardar(imagen);
                }
            }
            //Politicas
            if(!productoCompletoDTO.getPoliticasDTO().isEmpty()){
                //Se obtienen las politicas
                Set<PoliticaDTO> politicas = productoCompletoDTO.getPoliticasDTO();
                for (PoliticaDTO politica:politicas) {
                    Producto productoPolitica = politica.getProducto();
                    productoPolitica.setId(response.getBody().getId());
                    politica.setProducto(productoPolitica);

                    //Se guarda la política
                    politicaService.guardar(politica);
                }
            }

            Long idProducto = response.getBody().getId();
            if(idProducto != null){
                ProductoDTO productoDTOById = productoService.buscar(idProducto);
                if(productoDTOById != null){
                    productoCreado = ResponseEntity.ok(productoDTOById);
                }
            }
        }
        return productoCreado;
    }
}
