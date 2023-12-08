package ru.mts.homework.ArticlesComments;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

public class Article {
  private final ArticleId id;
  private final String title;
  private Set<String> tags;
  private List<Comment> comments;

  public Article(ArticleId artId, String artName){
    this.id = artId;
    this.title = artName;
    tags = new TreeSet<String>();
    comments = new ArrayList<Comment>();

  }


  public ArticleId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Set<String> getTags() {
    return tags;
  }

  public List<Comment> getComments() {
    return comments;
  }

  @Override
  public String toString() {
    return "{\n\"id\": " + id + ",\n\"title\": " + title + ",\n \"comments: \"" + comments.toString() + "}\n";
  }

  public boolean addNewComment(Comment comment){
    return comments.add(comment);
  }

  public boolean deleteComment(CommentId commentId){
    Comment c = new Comment(id, commentId, "");
    return comments.remove(c);
  }
}
