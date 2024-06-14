package com.aluracursos.literalura.mode;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "idiomas")
public class CIdioma {
  @Id
  private String id;
  private String detalle;
  @OneToMany(mappedBy = "cIdioma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<CLibro> cLibros;

  public CIdioma() {}

  public CIdioma(String id, String detalle) {
    this.id = id;
    this.detalle = detalle;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDetalle() {
    return detalle;
  }

  public void setDetalle(String detalle) {
    this.detalle = detalle;
  }

  public List<CLibro> getcLibros() {
    return cLibros;
  }

  public void setcLibros(List<CLibro> cLibros) {
    this.cLibros = cLibros;
  }

  @Override
  public String toString() {
    return "CIdioma{" +
         "id='" + id + '\'' +
         ", detalle='" + detalle + '\'' +
         '}';
  }
}
