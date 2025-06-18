package com.academico.titulos.controladores;

import org.springframework.web.bind.annotation.RestController;
import com.academico.titulos.dtos.TituloDto;
import com.academico.titulos.entidades.Titulo;
import com.academico.titulos.servicios.TituloService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/titulos")
public class TituloRestController {
    // Lista en memoria para almacenar los títulos
    private final List<TituloDto> titulos = new ArrayList<>();

    @Autowired
	private TituloService tituloService;

    // Constructor para inicializar datos de ejemplo
    public TituloRestController() {
        titulos.add(new TituloDto("Juan Perez", "Ingeniería de Sistemas"));
        titulos.add(new TituloDto("Maria Lopez", "Medicina"));
        titulos.add(new TituloDto("Carlos Gomez", "Derecho"));
    }


    // Obtener todos los títulos
    @GetMapping("/")
    @Operation(summary = "Obtener todos los titulos")
    public List<TituloDto> getAllTitulos() {
        var titulos = tituloService.ObtenerTodos();
        //obtener los titulos y convertirlos a TituloDto
        List<TituloDto> titulosDto = new ArrayList<>();
        for (Titulo titulo : titulos) {
            TituloDto tituloDto = new TituloDto(titulo.getFirstName(), titulo.getLastName());
            titulosDto.add(tituloDto);
        }
        System.out.println("Obteniendo todos los títulos");
        // Retornar la lista de títulos
        if (titulosDto.isEmpty()) {
            System.out.println("No se encontraron títulos");
            return new ArrayList<>(); // Retornar una lista vacía si no hay títulos
        }
        System.out.println("Se encontraron " + titulosDto.size() + " títulos");
        return titulosDto;
    }

    // Obtener un título por identificacion
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
    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Void> deleteTitulo(@PathVariable String identificacion) {
        tituloService.EliminarPorIdentificacion(identificacion);
        return new ResponseEntity<>(HttpStatus.OK);
    }  
}
