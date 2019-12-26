package producer;

import com.rabbitmq.client.*;

public class SimpleMessageProducer {

    private final static String QUEUE_NAME = "hello1";
    private final static String EXCHANGE = "test.exchange1";

    private void setDirectExchange(Channel channel, String queue, String exchange) throws Exception {
        channel.exchangeDeclare(exchange, "direct", true);
        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, "hola");
    }

    private void setDefaultExchange(Channel channel) throws Exception {
        channel.exchangeDeclare("", "direct", true);
        //channel.queueBind()
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.254.131");
        connectionFactory.setUsername("robbie");
        connectionFactory.setPassword("19811981");
        connectionFactory.setVirtualHost("testvhost");

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            Publisher publisher = Publisher.newBulider(channel)
                    .setExchange(EXCHANGE)
                    .setQueue(QUEUE_NAME)
                    .exchangeType("direct")
                    .build();

            for (int i = 0; i < 10; i++) {
                //publisher.publish("pdf_create" , "book name");
                publisher.publish("hola", "this is test message");
            }

        }
    }
}
