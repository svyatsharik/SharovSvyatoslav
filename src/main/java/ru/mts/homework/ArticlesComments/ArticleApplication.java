package ru.mts.homework.ArticlesComments;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleApplication {

  private static final Logger LOG = LoggerFactory.getLogger(WebServer.class);
  private final ObjectMapper objMapper;
  private final ArticleRepositoryMem repos;
  private final FreeMarkerEngine freeMarkerEngine;
  private Service service;
  public ArticleApplication(Service service){
    this.objMapper = new ObjectMapper();
    this.repos = new ArticleRepositoryMem();
    this.freeMarkerEngine = TemplateFactory.freeMarkerEngine();
    this.service = service;
  }

  public void start(){
    createArticle();
    getArticleList();
    getArticleById();
    removeArticleById();
    updateArticleById();
    addNewCommentToExistingArticle();
    removeCommentFromExistingArticle();
    displayArticles();
  }

  private void createArticle (){
    service.post("/article/create", (Request req, Response rep) -> {

      ArticleCreateRequest artReq = objMapper.readValue(
              req.body(), ArticleCreateRequest.class);
      Article article = repos.addNewArticle(artReq.getName());
      LOG.info("Создана новая статья: " + article);
      rep.type("application/json");
      return objMapper.writeValueAsString(new ArticleCreateResponse(article.getId(), article.getTitle()));

    });
  }

  private void getArticleList(){
    service.get("/article/list", (Request req, Response rep) -> {

      rep.type("application/json");
      return objMapper.writeValueAsString(repos.getArticles());
    });
  }

  private void getArticleById(){
    service.get("/article/byid/:id", (Request req, Response rep) -> {
      long artId = Long.valueOf(req.params("id"));
      Article art = repos.getArticleById(new ArticleId(artId));
      rep.type("application/json");
      return objMapper.writeValueAsString(new ArticleCreateResponse(art.getId(), art.getTitle()));
    });
  }

  private void removeArticleById(){
    service.put("/article/delete/:id", (Request req, Response rep) -> {
      long artId = Long.valueOf(req.params("id"));
      Article art = repos.deleteArticle(new ArticleId(artId));
      LOG.info("удалена статья с ИД: " + art.getId());
      return objMapper.writeValueAsString(new ArticleCreateResponse(art.getId(), art.getTitle()));
    });
  }

  private void updateArticleById(){
    service.put("/article/update", (Request req, Response rep) -> {
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
  }

  private void addNewCommentToExistingArticle(){
    service.put("/article/addcomment", (Request req, Response rep) -> {
      CommentCreateRequest commentReq = objMapper.readValue(
              req.body(), CommentCreateRequest.class);

      ArticleId articleId = new ArticleId(commentReq.getArticleId());
      CommentId commentId = repos.addCommentToArticle(articleId, commentReq.getCommentMessage());

      LOG.info("добавление комментария в статью: "
              + repos.getArticleById(articleId)
              + ", комментарий: " + commentReq.getCommentMessage()
      );
      return objMapper.writeValueAsString(
              new CommentCreateResponse(commentId));
    });
  }

  private void removeCommentFromExistingArticle(){
    service.put("/article/deletecomment", (Request req, Response rep) -> {
      CommentDeleteRequest commentDeleteReq = objMapper.readValue(
              req.body(), CommentDeleteRequest.class);

      ArticleId articleId = new ArticleId(commentDeleteReq.getArticleId());
      CommentId commentId = new CommentId(commentDeleteReq.getCommentId());
      LOG.info("удаление комментария из статьи: " +articleId);


      repos.deleteCommentFromArticle(articleId, commentId);

      return objMapper.writeValueAsString(
              new CommentDeleteResponse(repos.getArticleById(articleId)));
    });
  }

  private void displayArticles(){
    service.get("/", (Request req, Response rep) -> {
      Collection<Article> articles = repos.getArticles();
      List<Map<String, String>> articleMapList = articles.stream()
              .map(article -> Map.of("name", article.getTitle(), "comments", Long.valueOf(article.getComments().size()).toString()))
              .toList();
      Map<String, Object> model = new HashMap<>();
      model.put("articles",articleMapList);
      return freeMarkerEngine.render(new ModelAndView(model, "/index.ftl"));
    });
  }
}
