package com.fab;

import java.util.Vector;

/**
 * Thread qui va lire le fichier listePersonnes et va sauvegarder la valeur obtenue dans un vector
 * la sauvegarde ne sera possible que si le vector n a pas atteind sa taille maximale
 * 
 * Producer arrete de travailler si la valeur end est true;
 * 
 * @author merlint
 *
 */
public class Producer extends Thread {

	private static final int MAXQUEUE = 1;
	private Vector<String> messages = new Vector<>();
	private FileOperation tp1;
	public static boolean end;
	
	public Producer(FileOperation tp1) {
		this.tp1 = tp1;
	}

	
	@Override
	public void run() {
		try {
			while (true) {
				if (end)
					break;
				putMessage();
				sleep(500);
				
			}
		} catch (InterruptedException e) {
			FileOperation.LOGGER.severe(e.getMessage());
		}
	}

	/**
	 * lecture du fichier listePersonnes et insersion de la valeur lue dans le vector
	 * si la valeur lue est nulle
	 * tentative de null vers bonjour.txt qui va declencher la fermeture du dit fichier
	 * 
	 * @throws InterruptedException
	 */
	private synchronized void putMessage() throws InterruptedException {

		while (messages.size() == MAXQUEUE) {
			wait();
		}
		String[] values = tp1.readFile();

		if (values != null) {
			messages.addElement(values[0] + " " + values[1] + "-" + values[2]);

			FileOperation.LOGGER.info("sauvegarde message dans le buffer");
			
			
		} else {
			FileOperation.LOGGER.info("la precedente sauvegarde etait la dernier");
			tp1.writeFile(null);
			end = true;
			return;
		}
		
		notifyAll();
	}

	/**
	 * lecture et retour de la valeur sauvegardee dans le vector
	 * puis remise de la taille du vector a sa taille courante - 1;
	 * 
	 * @return  message  String
	 * @throws InterruptedException
	 */
	public synchronized String getMessage() throws InterruptedException {
		notifyAll();
		while (messages.size() == 0) {
			wait();
		}
		String message = (String) messages.firstElement();
		messages.removeElement(message);
	
		return message;
	}
}