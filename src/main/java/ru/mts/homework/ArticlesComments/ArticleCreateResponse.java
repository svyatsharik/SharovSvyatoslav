package ru.mts.homework.ArticlesComments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleCreateResponse {

  private final ArticleId id;
  private final String name;



  public ArticleCreateResponse(ArticleId artId, String artTitle){
    this.id = artId;
    this.name = artTitle;
  }

  @JsonCreator
  public ArticleCreateResponse(
          @JsonProperty("id") long artId,
          @JsonProperty("name") String name){
    this.id = new ArticleId(artId);
    this.name = name;
  }

  public long getId() {
    return id.getId();
  }

  public String getName() {
    return name;
  }
}
