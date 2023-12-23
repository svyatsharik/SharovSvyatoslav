package ru.mts.homework.ArticlesComments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentCreateResponse {
  private final CommentId commentId;

  public CommentCreateResponse (CommentId commentId){
    this.commentId = commentId;
  }

  @JsonCreator
  public CommentCreateResponse (
          @JsonProperty("commendid") long commentId){
    this.commentId = new CommentId(commentId);
  }

  public long getCommentId() {
    return commentId.getCommentId();
  }
}
