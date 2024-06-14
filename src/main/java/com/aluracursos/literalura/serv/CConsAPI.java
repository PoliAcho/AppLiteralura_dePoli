package com.aluracursos.literalura.serv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CConsAPI {
  private final HttpClient client;
  public CConsAPI() {
    this.client = HttpClient.newHttpClient();
  }
  public String obtDatos(String url) {
    HttpRequest request = HttpRequest.newBuilder()
         .uri(URI.create(url))
         .build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String json = response.body();
      return json;
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      throw new RuntimeException("Error al obtener datos de la API", e);
    }}}