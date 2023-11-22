package ru.mts.homework.ArticlesComments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleUpdateRequest {
  private final long articleId;
  private final String newArticleName;
  @JsonCreator
  public ArticleUpdateRequest(
          @JsonProperty("id") long articleId,
          @JsonProperty("name") String newArticleName){
    this.articleId = articleId;
    this.newArticleName = newArticleName;
  }

  public String getNewArticleName() {
    return newArticleName;
  }

  public long getArticleId() {
    return articleId;
  }
}
