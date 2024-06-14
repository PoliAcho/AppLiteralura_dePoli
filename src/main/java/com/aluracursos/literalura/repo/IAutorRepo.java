package com.aluracursos.literalura.repo;

import com.aluracursos.literalura.mode.CAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutorRepo extends JpaRepository<CAutor, Long> {
  //    3 - Autores registrados
  CAutor findByNombre(String nombre);

  //    4 - Autores vivos en el año...
  @Query(value = "SELECT * FROM autor a WHERE a.nacio <= :anio AND a.murio >= :anio", nativeQuery = true)
  List<CAutor> autoresVivosEnElAnio(int anio);
}
