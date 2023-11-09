package ru.mts.homework.userMessages;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

  public interface UserRepository {
  Map<String, User> map = new ConcurrentHashMap<>();

  User findByMsisdn(String msisdn);

}