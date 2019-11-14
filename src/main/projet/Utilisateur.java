/**
 * 
 */
package projet;

/**
 * @author Autome Edwin
 *
 */
public class Utilisateur {
    
    private String pseudo;
    private int score;
    
    // GETTERS & SETTERS
    /**
     * @return le pseudo de l'utilisateur
     */
    public String getPseudo() {
	return pseudo;
    }   
    
    /**
     * @return le score de l'utilisateur
     */
    public int getScore() {
	return score;
    }
    
    /**
     * Définit le pseudo de l'utilisateur
     * @param pseudo désiré par l'utilisateur 
     */
    public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
    }
    
    /**
     * Définit le score de l'utilisateur
     * @param score que l'utilisateur obtient
     */
    public void setScore(int score) {
	this.score = score;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }
}
