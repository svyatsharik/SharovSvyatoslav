package ru.mts.homework.ArticleComments;

import ru.mts.homework.ArticlesComments.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArticleTest {
    private Article article;

    @BeforeEach
    void beforeEach(){
        this.article = new Article(new ArticleId(0), "New article id: 0");
        for (int i = 0; i < 10; i++){
            boolean res = article.addNewComment(
                    new Comment(
                            article.getId(),
                            new CommentId(i),
                            "Comment #" + i
                    )
            );
        }
    }

    @AfterEach
    void afterEach(){

    }

    @Test
    public void shouldDeleteComment(){
        assertEquals(10, article.getComments().size());

        article.deleteComment(new CommentId(5));
        assertEquals(9, article.getComments().size());

        article.deleteComment(new CommentId(4));
        assertEquals(8, article.getComments().size());

        article.deleteComment(new CommentId(5));
        assertEquals(8, article.getComments().size());
    }

    @Test
    public void shouldAddComment(){
// Create and add new comment to existing article with specific Id and message to the end of list
        boolean res = article.addNewComment(
            new Comment(
                    article.getId(),
                    new CommentId(11),
                    "Comment manually created #11"
            )
        );
//Tests, if size became bigger by one comment
        int commentsListSize = article.getComments().size();
        assertEquals(11, commentsListSize);

//Gets the latest comment and tests that gotten comment has specified id and name
        Comment comment = article.getComments().get(commentsListSize - 1);

        assertEquals(11, comment.getCommentId().getCommentId());
        assertEquals("Comment manually created #11", comment.getCommentMessage());
    }

    @Test
    public void shouldGetComments(){
        assertEquals(10, article.getComments().size());
    }
}
