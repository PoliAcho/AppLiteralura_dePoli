package com.aluracursos.literalura.ppal;

import com.aluracursos.literalura.mode.*;
import com.aluracursos.literalura.repo.IAutorRepo;
import com.aluracursos.literalura.repo.IIdiomaRepo;
import com.aluracursos.literalura.repo.ILibroRepo;
import com.aluracursos.literalura.serv.CConsAPI;
import com.aluracursos.literalura.serv.CConvRtds;
import com.aluracursos.literalura.serv.CLibroServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class CPpal {
  private static final String URL_BASE = "https://gutendex.com/books/";
  private CConsAPI cConsAPI = new CConsAPI();
  private CConvRtds cConvRtds = new CConvRtds();
  private Scanner leer = new Scanner(System.in);

  @Autowired
  private CLibroServ cLibroServ;
  @Autowired
  private IIdiomaRepo iIdiomaRepo;
  @Autowired
  private ILibroRepo iLibroRepo;
  @Autowired
  private IAutorRepo iAutorRepo;

  public void muestraMenu() {
    var opcion1 = -1;
    var menu = """
         \n
         ********** MENÚ DE OPCIONES **********
             1 - Libro/Autor a buscar 
                  (y registrar)
             2 - Libros registrados
             3 - Autores registrados
             4 - Autores vivos en el año...
             5 - Libros por idioma
             6 - Libros por autor (plus)
         """;
    while (opcion1 != 0) {
      System.out.print(menu);
      System.out.print("\n  Ingrese su opción (0=Salir): ");
      if (leer.hasNextInt()) {
        opcion1 = leer.nextInt();
        leer.nextLine();
        switch (opcion1) {
          case 0:
            System.out.println("\n  Gracias por usar la aplicación");
            break;
          case 1:
            buscarLibroYRegistrar();
            break;
          case 2:
            mostrarLibrosRegistrados();
            break;
          case 3:
            mostrarAutoresRegistrados();
            break;
          case 4:
            verAutoresVivosEnAnio();
            break;
          case 5:
            verLibrosPorIdioma();
            break;
          case 6:
            verLibrosPorAutor();
            break;
          default:
            System.out.println("Opción inválida.");
        }
      } else {
        System.out.println("Debe ingresar un número.");
        leer.nextLine();
      }
    }
  }

  // Busca libros por nombre/autor y los registra en la DB
  private void buscarLibroYRegistrar() {
    System.out.print("         Libro/Autor a buscar: ");
    var libroABuscar = leer.nextLine();
    var json = cConsAPI.obtDatos(URL_BASE + "?search=" + libroABuscar.replace(" ", "+"));
    var librosHallados = cConvRtds.obtDatos(json, RRtdos.class);
    List<RLibro> listaLibros = new ArrayList<>();
    int cantLibros = librosHallados.rtdos().size();
    System.out.println("\n   Se encontraron " + cantLibros + " libros con la palabra: " + libroABuscar
         + "\n   de los cuales listamos los 12 (o menos) con más descargas:"
         + "\n   --------------------------------------------------------");
    listaLibros = librosHallados.rtdos().stream()
         .sorted(Comparator.comparing(RLibro::nroDescargas).reversed())
         .limit(12)
         .collect(Collectors.toList());
    cantLibros = listaLibros.size();
    if (cantLibros > 0) {
      for (int i = 0; i < cantLibros; i++) {
        System.out.println(1 + i + "- " + listaLibros.get(i).titulo().toUpperCase()
             + "\n   Autor: " + listaLibros.get(i).autores().get(0).nombre()
             + " * Idioma: " + iIdiomaRepo.leeIdiomaUsado(listaLibros.get(i).idiomas().get(0)).getDetalle()
             + " * Descargas: " + listaLibros.get(i).nroDescargas());
      }
//      listaLibros.forEach(System.out::println);
      System.out.print("\n    Elija el libro a registrar (0=Ninguno): ");
      int opcion2 = 0;
      if (leer.hasNextInt()) {
        opcion2 = leer.nextInt();
        leer.nextLine();
//        break;
        if (opcion2 > 0 && opcion2 <= cantLibros) {
          RLibro rLibroSeleccionado = listaLibros.get(opcion2 - 1);
          System.out.println("______________________________________________________________________");
          System.out.println("Su elección: " + listaLibros.get(opcion2 - 1).titulo().toUpperCase()
               + "\n         de: " + rLibroSeleccionado.autores().get(0).nombre()
               + "\n     idioma: " + iIdiomaRepo.leeIdiomaUsado(rLibroSeleccionado.idiomas().get(0)).getDetalle()
               + "\n  descargas: " + rLibroSeleccionado.nroDescargas());

// Registrar el libro en la DB
          CAutor cAutor = new CAutor(rLibroSeleccionado.autores().get(0));
          CIdioma cIdioma = iIdiomaRepo.findById(rLibroSeleccionado.idiomas().get(0)).orElse(null);
          CLibro cLibro = new CLibro(rLibroSeleccionado, cIdioma);

          // Registrar el autor y el libro en la DB
          cLibroServ.registrarLibro(cLibro, cAutor);
        }
      } else {
        leer.nextLine();
        System.out.println("¡Opción inválida!");
        opcion2 = 0;
      }
    } else {
      System.out.println("No se encontró el libro/autor solicitado.\n");
    }
  }

  //    2 - Libros registrados
  private void mostrarLibrosRegistrados() {
    List<CLibro> librosRegistrados = iLibroRepo.findAll();
    if (librosRegistrados.isEmpty()) {
      System.out.println("No hay libros registrados.");
    } else {
      System.out.println("\n***** LISTA DE LIBROS REGISTRADOS *****");
      for (CLibro libro : librosRegistrados) {
        System.out.println("Título: " + libro.getTitulo());
        System.out.println(" Autor: " + libro.getcAutor().getNombre());
        System.out.println("Idioma: " + libro.getcIdioma().getDetalle());
        System.out.println("         - o -");
      }
    }
  }

  //    3 - Autores registrados
  private void mostrarAutoresRegistrados() {
    List<CAutor> autoresRegistrados = iAutorRepo.findAll();
    if (autoresRegistrados.isEmpty()) {
      System.out.println("No hay autores registrados.");
    }else {
      System.out.println("\n***** LISTA DE AUTORES REGISTRADOS *****");
      for (CAutor autor : autoresRegistrados) {
        System.out.println("  Nombre: " + autor.getNombre());
        System.out.println("          " + autor.getNacio() + " - " + autor.getMurio());
        System.out.println("          - o -");
      }
    }
  }

  //    4 - Autores vivos en el año...
  private void verAutoresVivosEnAnio() {
    System.out.print("               Ingrese el año: ");
    var anio = leer.nextInt();
    leer.nextLine();
    List<CAutor> filtroAutoresVivos = iAutorRepo.autoresVivosEnElAnio(anio);
    if (filtroAutoresVivos.isEmpty()) {
      System.out.println("No hay autores vivos en el año: " + anio);
    }else {
      System.out.println("\n***** LISTA DE AUTORES VIVOS EN EL AÑO " + anio + " *****");
      for (CAutor autor : filtroAutoresVivos) {
        System.out.println("  Nombre: " + autor.getNombre());
        System.out.println("          " + autor.getNacio() + " - " + autor.getMurio());
        System.out.println("          - o -");
      }
    }
  }

  //    5 - Libros por idioma
  private void verLibrosPorIdioma() {
    List<CIdioma> idiomasUsados = iLibroRepo.buscaIdiomasUsados();
    if (idiomasUsados.isEmpty()) {
      System.out.println("\nNo se encontró ningún idioma usado.");
    }else{
      System.out.println("\n***** LISTA DE IDIOMAS DE LOS LIBROS REGISTRADOS *****");
      for (int i = 0; i < idiomasUsados.size(); i++) {
        System.out.println("     " + (1 + i) + " - " + idiomasUsados.get(i).getDetalle().toUpperCase());
      }
      System.out.print("\nIngrese el idioma de los libros a listar (0=ninguno): ");
      var opcion3 = leer.nextInt();
      leer.nextLine();
      if (opcion3 == 0) {
        System.out.println("\nNo seleccionó ningún idioma.");
        return;
      } else if (opcion3 > 0 && opcion3 <= (idiomasUsados.size())) {
        List<CLibro> librosEnIdioma = iLibroRepo.librIdiomaSele(idiomasUsados.get(opcion3 - 1).getId());
        if (librosEnIdioma.isEmpty()) {
          System.out.println("\nNo se encontraron libros en el idioma seleccionado.");
        } else {
          System.out.println("\n***** LIBROS EN EL IDIOMA SELECCIONADO: "
               + idiomasUsados.get(opcion3 - 1).getDetalle().toUpperCase() + " *****");
          for (CLibro libro : librosEnIdioma) {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println(" Autor: " + libro.getcAutor().getNombre());
            System.out.println("        - o -");
          }
        }
      }
    }
  }

  //    6 - Libros por autor
  private void verLibrosPorAutor() {
    List<CAutor> autoresRegistrados = iAutorRepo.findAll();
    if (autoresRegistrados.isEmpty()) {
      System.out.println("No hay autores registrados.");
    }else {
      System.out.println("\n***** LISTA DE AUTORES REGISTRADOS *****");
      for (int i = 0; i < autoresRegistrados.size(); i++) {
        System.out.println("     " + (1 + i) + "- " + autoresRegistrados.get(i).getNombre());
      }
      System.out.print("\nIngrese el autor del cual verá sus libros (0=ninguno): ");
      var opcion4 = leer.nextInt();
      leer.nextLine();
      if (opcion4 == 0) {
        System.out.println("\nNo seleccionó ningún autor.");
        return;
      } else if (opcion4 > 0 && opcion4 <= (autoresRegistrados.size())) {
        List<CLibro> librosDelAutor = iLibroRepo.librDelAutorSele(autoresRegistrados.get(opcion4 - 1).getId());
        if (librosDelAutor.isEmpty()) {
          System.out.println("\nNo se encontró el autor seleccionado.");
        } else {
          List<CIdioma> idiomasUsados = iLibroRepo.buscaIdiomasUsados();
          System.out.println("\n***** LIBROS DEL AUTOR: "
               + autoresRegistrados.get(opcion4 - 1).getNombre().toUpperCase()
               + " (" + autoresRegistrados.get(opcion4 - 1).getNacio()
               + "-" + autoresRegistrados.get(opcion4 - 1).getMurio() + ") *****");
          for (CLibro libro : librosDelAutor) {
            System.out.println("            Título: " + libro.getTitulo().toUpperCase());
            System.out.println("            Idioma: " + libro.getcIdioma().getDetalle().toUpperCase());
            System.out.println("                      - o -");
          }
        }
      }

    }
  }
}
