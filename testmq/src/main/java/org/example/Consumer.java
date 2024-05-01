package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.Connection;
import jakarta.jms.Destination;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

import static jakarta.jms.Session.AUTO_ACKNOWLEDGE;
import static org.apache.activemq.ActiveMQConnection.DEFAULT_BROKER_URL;

public class Consumer implements Runnable {

    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("user", "1234", DEFAULT_BROKER_URL);

            //Create Connection
            Connection connection = factory.createConnection();

            // Start the connection
            connection.start();

            // Create Session
            Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);

            //Create queue
            Destination queue = session.createQueue("MyFirstQueue");

            MessageConsumer consumer = session.createConsumer(queue);

            Message message = consumer.receive(1000);
            
            // System.out.println("Class: " + message.getClass());

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Consumer Received: " + text);
            }

            session.close();
            connection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured");
        }


    }

}
