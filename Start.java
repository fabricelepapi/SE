package com.fab;


public class Start {


    public static void main(String args[]) {
    	
    	FileOperation tp = new FileOperation();
        Producer producer = new Producer(tp);
        
        producer.start();
       
        new Consumer(producer, tp, 1).start();
        new Consumer(producer, tp, 2).start();
        new Consumer(producer, tp, 3).start();
        new Consumer(producer, tp, 4).start();
    }

}