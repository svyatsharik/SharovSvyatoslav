package ru.mts.homework.ArticlesComments;

import java.util.Objects;

public class CommentId {
  private final long commentId;

  public CommentId(long commentId){
    this.commentId = commentId;
  }

  public long getCommentId() {
    return commentId;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    CommentId commId = (CommentId)obj;
    return commId.getCommentId() == this.commentId;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentId);
  }
}
