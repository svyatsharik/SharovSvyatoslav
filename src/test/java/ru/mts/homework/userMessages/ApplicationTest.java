package ru.mts.homework.userMessages;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

  Map<String, String> input = new ConcurrentHashMap<>();
  Message message = new Message(input, Message.EnrichmentType.MSISDN);
  User user = new User(message, "Ivan", "Smirnov");
  EnrichmentService enrichment = new EnrichmentService();

  @Test
  public void checkEnrichment() {
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "88005553535");
    UserRepository.map.put("88005553535", user);
    Message message2 = enrichment.enrichment(message);
    assertSame(message2.content.get("firstName"), user.firstName);
    assertSame(message2.content.get("lastName"), user.lastName);
  }


  @Test
  public void shouldSucceedEnrichmentInConcurrentEnvironmentSuccessfully() throws InterruptedException {
    List<Message> enrichmentResults = new CopyOnWriteArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CountDownLatch latch = new CountDownLatch(5);
    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
        enrichmentResults.add(enrichment.enrichment(message));
        latch.countDown();
      });
    }

    // latch.await();
    for (int i = 0; i < enrichmentResults.size() - 1; i++) {
      assertSame(enrichmentResults.get(i), enrichmentResults.get(i + 1));
    }
  }
}