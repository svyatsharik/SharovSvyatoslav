package ru.mts.homework.ArticleComments;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.mts.homework.ArticlesComments.ArticleCreateResponse;
import ru.mts.homework.ArticlesComments.CommentCreateResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ArticleServerTest {

  @Test
  public void shouldSuccessfullyManipulatingWithArticle() throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    HttpResponse<String> response = client.send(
            HttpRequest.newBuilder().POST(
                    HttpRequest.BodyPublishers.ofString(" { \"name\": \"article 2\"}")).uri(URI.create("http://localhost:4567/article/create")).build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );
    assertEquals(200, response.statusCode());
    ArticleCreateResponse articleCreateResponse = objectMapper.readValue(response.body(), ArticleCreateResponse.class);


    response = client.send(
            HttpRequest.newBuilder().PUT(
                    HttpRequest.BodyPublishers.ofString(" { \"artid\": \"" + articleCreateResponse.getId() + "\", \"message\": \"comment 1\"}")).uri(URI.create("http://localhost:4567/article/addcomment")).build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );

    CommentCreateResponse commentCreateResponse = objectMapper.readValue(response.body(), CommentCreateResponse.class);
    assertEquals(200, response.statusCode());


    response = client.send(
            HttpRequest.newBuilder().PUT(
                    HttpRequest.BodyPublishers.ofString(" { \"id\": \"" + articleCreateResponse.getId() + "\", \"name\": \"updated name\"}")).uri(URI.create("http://localhost:4567/article/update")).build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );
    assertEquals(200, response.statusCode());

    response = client.send(
            HttpRequest.newBuilder().PUT(
                    HttpRequest.BodyPublishers.ofString(
                            " { \"artid\": \"" + articleCreateResponse.getId() + "\", \"commentid\": \""+commentCreateResponse.getCommentId()+"\"}")).uri(URI.create("http://localhost:4567/article/deletecomment")).build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );
    assertEquals(200, response.statusCode());

    response = HttpClient.newHttpClient().send(
            HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:4567/article/byid/" + commentCreateResponse.getCommentId())).build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
    );
    assertEquals(200, response.statusCode());

  }
}
