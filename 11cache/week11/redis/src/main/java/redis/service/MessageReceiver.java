package redis.service;

import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    /**
     * 接收消息方法
     */
    public void receiverMessage(String message) {
        System.out.println("MessageReceiver收到一条新消息：" + message);
    }
}
