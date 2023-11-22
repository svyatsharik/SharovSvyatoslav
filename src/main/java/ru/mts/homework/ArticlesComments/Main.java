package ru.mts.homework.ArticlesComments;


import spark.Request;
import spark.Response;
import spark.Spark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    ObjectMapper objMapper = new ObjectMapper();
    ArticlesRepository repos = new ArticlesRepository();

    Spark.post("/article/create", (Request req, Response rep) -> {
      ArticleCreateRequest artReq = objMapper.readValue(
              req.body(), ArticleCreateRequest.class);
      Article article = repos.addNewArticle(artReq.getName());

      rep.type("application/json");
      return objMapper.writeValueAsString(new ArticleCreateResponse(article.getId(), article.getTitle()));
    });

    Spark.get("/article/list", (Request req, Response rep) -> {
      rep.type("application/json");
      return objMapper.writeValueAsString(repos.getArticles());
    });

    Spark.get("/article/byid/:id", (Request req, Response rep) -> {
      long artId = Long.valueOf(req.params("id"));
      Article art = repos.getArticleById(new ArticleId(artId));
      rep.type("application/json");
      return objMapper.writeValueAsString(new ArticleCreateResponse(art.getId(), art.getTitle()));
    });

    Spark.delete("/article/delete/:id", (Request req, Response rep) -> {
      long artId = Long.valueOf(req.params("id"));
      Article art = repos.deleteArticle(new ArticleId(artId));
      LOG.info("удалена статья с ИД: " + art.getId());
      return objMapper.writeValueAsString(new ArticleCreateResponse(art.getId(), art.getTitle()));
    });

    Spark.put("/article/update", (Request req, Response rep) -> {
      ArticleUpdateRequest artUpdateReq = objMapper.readValue(
              req.body(), ArticleUpdateRequest.class);
      Article art = repos.updateArticle(
              new ArticleId (artUpdateReq.getArticleId()),
              artUpdateReq.getNewArticleName()
      );
      Article updatedArt = repos.getArticleById(art.getId());
      LOG.info("изменение статьи c номером: "
              + updatedArt.getId()
              + ", новое имя: " + updatedArt.getTitle());
      return objMapper.writeValueAsString(
              new ArticleUpdateResponse(art.getId(), art.getTitle()));
    });

    Spark.put("/article/addcomment", (Request req, Response rep) -> {
      CommentCreateRequest commentReq = objMapper.readValue(
              req.body(), CommentCreateRequest.class);

      ArticleId articleId = new ArticleId(commentReq.getArticleId());
      repos.addCommentToArticle(articleId, commentReq.getCommentMessage());

      LOG.info("добавление комментария в статью: "
              + repos.getArticleById(articleId)
              + ", комментарий: " + commentReq.getCommentMessage()
      );
      return objMapper.writeValueAsString(
              new CommentCreateResponse(repos.getArticleById(articleId)));
    });

    Spark.delete("/article/deletecomment", (Request req, Response rep) -> {
      CommentDeleteRequest commentDeleteReq = objMapper.readValue(
              req.body(), CommentDeleteRequest.class);
      ArticleId articleId = new ArticleId(commentDeleteReq.getArticleId());
      CommentId commentId = new CommentId(commentDeleteReq.getCommentId());
      LOG.info("удаление комментария из статьи: " +articleId);
      repos.deleteCommentFromArticle(articleId, commentId);
      return objMapper.writeValueAsString(
              new CommentCreateResponse(repos.getArticleById(articleId)));
    });
  }
}
