package producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

abstract class Publisher {
    private final static String QUEUE_NAME = "hello1";
    private final static String EXCHANGE = "test.exchange1";
    protected final String queueName;
    protected final String exchangeName;
    //private final String exchangeType;
    protected final Channel channel;

    public Publisher(Channel channel, String exchangeName, String queueName) throws Exception {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        //this.exchangeType = type;
        this.channel = channel;
        //init();
    }

//    private void init( ) throws Exception{
//        if (exchangeType.equals("direct")) {
//            channel.exchangeDeclare(exchangeName, "direct", true);
//            channel.queueDeclare(queueName, true, false, false, null);
//            channel.queueBind(queueName, exchangeName, "hola");
//        } else if (exchangeType.equals("default")) {
//            //exchangeName =;
//            //channel.exchangeDeclare("", "direct", true);
//            channel.queueDeclare(queueName, false, false, false, null);
//        }
//    }

    public static class Builder {
        private String queueName;
        private String exchangeName;
        private final Channel channel;
        private String exchangeType;

        public Builder(Channel channel) {
//            this.queueName = queueName;
//            this.exchangeName = exchangeName;
            this.channel = channel;
        }

        public Builder setQueue(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public Builder setExchange(String exchangeName) {
            this.exchangeName = exchangeName;
            return this;
        }

        public Builder exchangeType(String exchangeType) {
            this.exchangeType = exchangeType;
            return this;
        }

        public Publisher build() throws Exception {
            if (exchangeType.equals("direct")) {
                return new DirectPublisher(channel, exchangeName, queueName);
            } else if (exchangeType.equals("default")) {
                return new DirectPublisher(channel, exchangeName, queueName);
            }

            throw new RuntimeException("invalid parameters");
        }
    }

    public static Builder newBulider(Channel channel) {
        return new Builder(channel);
    }

    public abstract void publish(String routingkey, String message) throws Exception;
    //{
    // if (exchangeType.equals("direct")) {
//            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
//            AMQP.BasicProperties properties = builder.contentType("text/plain").build();
//
//            //String message = "hello,robbie!";
//            channel.confirmSelect();
//            channel.basicPublish(exchangeName, "hola", null, message.getBytes());
//            channel.addConfirmListener(new ConfirmListener() {
//                @Override
//                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
//                    System.out.println("ack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
//                }
//
//                @Override
//                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
//                    System.out.println("nack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
//                }
//            });
    // } else if(exchangeType.equals("default")) {
    //      channel.basicPublish("", queueName, null, message.getBytes() );
    //      System.out.println(" [x] Sent '" + message + "' to default exchange");
    // }
    //}
}
