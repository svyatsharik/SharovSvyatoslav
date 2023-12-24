package ru.mts.homework.ArticlesComments;

import java.util.Collection;

public interface ArticleRepository {

  public Article addNewArticle(String artName);

  public Collection<Article> getArticles();

  public Article getArticleById(ArticleId artId);

  public Article deleteArticle(ArticleId artId) ;

  public Article updateArticle(ArticleId articleId, String articleName) ;

  public CommentId addCommentToArticle(ArticleId articleId, String comment) ;

  public long getNumberOfComments(ArticleId artId);

  public void deleteCommentFromArticle(ArticleId articleId, CommentId commentId);
}