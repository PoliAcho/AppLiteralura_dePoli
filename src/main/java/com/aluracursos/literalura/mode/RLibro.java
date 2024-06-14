package com.aluracursos.literalura.mode;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RLibro(
     @JsonAlias("title")          String titulo,
     @JsonAlias("authors")        List<RAutor> autores,
     @JsonAlias("languages")      List<String> idiomas,
     @JsonAlias("download_count") Double nroDescargas) {
}
