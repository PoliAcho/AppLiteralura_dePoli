package com.aluracursos.literalura.mode;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class CLibro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String titulo;
  @ManyToOne
  @JoinColumn(name = "idioma_id")
  private CIdioma cIdioma;
  @ManyToOne
  @JoinColumn(name = "autor_id")
  private CAutor cAutor;

  public CLibro(){}

  public CLibro(RLibro rLibro,CIdioma cIdioma) {
    this.titulo = rLibro.titulo();
    this.cIdioma = cIdioma;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public CIdioma getcIdioma() {
    return cIdioma;
  }

  public void setcIdioma(CIdioma cIdioma) {
    this.cIdioma = cIdioma;
  }

  public CAutor getcAutor() {
    return cAutor;
  }

  public void setcAutor(CAutor cAutor) {
    this.cAutor = cAutor;
  }

  @Override
  public String toString() {
    return "CLibro{" +
         "id=" + id +
         ", titulo='" + titulo + '\'' +
         ", cIdioma=" + cIdioma +
         ", cAutor=" + cAutor +
         '}';
  }
}
