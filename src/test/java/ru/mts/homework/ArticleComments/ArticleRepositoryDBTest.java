package ru.mts.homework.ArticleComments;

import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.mts.homework.ArticlesComments.*;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
class ArticleRepositoryDBTest {

  @Container
  public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13");
  private ArticleRepositoryDB repository;
  private static Jdbi jdbi;

  @BeforeAll
  static void beforeAll(){

    String postgresJdbcUrl = POSTGRES.getJdbcUrl();
    Flyway flyway =
            Flyway.configure()
                    .outOfOrder(true)
                    .locations("classpath:db.migrations/")
                    .dataSource(postgresJdbcUrl, POSTGRES.getUsername(), POSTGRES.getPassword())
                    .load();
    flyway.migrate();


  }

  @BeforeEach

  void beforeEach(){
    this.repository = new ArticleRepositoryDB(jdbi);
    DBInitializer initializer = new DBInitializer(jdbi);
    initializer.initialize();
      /*
        DBInitializer initializer = new DBInitializer();
        initializer.initialize();
        this.repository = new ArticleRepositoryDB();

       */
  }

  @AfterEach
  void afterEach(){

  }

  @Test
  void shouldAddNewArticle() {
    Article article = repository.addNewArticle("New article");
    assertEquals("New article", article.getTitle());

  }

  @Test
  void shouldGetArticles() {
    for (int i = 0; i<10; i++){
      repository.addNewArticle("New article #" + i);
    }

    Collection<Article> articles = repository.getArticles();
    assertEquals(10, articles.size());
  }

  @Test
  void shouldGetArticleById() {

    Article article = repository.addNewArticle("New article for test");

    Article foundArticle = repository.getArticleById(article.getId());
    assertEquals(foundArticle.getId().getId(), article.getId().getId());
    assertEquals(foundArticle.getTitle(), article.getTitle());
  }

  @Test
  void shouldDeleteArticle() {

    Article article = repository.addNewArticle("New article for test");

    Article foundArticle = repository.getArticleById(article.getId());

    assertEquals(foundArticle.getId().getId(), article.getId().getId());
    assertEquals(foundArticle.getTitle(), article.getTitle());

    repository.deleteArticle(foundArticle.getId());
    foundArticle = repository.getArticleById(article.getId());
    assertNull(foundArticle);

  }

  @Test
  void shouldUpdateArticle() {
    Article article = repository.addNewArticle("New article");
    assertEquals("New article", article.getTitle());
    Article updatedArticle = repository.updateArticle(article.getId(), "Updated article name");
    assertEquals("Updated article name", updatedArticle.getTitle());
  }

  @Test
  void shouldAddCommentToArticle() {
    Article article = repository.addNewArticle("New article to test add comments func");
    for (int i = 0; i < 10; i ++) {
      repository.addCommentToArticle(article.getId(), "New comment #" + i);
    }
    long count = repository.getNumberOfComments(article.getId());
    assertEquals(10, count);
  }

  @Test
  void shouldGetNumberOfComments() {
    Article article = repository.addNewArticle("New article to test GetNumberOfComments func");
    for (int i = 0; i < 10; i++){
      repository.addCommentToArticle(article.getId(), "New comment#" + i);
    }
    assertEquals(10, repository.getNumberOfComments(article.getId()));
  }

  @Test
  void shouldDeleteCommentFromArticle() {
    Article article = repository.addNewArticle("New article to test DeleteCommentFromArticle func");
    CommentId commentId = repository.addCommentToArticle(article.getId(), "New comment #1");
    assertEquals(1, repository.getNumberOfComments(article.getId()));

    repository.deleteCommentFromArticle(article.getId(), commentId);
    assertEquals(0, repository.getNumberOfComments(article.getId()));
  }
}