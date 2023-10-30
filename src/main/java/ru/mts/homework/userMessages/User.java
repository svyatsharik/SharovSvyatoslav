package ru.mts.homework.userMessages;

public class User {
  public User(Message message, String firstName, String lastName) {
    this.message = message;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Message message;
  public String firstName;
  public String lastName;

}
