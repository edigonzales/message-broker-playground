package org.example;

import jakarta.jms.Connection;
import jakarta.jms.DeliveryMode;
import jakarta.jms.Destination;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

import static jakarta.jms.Session.AUTO_ACKNOWLEDGE;
import static org.apache.activemq.ActiveMQConnection.DEFAULT_BROKER_URL;

public class Producer implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub

        try { 
            // Create a connection factory.
            //ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("user", "1234", DEFAULT_BROKER_URL);

            //Create connection.
            Connection connection = factory.createConnection();

            // Start the connection
            connection.start();

            // Create a session which is non transactional
            Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);

            // Create Destination queue
            Destination queue = session.createQueue("MyFirstQueue");

            // Create a producer
            MessageProducer producer = session.createProducer(queue);

            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            String msg = "Hello World";

            // insert message
            TextMessage message = session.createTextMessage(msg);
            System.out.println("Producer Sent: " + msg);
            producer.send(message);

            session.close();
            connection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured");
        }
    }

}
