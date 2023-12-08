package ru.mts.homework.ArticleComments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mts.homework.ArticlesComments.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentTest {
  private Comment comment;

  @BeforeEach
  void beforeEach(){
    this.comment = new Comment(
            new ArticleId(0),
            new CommentId(0),
            "Test comment message"
    );
  }

  @AfterEach
  void afterEach(){

  }

  @Test
  public void shouldGetArticleId() {
    assertEquals(0, comment.getArticleId().getId());

  }

  @Test
  public void shouldGetCommentId() {
    assertEquals(0, comment.getCommentId().getCommentId());
  }

  @Test
  public void shouldGetCommentMessage() {
    assertEquals("Test comment message", comment.getCommentMessage());
  }

  @Test
  public void shouldEquals() {
    Comment c = new Comment(
            new ArticleId(0),
            new CommentId(0),
            "Test comment message"
    );
    assertTrue(c.equals(comment));
    assertTrue(comment.equals(c));
  }
}
