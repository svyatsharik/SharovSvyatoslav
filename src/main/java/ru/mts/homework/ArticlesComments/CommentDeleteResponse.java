package ru.mts.homework.ArticlesComments;

public class CommentDeleteResponse {

  private final Article article;
  public CommentDeleteResponse (Article article){
    this.article = article;
  }

  public Article getArticle() {
    return article;
  }
}
