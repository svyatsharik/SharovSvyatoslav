package ru.mts.homework.ArticlesComments;

public class ArticleUpdateResponse {
  private final ArticleId articleId;
  private final String articleName;
  public ArticleUpdateResponse (ArticleId articleId, String articleName){
    this.articleId = articleId;
    this.articleName = articleName;
  }


  public ArticleId getArticleId() {
    return articleId;
  }

  public String getArticleName() {
    return articleName;
  }
}
