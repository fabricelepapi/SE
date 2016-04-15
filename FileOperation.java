package com.fab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * class qui contient les operation de lecture et ecriture 
 * 
 * @author merlint
 *
 */
public class FileOperation {

	public static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	BufferedReader br;
	PrintWriter pw;
	int nberLine = 0;

	public FileOperation() {
		setReadFile(new File("listePersonnes.txt"));
		setWriteFile();
	}

	/**
	 * sauvegarde les donnees du fichier dans le BufferedReader en vue de lecture
	 * 
	 * @param fin ficher File
	 * @return br BufferedReader
	 */
	public BufferedReader setReadFile(File fin) {

		try {
			br = new BufferedReader(new FileReader(fin));
			LOGGER.info("debut lecture du fichier listePersonne.txt");
			return br;

		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		} finally {
			if (br == null) {
				throw new RuntimeException("BufferedReader is null");
			}
		}

		return null;

	}

	/**
	 * lecture ligne par ligne du fichier et fermeture du buffer si end of ligne
	 * 
	 * @return  chaine de character String[]
	 */
	public String[] readFile() {

		String line = null;

		try {

			if ((line = br.readLine()) != null) {

				String[] contents = line.split("\\s{1,}");
				LOGGER.info("lecture ligne " + (++nberLine));
				return contents;

			} else {

				LOGGER.info("fin lecture");
				br.close();
				return null;
			}

		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}

		return null;

	}

	/**
	 * prepare le fichier bonjour.txt pour l ecriture
	 * 
	 * @return pr PrintWriter
	 */
	public PrintWriter setWriteFile() {

		try {
			pw = new PrintWriter(new FileWriter("bonjour.txt"));
			LOGGER.info("debut ecrire dans le fichier bonjour.txt");
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
		return pw;
	}

	/**
	 * ecriture ligne apres ligne dans le fichier bonjour.txt
	 * fermeture du PrintWriter si la valeur a ecrire est null
	 * 
	 * @param value String
	 */
	public void writeFile(String value) {

		if (value != null) {
			LOGGER.info("ecriture de la valeur: " + value);
			pw.println (value.substring(0, value.indexOf("-"))+ " "+value.substring(value.indexOf("-")+1, value.length()) );
		} else {
			LOGGER.info(" fin ecriture");
			pw.close();
		}

	}
}
