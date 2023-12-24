package ru.mts.homework.ArticlesComments;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Batch;
import org.jdbi.v3.core.statement.Update;

public class DBInitializer {

  private final Jdbi jdbi;


  public DBInitializer() {
    Config config = ConfigFactory.load("resources/application.properties");
    this.jdbi = Jdbi.create(
            config.getString("app.database.url"),
            config.getString("app.database.user"),
            config.getString("app.database.password")
    );
  }

  public DBInitializer(Jdbi jdbi) {
    this.jdbi = jdbi;
  }
  public void initialize(){
    Handle handle = jdbi.open();
    Batch batch = handle.createBatch();
    batch.add("DELETE from comment");
    batch.add("DELETE from article");
    batch.execute();
  }

}
