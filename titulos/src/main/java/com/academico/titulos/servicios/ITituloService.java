package com.academico.titulos.servicios;

import java.util.List;

import com.academico.titulos.entidades.Titulo;

public interface ITituloService {
    public List<Titulo> BuscarPorIdentificacion(String identification); 
    public void EliminarPorIdentificacion(String identification);
    public List<Titulo> ObtenerTodos(); 
}
