package redis.service;

public interface PublishService {
    void publish(String topicName,String message);
}
