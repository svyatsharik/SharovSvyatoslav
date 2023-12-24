package ru.mts.homework.ArticlesComments;

import java.util.*;

public class ArticleRepositoryMem implements ArticleRepository {
  private final Map<ArticleId, Article> articles;
  private long artId;
  private long commentId;
  public ArticleRepositoryMem(){
    articles = new HashMap<ArticleId, Article>();
    artId = 0;
    commentId = 0;
  }

  public Article addNewArticle(String artName){
    ArticleId articleId = new ArticleId(artId);
    Article article = new Article(articleId, artName);
    articles.put(articleId, article);
    artId++;
    return article;
  }

  public Collection<Article> getArticles(){
    return articles.values();
  }


  public Article getArticleById(ArticleId artId){
    return (Article)articles.get(artId);
  }

  public Article deleteArticle(ArticleId artId){

    return articles.remove(artId);
  }

  public Article updateArticle(ArticleId articleId, String articleName){
    Article article = new Article(articleId, articleName);
    articles.put(articleId, article);
    return article;

  }

  public CommentId addCommentToArticle(ArticleId articleId, String comment){

    Article article = (Article)articles.get(articleId);
    CommentId id = new CommentId(commentId);
    if (article != null) {
      boolean res = article.addNewComment(new Comment(articleId, id, comment));
      commentId++;
      return id;
    } else {
      throw new RuntimeException("Article not found by articleId: " + article.getId().getId());
    }
  }

  public long getNumberOfComments(ArticleId artId){
    Article article = (Article)articles.get(artId);
    return article.getComments().size();
  }

  public void deleteCommentFromArticle(ArticleId articleId, CommentId commentId){
    Article article = (Article)articles.get(articleId);
    article.deleteComment(commentId);
  }

}
