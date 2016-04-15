package com.fab;

/**
 * thread qui recupere une value du vector et ecrit cette value dans le fichier bonjour.txt
 * 
 * @author merlint
 *
 */
public class Consumer extends Thread {
 
	private Producer producer;
    private FileOperation tp1;
    int identifiant;
    
    Consumer(Producer p, FileOperation tp1, int identifiant) {
		this.tp1 = tp1;
        producer = p;
        this.identifiant =identifiant;
    }
 
    /**
     * 
     */
    @Override
    public void run() {
        try {
            while (!Producer.end) {
            	
            		if(Producer.end){
            			 break;
            		}
                     	
                    String message = producer.getMessage();
                    FileOperation.LOGGER.info("Consumer: "+ this.identifiant+" [ recuperation du message a partir du buffer: ]"); 
                    tp1.writeFile("Bonjour "+message);
                    
                    sleep(5000);               
                  
            }
        } catch (InterruptedException e) {
        	FileOperation.LOGGER.severe(e.getMessage());
        }
        FileOperation.LOGGER.info("consummer: "+ this.identifiant+" a termine");
    }
 
}