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
    
    //CONSTRUCTEUR
    /**
      *Constucteur par défaut de l'objet Utilisateur
      */
    public Utilisateur() {
	this.pseudo = "";
	this.score = 0;
    }
    
    //METHODES
    /**
     * Paramètre le thème du quizz en fonction du choix de l'utilisateur
     */
    public void choisirTheme() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Choisissez le thème de vos questions : ");
	Quizz.setTheme(sc.nextLine());
    }
    
    /**
     * Paramètre la difficulté du quizz en fonction du choix de l'utilisateur
     */
    public void choisirDifficulte() {
    String i;
	Scanner sc = new Scanner(System.in);
	System.out.println("Choisissez votre difficulté?");
	i = sc.nextLine();
	Quizz.setDifficulte(i);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	Utilisateur user = new Utilisateur();
    }
}
