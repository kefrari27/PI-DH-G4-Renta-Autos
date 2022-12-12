package com.dh.backend_G4.controller;

import com.dh.backend_G4.exceptions.ResourceNotFoundException;
import com.dh.backend_G4.model.Producto;
import com.dh.backend_G4.model.modelDTO.ProductoDTO;
import com.dh.backend_G4.model.modelDTO.ReservaDTO;
import com.dh.backend_G4.model.modelDTO.UsuarioDTO;
import com.dh.backend_G4.service.interfaceService.IMailService;
import com.dh.backend_G4.service.interfaceService.IProductoService;
import com.dh.backend_G4.service.interfaceService.IReservaService;
import com.dh.backend_G4.service.interfaceService.IUsuarioService;
import org.apache.log4j.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@EnableAsync
@RequestMapping("api/v1/reservas")
public class ReservaController {

    public static Logger logger = Logger.getLogger(ProductoController.class);
    private final IReservaService reservaService;
    private final IMailService mailService;
    private final IProductoService productoService;

    private final IUsuarioService usuarioService;

    public ReservaController(IReservaService reservaService, IMailService mailService, IProductoService productoService, IUsuarioService usuarioService) {
        this.reservaService = reservaService;
        this.mailService = mailService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Set<ReservaDTO>> getReservas() throws ResourceNotFoundException {
        logger.info("Listando Reservas");
        Set<ReservaDTO> reservas = reservaService.listar();
        if(!reservas.isEmpty()){
            return ResponseEntity.ok(reservas);
        }else{
            throw new ResourceNotFoundException("No hay Reservas para listar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Reserva por id " + id);
        ResponseEntity<ReservaDTO> response = null;
        if(id !=0){
            ReservaDTO reservaDTOById = reservaService.buscar(id);
            if(reservaDTOById != null){
                response = ResponseEntity.ok(reservaDTOById);
            }else{
                throw new ResourceNotFoundException("Reserva no encontrada");
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) throws ResourceNotFoundException, MessagingException, InterruptedException {
        List<Boolean> isRango = new ArrayList<>();
        //Se consulta si existen reservas
        Set<ReservaDTO> reservasDTOS = reservaService.buscarReservabyProducto(reservaDTO.getProducto().getId());
        //Si no hay reservas
        if(reservasDTOS.isEmpty()){
            //Se agrega Reserva
            logger.info("Agregando Reserva");
            return new ResponseEntity<>(reservaService.guardar(reservaDTO), HttpStatus.CREATED);
        }else{
            //Se consulta cada una de las reservas
            for (ReservaDTO reservaDTO1: reservasDTOS) {
                //Se verifica si las reservas existentes están en el rango de fechaCheckin y fechaCheckout y se agrega a un array de booleanos
                isRango.add(reservaService.comprobarDisponibilidadFechaNuevaReserva(reservaDTO1, reservaDTO.getFechaCheckIn(), reservaDTO.getFechaCheckOut()));
            }
            //Si el arreglo isRango no contiene false, indica que no hay reservas en ese rango
            if(!isRango.contains(false)){
                //Se agrega Reserva
                logger.info("Agregando Reserva");
                ReservaDTO reserva = reservaService.guardar(reservaDTO);
                ReservaDTO resultadoReserva = reservaService.obtenerReservaEspecifica(reservaDTO);
                sendMailReserva(resultadoReserva);
                Thread.sleep(2000);
                return new ResponseEntity<>(resultadoReserva, HttpStatus.CREATED);
            }else{
                throw new ResourceNotFoundException("No es posible alamacenar la reserva, ya existe una reserva que se cruzan con las fechas indicadas");
            }
        }
    }

    @PutMapping
    public ResponseEntity<ReservaDTO> updateReserva(@RequestBody ReservaDTO reservaDTO) throws ResourceNotFoundException{
        ResponseEntity<ReservaDTO> response = null;
        if(reservaDTO.getId() != null && reservaService.buscar(reservaDTO.getId()) != null){
            logger.info("Actualizando Reserva con id "+reservaDTO.getId());
            response = ResponseEntity.ok(reservaService.actualizar(reservaDTO));
        }else{
            throw new ResourceNotFoundException("Reserva no encontrada para ser actualizada");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReserva(@PathVariable("id") Long id) throws ResourceNotFoundException{
        ResponseEntity<HttpStatus> response;
        if(id != 0 && reservaService.buscar(id) != null){
            logger.info("Eliminando Reserva con id "+id);
            reservaService.eliminar(id);
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            throw new ResourceNotFoundException("Reserva no encontrada para ser eliminado");
        }
        return response;
    }

    @GetMapping("/reservasByProductoId/{id}")
    public ResponseEntity<Set<ReservaDTO>> getReservasByProductoId(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Reservas por ProductoId " + id);
        ResponseEntity<Set<ReservaDTO>> response = null;
        if(id !=0){
            Set<ReservaDTO> reservasDTOByProductoId = reservaService.buscarReservabyProducto(id);
            if(!reservasDTOByProductoId.isEmpty()){
                response = ResponseEntity.ok(reservasDTOByProductoId);
            }else{
                throw new ResourceNotFoundException("Reservas no encontradas para producto id = "+id);
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

    @GetMapping("/reservasByUsuarioId/{id}")
    public ResponseEntity<Set<ReservaDTO>> getReservasByUsuarioId(@PathVariable("id") Long id) throws ResourceNotFoundException{
        logger.info("Buscando Reservas por UsuarioId " + id);
        ResponseEntity<Set<ReservaDTO>> response = null;
        if(id !=0){
            Set<ReservaDTO> reservasDTOByUsuarioId = reservaService.obtenerReservasPorUsuario(id);
            if(!reservasDTOByUsuarioId.isEmpty()){
                response = ResponseEntity.ok(reservasDTOByUsuarioId);
            }else{
                throw new ResourceNotFoundException("Reservas no encontradas para el usuario con id = "+id);
            }
        }else{
            throw new ResourceNotFoundException("id no válido");
        }

        return response;
    }

   @Async("threadPoolTaskExecutor")
    public void sendMailReserva(ReservaDTO reservaDTO) throws MessagingException {
        Long idReserva = reservaDTO.getId();
        //reservaDTO = reservaService.buscar(idReserva);
        ProductoDTO producto = productoService.buscar(reservaDTO.getProducto().getId());
        UsuarioDTO usuario = usuarioService.buscar(reservaDTO.getUsuario().getId());
        String name =usuario.getNombre();
        String to = usuario.getCorreo();
        String subject = "Reserva DigitalBooking4";
        String content = "Nos alegra que nos hayas elegido para darte el mejor servicio, esperamos que disfrutes como te lo mereces";
        String reserva = "Tenemos dispuesto nuestro "+producto.getTitulo()+" ubicado en la ciudad de "+producto.getCiudad().getNombre()+", con las siguientes fechas reservadas para ti: <br> Fecha Checkin = "+reservaDTO.getFechaCheckIn().toString()+", <br> Fecha Checkout = "+reservaDTO.getFechaCheckOut().toString();
        //String reserva = "Tenemos dispuesto nuestro "+reservaDTO.getProducto().getTitulo().toString()+" ubicado en la ciudad de "+reservaDTO.getProducto().getCiudad().getNombre().toString();

        mailService.sendEmailWithImage(name, to, subject, content, reserva);
    }

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }

}
