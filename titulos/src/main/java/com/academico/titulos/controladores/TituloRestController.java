package com.academico.titulos.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.academico.titulos.dtos.TituloCreationDto;
import com.academico.titulos.dtos.TituloDto;
import com.academico.titulos.dtos.TituloDtoV2;
import com.academico.titulos.entidades.Titulo;
import com.academico.titulos.servicios.EmailService;
import com.academico.titulos.servicios.TituloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/titulos")
@Tag(name = "ApiTitulo", description = "encargador de manejar los titulos")
public class TituloRestController {

    @Autowired
    private TituloService tituloService;

    @Autowired
    private EmailService emailService;

    // Obtener todos los títulos con todos los campos
    @GetMapping("/v1")
    @Operation(summary = "Obtener todos los titulos")
    @Tag(name = "Titulo", description = "encargador de manejar los titulos")
    public List<TituloDto> getAllTitulosV1() {
        var titulos = tituloService.ObtenerTodos();
        // obtener los titulos y convertirlos a TituloDto
        List<TituloDto> titulosDto = new ArrayList<>();
        for (Titulo titulo : titulos) {
            TituloDto tituloDto = new TituloDto(titulo.getFirstName(), titulo.getLastName());
            titulosDto.add(tituloDto);
        }
        // Retornar la lista de títulos
        if (titulosDto.isEmpty()) {
            return new ArrayList<>(); // Retornar una lista vacía si no hay títulos
        }
        System.out.println("Se encontraron " + titulosDto.size() + " títulos");

        return titulosDto;
    }

    // Obtener todos los títulos con todos los campos
    @GetMapping("/v2")
    @Operation(summary = "Obtener todos los titulos")
    @Tag(name = "Titulo", description = "encargador de manejar los titulos")
    public List<TituloDtoV2> getAllTitulosV2() {
        var titulos = tituloService.ObtenerTodos();
        // obtener los titulos y convertirlos a TituloDto
        List<TituloDtoV2> titulosDto = new ArrayList<>();
        for (Titulo titulo : titulos) {
            TituloDtoV2 tituloDto = new TituloDtoV2(titulo.getFirstName());
            titulosDto.add(tituloDto);
        }
        // Retornar la lista de títulos
        if (titulosDto.isEmpty()) {
            return new ArrayList<>(); // Retornar una lista vacía si no hay títulos
        }
        System.out.println("Se encontraron " + titulosDto.size() + " títulos");

        return titulosDto;
    }

    // Obtener un título por identificacion
    @Operation(summary = "Busqueda un titulo por identificacion")
    @GetMapping("/{identificacion}")
    public Titulo getTitulo(@PathVariable String identificacion) {
        var titulos = tituloService.BuscarPorIdentificacion(identificacion);
        if (titulos.isEmpty()) {
            return null; // O lanzar una excepción si no se encuentra
        }
        System.out.println("Obteniendo título para la identificación: " + identificacion);
        // Retornar el primer título encontrado
        return titulos.get(0);
    }

    // Metodo para eliminar un titulo por identificacion
    @Operation(summary = "Eliminar un titulo por identificacion")
    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Void> deleteTitulo(@PathVariable String identificacion) {
        tituloService.EliminarPorIdentificacion(identificacion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Guardar un título
    @Operation(summary = "Guardar un titulo v1")
    @PostMapping("/v1")
    public ResponseEntity<Titulo> guardar(TituloCreationDto tituloDto) {
        var tituloE = new Titulo();
        tituloE.setFirstName(tituloDto.getFirstName());
        tituloE.setLastName(tituloDto.getLastName());
        tituloE.setIdentification(tituloDto.getIdentification());

        var titulo = tituloService.Guardar(tituloE);
        System.out.println("Título guardado: " + tituloDto.getFirstName() + " " + tituloDto.getLastName());

        /*
         * emailService.enviarCorreo(
         * "fdquinones@utpl.edu.ec", "Notificacion de prueba",
         * "Este es un mensaje de prueba desde el servicio de titulos");
         */

        try {
            Context context = new Context();
            context.setVariable("titulo", tituloDto);

            emailService.sendHtmlMessage("fdquinones@utpl.edu.ec", "Notificacion de prueba", "EmailTemplate", context);
        } catch (MessagingException e) {
            // Manejar la excepción, por ejemplo, imprimir el error o registrar
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(titulo);
    }

    // Guardar un título
    @Operation(summary = "Guardar un titulo v2")
    @PostMapping("/v2")
    public ResponseEntity<Titulo> guardarV2(TituloCreationDto tituloDto) {
        var tituloE = new Titulo();
        tituloE.setFirstName(tituloDto.getFirstName());
        tituloE.setLastName(tituloDto.getLastName());
        tituloE.setIdentification(tituloDto.getIdentification());

        var titulo = tituloService.Guardar(tituloE);
        System.out.println("Título guardado: " + tituloDto.getFirstName() + " " + tituloDto.getLastName());

        /*
         * emailService.enviarCorreo(
         * "fdquinones@utpl.edu.ec", "Notificacion de prueba",
         * "Este es un mensaje de prueba desde el servicio de titulos");
         */

        try {
            Context context = new Context();
            context.setVariable("titulo", tituloDto);

            emailService.sendHtmlMessage("fdquinones@utpl.edu.ec", "Notificacion de prueba", "EmailTemplate", context);
        } catch (MessagingException e) {
            // Manejar la excepción, por ejemplo, imprimir el error o registrar
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(titulo);
    }
}
