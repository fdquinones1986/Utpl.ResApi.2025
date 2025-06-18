package com.academico.titulos.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academico.titulos.entidades.Titulo;

import jakarta.transaction.Transactional;


@Repository
public interface TituloRepository extends JpaRepository<Titulo,Integer>{      
      public List<Titulo> findByIdentification(String identification);
      @Transactional
      public long deleteByIdentification(String identification); 
      public List<Titulo> findAll();
}