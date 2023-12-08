package ru.mts.homework.ArticlesComments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentCreateRequest {
  private final long articleId;

  private final String commentMessage;
  @JsonCreator
  public CommentCreateRequest(
          @JsonProperty("artid") long articleId,
          @JsonProperty("message") String commentMessage){
    this.articleId = articleId;
    this.commentMessage = commentMessage;

  }


  public long getArticleId() {
    return articleId;
  }


  public String getCommentMessage() {
    return commentMessage;
  }
}
