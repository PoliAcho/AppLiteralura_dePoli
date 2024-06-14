package com.aluracursos.literalura.repo;

import com.aluracursos.literalura.mode.CIdioma;
import com.aluracursos.literalura.mode.CLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILibroRepo extends JpaRepository<CLibro, Long> {
  //    2 - Libros registrados
  CLibro findByTitulo(String titulo);

  //    5 - Libros por idioma
  @Query("SELECT DISTINCT l.cIdioma FROM CLibro l")
  List<CIdioma> buscaIdiomasUsados();

  @Query("SELECT l FROM CLibro l WHERE l.cIdioma.id = :id")
  List<CLibro> librIdiomaSele(@Param("id") String id);

  //    6 - Libros por autor
  @Query("SELECT l FROM CLibro l WHERE l.cAutor.id = :id")
  List<CLibro> librDelAutorSele(Long id);
}
