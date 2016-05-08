import java.util.Arrays;


public class Fourchettes {

     int taille;
    
    public Fourchettes(int _taille) {
    	lesFourchettes = new boolean[_taille];
    	Arrays.fill(lesFourchettes, true);
        taille = _taille;
    }
    

    public synchronized void prendre(int no) {
    	int gauche = no;
    	int droite = (no+1)%taille;    	
        while (!lesFourchettes[gauche] || !lesFourchettes[droite]) {            
            try   {  wait();  }
            catch (InterruptedException e) {}
        }
        lesFourchettes[gauche] = false;
        lesFourchettes[droite] = false;
    }
    
 
    public synchronized void deposer(int no) {
    	int gauche = no;
    	int droite = (no+1)%taille;    	
        lesFourchettes[gauche] = true;
        lesFourchettes[droite] = true;
        notifyAll(); // reveille les processus en attente de fourchettes
    }       
    
    public String toString() {
        String chaine = "";
        for(int i=0; i<taille; i++)
            chaine = chaine + lesFourchettes[i] + " ; ";
        return chaine;
    }


}
