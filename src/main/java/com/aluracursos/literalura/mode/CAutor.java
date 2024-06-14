package com.aluracursos.literalura.mode;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class CAutor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String nombre;
  private Integer nacio;
  private Integer murio;
  @OneToMany(mappedBy = "cAutor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<CLibro> cLibros;

  public CAutor(){}

  public CAutor(RAutor rAutor) {
    this.nombre = rAutor.nombre();
    this.nacio = rAutor.nacio();
    this.murio = rAutor.murio();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getNacio() {
    return nacio;
  }

  public void setNacio(Integer nacio) {
    this.nacio = nacio;
  }

  public Integer getMurio() {
    return murio;
  }

  public void setMurio(Integer murio) {
    this.murio = murio;
  }

  public List<CLibro> getcLibros() {
    return cLibros;
  }

  public void setcLibros(List<CLibro> cLibros) {
    this.cLibros = cLibros;
  }

  @Override
  public String toString() {
    return "CAutor{" +
         "id=" + id +
         ", nombre='" + nombre + '\'' +
         ", nacio=" + nacio +
         ", murio=" + murio +
         ", cLibros=" + cLibros +
         '}';
  }
}
