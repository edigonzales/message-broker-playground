package org.example;

public class App {

    public static void main(String[] args) {
        
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
 
//        Thread producerThread = new Thread(producer);
//        producerThread.start();
 
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        
        System.out.println("Hallo Welt.");
    }
}
