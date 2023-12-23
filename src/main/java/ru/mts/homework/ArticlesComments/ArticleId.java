package ru.mts.homework.ArticlesComments;


import java.util.Objects;

public class ArticleId {
  private long id;
  public ArticleId(long artId){
    id = artId;
  }


  public long getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    ArticleId articleId = (ArticleId)obj;
    return articleId.getId() == this.id;

  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
