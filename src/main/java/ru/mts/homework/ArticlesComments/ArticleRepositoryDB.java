package ru.mts.homework.ArticlesComments;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultBearing;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArticleRepositoryDB implements ArticleRepository{

  private final Jdbi jdbi;
  public ArticleRepositoryDB() {
    Config config = ConfigFactory.load("resources/application.properties");

    this.jdbi = Jdbi.create(
            config.getString("app.database.url"),
            config.getString("app.database.user"),
            config.getString("app.database.password")
    );
  }

  public ArticleRepositoryDB(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  @Override
  public Article addNewArticle(String artName) {

    Handle handle = jdbi.open();
    Update update = handle.createUpdate("INSERT into article(title) VALUES (:title)");

    ResultBearing res = update.bind("title", artName).
            executeAndReturnGeneratedKeys("id");
    Map<String, Object> mapRes = res.mapToMap().first();

    return new Article(
            new ArticleId((Long) mapRes.get("id")),
            artName
    );
  }

  @Override
  public Collection<Article> getArticles() {
    Handle handle = jdbi.open();

    handle.registerRowMapper(
            Article.class,
            (rs, ctx) -> new Article(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getBoolean("trending")
            )
    );

    Query query = handle.createQuery("SELECT id as id, title as title, trending as trending FROM article ORDER by id");
    List<Article> articles = query.mapTo(Article.class).list();
    return articles;
  }

  @Override
  public Article getArticleById(ArticleId artId) {
    Handle handle = jdbi.open();
    Optional<Article> optArt = handle.select("SELECT id as id, title as title, trending as trending FROM article WHERE id = ?", artId.getId())
            .map(
                    (rs, ctx) -> new Article(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getBoolean("trending"))
            ).findOne();
    if (!optArt.isEmpty()) {
      Article article = optArt.get();

      List<Comment> optComment = handle.select("SELECT id, artId, message FROM comment WHERE artid = ?", artId.getId())
              .map(
                      (rs, ctx) -> new Comment(
                              article.getId(),
                              new CommentId(rs.getLong("id")),
                              rs.getString("message"))
              ).list();
      article.addComments(optComment);
      return article;
    }
    else {
      return null;
    }
/*
        handle.
                registerRowMapper(
                Article.class,
                (rs, ctx) -> new Article(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getBoolean("trending")
                )
        )
                .select("SELECT id as id, title as title, trending as trending FROM article WHERE id = ?", artId)

        Query query = handle.select(
                "SELECT id as id, title as title, trending as trending FROM article WHERE id = ?", artId);
                Optional<Article> optional = query.mapTo(Article.class).findOne();

 */


  }

  @Override
  public Article deleteArticle(ArticleId artId) {

    return jdbi.inTransaction((Handle handle) -> {
      Article article = getArticleById(artId);
      handle = jdbi.open();
      handle.createUpdate(
                      "DELETE from comment WHERE artid=:artid"
              )
              .bind("artid", article.getId().getId())
              .execute();

      handle.createUpdate(
                      "DELETE FROM article WHERE id = :id"
              )
              .bind("id", article.getId().getId())
              .execute();
      return article;
    });

  }

  @Override
  public Article updateArticle(ArticleId articleId, String articleName) {

    Handle handle = jdbi.open();
    Update update = handle.createUpdate("UPDATE article SET title = :title WHERE id = :id");

    int count = update.bind("title", articleName)
            .bind("id", articleId.getId())
            .execute();
    return new Article(articleId, articleName);
  }

  @Override
  public CommentId addCommentToArticle(ArticleId articleId, String comment) {

    return jdbi.inTransaction((Handle handle) -> {
      //Handle handle = jdbi.open();
      Update update = handle.createUpdate("INSERT into comment (artid, message) VALUES (:artid, :message)");

      ResultBearing res = update
              .bind("artid", articleId.getId())
              .bind("message", comment)
              .executeAndReturnGeneratedKeys("id");
      Map<String, Object> mapRes = res.mapToMap().first();

      CommentId commentId = new CommentId((Long) mapRes.get("id"));
      long numberOfComments = getNumberOfComments(articleId);
      if (numberOfComments == 3){
        updateArticleTrending(articleId);
      }
      return commentId;
    });

  }

  private void updateArticleTrending(ArticleId articleId){
    Handle handle = jdbi.open();
    Update update = handle.createUpdate("UPDATE article SET trending = true WHERE id =:id");
    update.bind("id", articleId.getId()).execute();
  }

  @Override
  public long getNumberOfComments(ArticleId artId) {
    Handle handle = jdbi.open();

    Query query = handle.select(
            "SELECT count(*) FROM comment WHERE artid = ?", artId.getId());
    Long count = query.mapTo(Long.class).one();
    return count;
  }

  @Override
  public void deleteCommentFromArticle(ArticleId articleId, CommentId commentId) {
    Handle handle = jdbi.open();
    Update update = handle.createUpdate("DELETE FROM comment WHERE id = :id");

    int count = update.bind("id", commentId.getCommentId()).execute();
  }
}
