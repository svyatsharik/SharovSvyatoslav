package UserMessages;

public class EnrichmentService implements UserRepository {


  @Override
  public User findByMsisdn(String msisdn) {
    return map.get(msisdn);
  }

  public synchronized Message enrichment(Message message) {
    User user = findByMsisdn(message.content.get("msisdn"));
    if (user == null) return message;
    user.message.content.put("firstName", user.firstName);
    user.message.content.put("lastName", user.lastName);
    return message;
  }
}
