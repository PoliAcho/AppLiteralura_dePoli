package com.aluracursos.literalura.repo;

import com.aluracursos.literalura.mode.CIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IIdiomaRepo extends JpaRepository<CIdioma, String> {

  @Query("SELECT i FROM CIdioma i WHERE i.id = :id")
  CIdioma leeIdiomaUsado(String id);
}
