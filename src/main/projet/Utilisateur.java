/**
 * 
 */
package projet;

import java.util.Scanner;

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
    
    //METHODES
    /**
     * Paramètre le thème du quizz en fonction du choix de l'utilisateur
     */
    public void choisirTheme() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Choisissez le thème de vos questions : ");
	Quizz.getTheme() = sc.nextLine();
    }
    
    /**
     * Paramètre la difficulté du quizz en fonction du choix de l'utilisateur
     */
    public void choisirDifficulte() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Choisissez votre difficulté?");
	Quizz.getDifficulte() = sc.nextLine();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	//TODO
    }
}
