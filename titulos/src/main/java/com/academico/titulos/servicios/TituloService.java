package com.academico.titulos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academico.titulos.entidades.Titulo;
import com.academico.titulos.repositorios.TituloRepository;

@Service
public class TituloService implements ITituloService {

    @Autowired
    private TituloRepository tituloRepository;


    @Override
    public List<Titulo> BuscarPorIdentificacion(String identification) {
        return tituloRepository.findByIdentification(identification);
    }

    @Override
    public void EliminarPorIdentificacion(String identification) {
        tituloRepository.deleteByIdentification(identification);
    }


    
}
