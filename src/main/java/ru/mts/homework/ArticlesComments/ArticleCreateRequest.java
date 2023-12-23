package ru.mts.homework.ArticlesComments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



public class ArticleCreateRequest {

  private final String name;

  @JsonCreator
  public ArticleCreateRequest(

          @JsonProperty("name") String name){
    this.name = name;

  }


  public String getName() {
    return name;
  }


}
