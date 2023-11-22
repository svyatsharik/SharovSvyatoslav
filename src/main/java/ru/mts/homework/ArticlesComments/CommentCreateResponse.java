package ru.mts.homework.ArticlesComments;


public class CommentCreateResponse {
  private final Article article;
  public CommentCreateResponse (Article article){
    this.article = article;
  }

  public Article getArticle() {
    return article;
  }
}
