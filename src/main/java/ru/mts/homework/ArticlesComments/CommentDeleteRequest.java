package ru.mts.homework.ArticlesComments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDeleteRequest {
  private final long articleId;

  private final long commentId;

  @JsonCreator
  public CommentDeleteRequest(
          @JsonProperty("artid") long articleId,
          @JsonProperty("commentid") long commentId){
    this.articleId = articleId;
    this.commentId = commentId;
  }

  public long getArticleId() {
    return articleId;
  }

  public long getCommentId() {
    return commentId;
  }
}
