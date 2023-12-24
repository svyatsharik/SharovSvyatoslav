package ru.mts.homework.ArticleComments;

import ru.mts.homework.ArticlesComments.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleRepositoryMemTest {
    private ArticleRepositoryMem repository;

    @BeforeEach
    void beforeEach(){
        this.repository = new ArticleRepositoryMem();
    }

    @AfterEach
    void afterEach(){

    }

    @Test
    public void shouldAddNewArticle(){
        Article article = repository.addNewArticle("New article");
        assertEquals("New article", article.getTitle());
    }

    @Test
    public void shouldGetArticles(){
        for (int i = 0; i<10; i++){
            repository.addNewArticle("New article #" + i);
        }

        Collection<Article> articles = repository.getArticles();
        assertEquals(10, articles.size());
    }


    @Test
    public void shouldGetArticleById(){
        for (int i = 0; i<10; i++){
            repository.addNewArticle("New article #" + i);
        }
        Article article = repository.getArticleById(new ArticleId(5));
        assertEquals(5, article.getId().getId());
        assertEquals("New article #5", article.getTitle());
    }
    @Test
    public void shouldDeleteArticle(){
        for (int i = 0; i<10; i++){
            repository.addNewArticle("New article #" + i);
        }
        assertEquals(10, repository.getArticles().size());
        repository.deleteArticle(new ArticleId(5));
        assertEquals(9, repository.getArticles().size());
        repository.deleteArticle(new ArticleId(4));
        assertEquals(8, repository.getArticles().size());
        repository.deleteArticle(new ArticleId(5));
        assertEquals(8, repository.getArticles().size());

    }

    @Test
    public void shouldUpdateArticle(){
        Article article = repository.addNewArticle("New article");
        assertEquals("New article", article.getTitle());
        Article updatedArticle = repository.updateArticle(article.getId(), "Updated article name");
        assertEquals("Updated article name", updatedArticle.getTitle());
    }

    @Test
    public void shouldAddCommentToArticle(){
        Article article = new Article(new ArticleId(0), "Article #1");
        article = repository.addNewArticle(article.getTitle());
        repository.addCommentToArticle(article.getId(), "New comment #1");
        assertEquals(1, article.getComments().size());

        for (int i = 0; i < 10; i ++) {
            repository.addCommentToArticle(article.getId(), "New comment #" + i);
        }

        assertEquals(11, article.getComments().size());
    }

    @Test
    public void shouldGetNumberOfComments(){
        Article article = repository.addNewArticle("New article");
        for (int i = 0; i < 10; i++){
            repository.addCommentToArticle(article.getId(), "New comment#" + i);
        }
        assertEquals(10, article.getComments().size());
    }

    @Test
    public void shouldDeleteCommentFromArticle(){
        Article article = repository.addNewArticle("New article");
        CommentId commentId = null;
        for (int i = 0; i < 10; i++){
            commentId = repository.addCommentToArticle(article.getId(), "New comment#" + i);
        }

        repository.deleteCommentFromArticle(article.getId(), commentId);
        assertEquals(9, article.getComments().size());

        repository.deleteCommentFromArticle(article.getId(), commentId);
        assertEquals(9, article.getComments().size());

    }

}
