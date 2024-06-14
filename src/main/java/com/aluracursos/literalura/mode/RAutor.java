package com.aluracursos.literalura.mode;

import com.fasterxml.jackson.annotation.JsonAlias;

public record RAutor(
     @JsonAlias("name")       String nombre,
     @JsonAlias("birth_year") Integer nacio,
     @JsonAlias("death_year") Integer murio) {
}
