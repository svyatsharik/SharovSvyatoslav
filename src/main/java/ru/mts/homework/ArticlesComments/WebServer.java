package ru.mts.homework.ArticlesComments;


import spark.*;


public class WebServer {

  public static void main(String[] args) {
    Service service = Service.ignite();

    ArticleApplication application = new ArticleApplication(service);
    application.start();
  }

}
