package ru.mts.homework.ArticlesComments;

public class Comment {
  private final ArticleId articleId;
  private final CommentId commentId;
  private final String commentMessage;

  public Comment(ArticleId articleId, CommentId commentId, String commentMessage){
    this.articleId = articleId;
    this.commentId = commentId;
    this.commentMessage = commentMessage;
  }


  public ArticleId getArticleId() {
    return articleId;
  }

  public CommentId getCommentId() {
    return commentId;
  }

  public String getCommentMessage() {
    return commentMessage;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) { return false; }
    Comment c = (Comment)obj;
    return commentId.equals(c.getCommentId());
  }
}
