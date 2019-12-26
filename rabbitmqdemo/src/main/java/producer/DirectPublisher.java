package producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

public class DirectPublisher extends Publisher {

    public DirectPublisher(Channel channel, String exchangeName, String queueName) throws Exception {
        super(channel, exchangeName, queueName);
        init();
    }

    private void init() throws Exception {
        channel.exchangeDeclare(exchangeName, "direct", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, "hola");
    }

    public void publish(String routingkey, String message) throws Exception {
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        AMQP.BasicProperties properties = builder.contentType("text/plain").build();

        //String message = "hello,robbie!";
        channel.confirmSelect();
        channel.basicPublish(exchangeName, "hola", null, message.getBytes());
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("ack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("nack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
            }
        });
    }
}
