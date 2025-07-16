package com.academico.titulos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class TituloCreationDto {
    private String firstName;

    private String lastName;

    private String identification;

    private String career;

}
