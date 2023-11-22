package ru.mts.homework.ArticlesComments;


public class ArticleCreateResponse {
  private final ArticleId id;
  private final String title;

  public ArticleCreateResponse(ArticleId artId, String artTitle){
    this.id = artId;
    this.title = artTitle;
  }

  public ArticleId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }
}
