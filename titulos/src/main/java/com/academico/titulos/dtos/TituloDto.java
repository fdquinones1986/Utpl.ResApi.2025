package com.academico.titulos.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class TituloDto {
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String carrera;

    // Constructor
    public TituloDto(String nombreEstudiante, String apellidoEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
    }

}
