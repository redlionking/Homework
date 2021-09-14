package com.example.homework12;


import javax.jms.*;

public class TopicProducer {

    public void producer(Destination destination, Session session){
        try {

            // 创建生产者，生产100个消息
            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index++ < 100) {
                TextMessage message = session.createTextMessage(index + " message.");
                producer.send(message);
            }

           // Thread.sleep(2000);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
