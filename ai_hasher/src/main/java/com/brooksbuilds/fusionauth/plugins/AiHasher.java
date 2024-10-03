package com.brooksbuilds.fusionauth.plugins;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import com.fasterxml.jackson.core.type.TypeReference;
import io.fusionauth.plugin.spi.security.PasswordEncryptor;

public class AiHasher implements PasswordEncryptor {
  public AiHasher() {
  }

  @Override
  public int defaultFactor() {
    return 10_000;
  }

  @Override
  public String encrypt(String password, String salt, int factor) {
    HttpClient client = HttpClient.newBuilder()
      .connectTimeout(Duration.ofSeconds(500))
      .build();

    BodyPublisher body = BodyPublishers.ofString(new String("{\"model\": \"hasher\", \"prompt\":\"" + password + "\", \"stream\": false}"));

    HttpRequest request = HttpRequest.newBuilder(URI.create(new String("http://host.docker.internal:11434/api/generate")))
    .POST(body)
    .header(new String("Content-Type"), new String("application/json"))
    .build();

  @SuppressWarnings("rawtypes")
	UncheckedObjectMapper mapper = new UncheckedObjectMapper();

    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

      @SuppressWarnings("unchecked")
    	Map<String, String> responseBody = mapper.readValue(response.body());

      String hashedPassword = responseBody.get(new String("response"));
      
      return hashedPassword;

    } catch (IOException ioException) {
    } catch (InterruptedException interruptedException) {}

   
    return new String("I am totally a hash and not a fake thing");
  }
}

class UncheckedObjectMapper<T> extends com.fasterxml.jackson.databind.ObjectMapper {
    /** Parses the given JSON string into a Map. */
    Map<String,T> readValue(String content) {
      try {
        return this.readValue(content, new TypeReference<Map<String, T>>(){});
      } catch (IOException ioe) {
        throw new CompletionException(ioe);
      }
  }
}
