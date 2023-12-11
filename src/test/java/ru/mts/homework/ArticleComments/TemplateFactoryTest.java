package ru.mts.homework.ArticleComments;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.mts.homework.ArticlesComments.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TemplateFactoryTest {
  private ArticlesRepository repos;

  @BeforeEach
  void beforeEach(){
    repos = new ArticlesRepository();
  }

  @AfterEach
  void afterEach(){

  }
  @Disabled
  @Test
  public void shouldFreeMarkerEngine(){
    FreeMarkerEngine engine = TemplateFactory.freeMarkerEngine();

    for (int i = 0; i < 10; i++) {
      repos.addNewArticle("New article #" +i);
    }

    Collection<Article> articles = repos.getArticles();
    List<Map<String, String>> articleMapList = articles.stream()
            .map(article -> Map.of("name", article.getTitle(), "comments", Long.valueOf(article.getComments().size()).toString()))
            .toList();
    Map<String, Object> model = new HashMap<>();
    model.put("articles",articleMapList);
    assertTrue(!engine.render(new ModelAndView(model, "/resources/index.ftl")).equals(""));
  }
}
