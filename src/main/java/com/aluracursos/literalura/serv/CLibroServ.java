package com.aluracursos.literalura.serv;

import com.aluracursos.literalura.mode.CAutor;
import com.aluracursos.literalura.mode.CIdioma;
import com.aluracursos.literalura.mode.CLibro;
import com.aluracursos.literalura.repo.IAutorRepo;
import com.aluracursos.literalura.repo.IIdiomaRepo;
import com.aluracursos.literalura.repo.ILibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CLibroServ {

  @Autowired
  private ILibroRepo iLibroRepo;

  @Autowired
  private IAutorRepo iAutorRepo;

  @Autowired
  private IIdiomaRepo iIdiomaRepo;

  public void registrarLibro(CLibro cLibro, CAutor cAutor) {
    // Verificar si el libro ya está registrado
    CLibro libroExistente = iLibroRepo.findByTitulo(cLibro.getTitulo());
    if (libroExistente != null) {
      System.out.println("\nEl libro ya está registrado.");
      return;
    }
    // Registrar el autor si no existe
    CAutor autorExistente = iAutorRepo.findByNombre(cAutor.getNombre());
    if (autorExistente == null) {
      autorExistente = iAutorRepo.save(cAutor);
    }
    // Registrar el idioma si no existe (o manejarlo según tu lógica)
    CIdioma idiomaExistente = iIdiomaRepo.findById(cLibro.getcIdioma().getId()).orElse(null);
    if (idiomaExistente == null) {
      System.out.println("\nEl idioma no existe.");
      return;
    }
    // Asociar el autor al libro y registrar
    cLibro.setcAutor(autorExistente);
    cLibro.setcIdioma(idiomaExistente);
    // Registrar el libro
    iLibroRepo.save(cLibro);
    System.out.println("\nLibro registrado exitosamente.");
  }

  public void listaLibrosRegistrados() {

  }
}