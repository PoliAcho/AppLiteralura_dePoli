package com.aluracursos.literalura.serv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CConvRtds implements IConvRtds {
  private ObjectMapper mapper = new ObjectMapper();
  @Override
  public <T> T obtDatos(String json, Class<T> clase) {
    try {
      return mapper.readValue(json,clase);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
  }}}
