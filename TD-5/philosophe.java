
public class Philosophe extends Thread {
	
	int no;
	int nbBouchees;
	/** stoppe le processus s'il a fini son assiette */
	boolean fini;
	long debut;
	Fourchettes lesFourchettes;
	LeDiner laTable;
	
	Philosophe()
	{
		fini = true;
	}
	
	/** initialise le no et nb de bouchees */
	Philosophe(int _no, int _nbBouchees, Fourchettes _lesFourchettes, LeDiner _laTable)
	{
		no = _no;
		nbBouchees = _nbBouchees;
		lesFourchettes = _lesFourchettes;
		fini = false;
		laTable = _laTable;
	}
	
	public void run()
	{
		debut = System.currentTimeMillis();
		while(!fini)
		{
			//System.out.println("philo"+no+" : je pense en attendant les fourchettes");
			laTable.penserEtAttendre(no);
            lesFourchettes.prendre(no);
            laTable.manger(no, nbBouchees);
            //System.out.println("philo"+ no+ " : je mange, il me reste " + nbBouchees + " bouchees.");
            nbBouchees--;
            fini = (nbBouchees<=0); 
            try {	Thread.sleep((int)(Math.random()*2000));}  // 100 ms max
            catch (InterruptedException e) {}
            //System.out.println("philo"+ no+ " : je depose les fourchettes.");
            lesFourchettes.deposer(no);
            laTable.penser(no);
			//System.out.println("philo"+no+" : je pense un peu");
            try {	Thread.sleep((int)(Math.random()*2000));}  // 100 ms max
            catch (InterruptedException e) {}
		}
		long fin = System.currentTimeMillis();
		laTable.finir(no);
		//System.out.println("philo"+ no+ " : j'ai fini en " + (float)((float)(fin-debut)/1000) + "s...");

	}

}
