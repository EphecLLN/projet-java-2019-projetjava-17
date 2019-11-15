/**
 * 
 */
package projet;

import java.util.*;

/**
 * @author Gauthier Verschraegen
 *
 */
public class Question {
	private String question;
	private String[] reponsesF;
	private String reponseV;
	
	public Question() {
	this.question = "Qui suis-je ?";
	String[] repF = {"Lui", "Il", "Toi"};
	this.reponsesF = repF;
	this.reponseV = "Moi";
	}
	
	
	public Question(String question, String[] reponsesF, String reponseV) {
		this.question = question;
		this.reponsesF = reponsesF;
		this.reponseV = reponseV;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the reponsesF
	 */
	public String[] getReponsesF() {
		return reponsesF;
	}
	/**
	 * @param reponsesF the reponsesF to set
	 */
	public void setReponsesF(String[] reponsesF) {
		this.reponsesF = reponsesF;
	}
	/**
	 * @return the reponseV
	 */
	public String getReponseV() {
		return reponseV;
	}
	/**
	 * @param reponseV the reponseV to set
	 */
	public void setReponseV(String reponseV) {
		this.reponseV = reponseV;
	}	
	
	/**
	 * G�n�re une question (actuellement pr�d�finie) et renvoie les points issus de la r�ponse � cette question
	 * M�thode � am�liorer
	 * @param choix - La m�thode de r�ponse ("Carre" ou "Cash")
	 * @param rep - La r�ponse choisie
	 * @return le nombre de points obtenus
	 * 
	 */
	public int genererQuestion(String choix, String rep) {
		int points;
		Question maQuest = new Question();
		System.out.println(maQuest.question);
		points = maQuest.genererReponse(choix,rep);
		return points;
	}
	
	/**
	 * 
	 * @param choix - La m�thode de r�ponse ("Carre" ou "Cash")
	 * @param rep - La r�ponse choisie
	 * @return le nombre de points obtenus
	 */
	public int genererReponse(String choix, String rep) {
		if (choix.compareTo("Carre") == 0) {
			if (rep.compareTo(reponseV) == 0) {System.out.println("Correct ! +1");return 1;}
			else {return 0;}
		}
		else if (choix.compareTo("Cash") == 0) {
			if (rep.compareTo(reponseV) == 0) {System.out.println("Correct ! +3");return 3;}
			else {return 0;}
		}
		else {
			System.out.println("Mauvais choix encod�");
			return -1;
		}
	}
}
