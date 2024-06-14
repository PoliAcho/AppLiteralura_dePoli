package com.aluracursos.literalura.mode;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RRtdos(
     @JsonAlias("results") List<RLibro> rtdos) {
}
