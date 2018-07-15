package com.mmm.develop.common.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQUtil {
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";
    private static final String PATH = "tcp://120.25.195.137:61616";

    public enum type {
        topic, queue
    };

    public static void produce(type type, String des, String text) throws Exception {
        // 连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER, PASSWORD, PATH);
        // 获取一个连接
        Connection connection = connectionFactory.createConnection();
        // 建立会话
        Session session = connection.createSession(true,
                Session.AUTO_ACKNOWLEDGE);
        // 创建队列
        Destination destination;
        switch (type) {
            case queue: destination = session.createQueue(des); break;
            case topic: destination = session.createTopic(des); break;
            default: destination = session.createQueue(des);
        }
        // 创建生产者
        MessageProducer producer = session.createProducer(destination);
        // 发送消息
        producer.send(session.createTextMessage(text));
        // 提交操作
        session.commit();
    }
    public static void main(String args[]){
        // 测试
        try {
            produce(type.queue, "test", "1dsadasdsad");
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
