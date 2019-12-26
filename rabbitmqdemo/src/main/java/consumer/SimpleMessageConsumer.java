package consumer;

import com.rabbitmq.client.*;

public class SimpleMessageConsumer {
    private final static String QUEUE_NAME = "hello1";
    private final static String EXCHANGE = "test.exchange1";

    public static void receiveDefaultExchange(Channel channel, String queueName) throws Exception {
        channel.queueDeclare(queueName, false, false, false, null);
        receiveMessage(channel, queueName);
    }

    public static void receiveDirectExchange(Channel channel) throws Exception {
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE, "hola");
        receiveMessage(channel, QUEUE_NAME);
    }

    public static void receiveMessage(Channel channel, String queueName) throws Exception {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            System.out.println(consumerTag);
        });
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.254.131");
        connectionFactory.setUsername("robbie");
        connectionFactory.setPassword("19811981");
        connectionFactory.setVirtualHost("testvhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //receiveDefaultExchange(channel, "pdf_create");
        receiveDirectExchange(channel);


    }

}
