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
	 * Génère une question (actuellement prédéfinie) et renvoie les points issus de la réponse à cette question
	 * Méthode à améliorer
	 * @param choix - La méthode de réponse ("Carre" ou "Cash")
	 * @param rep - La réponse choisie
	 * @return le nombre de points obtenus
	 * 
	 */
	public void genererQuestion() {
		Question maQuest = new Question();
		System.out.println(maQuest.question);
		Scanner myScan = new Scanner(System.in);
		String choix;
		int i = 0;
		while (i != 1) {
			choix = (String) myScan.nextLine();
			if (choix.compareTo("Carre") == 0 || choix.compareTo("Cash") == 0) {
				maQuest.genererReponse(choix);
				i = 1;
			}
			else {
				System.out.println("Mauvais choix encodé");
			}
			myScan.close();
		}
	}
	
	/**
	 * 
	 * @param choix - La méthode de réponse ("Carre" ou "Cash")
	 * @param rep - La réponse choisie
	 * @return le nombre de points obtenus
	 */
	public int genererReponse(String choix) {
		String rep;
		Scanner myScan;
		
		if (choix.compareTo("Carre") == 0) {
			for (int i = 0;i<this.reponsesF.length;i++) {
				System.out.println(this.reponsesF[i]+ "\n");
			}
			System.out.println(this.reponseV);
		}
		myScan = new Scanner(System.in);
		rep = (String) myScan.nextLine();
		if (rep.compareTo(reponseV) == 0) {
			if (choix.compareTo("Carre") == 0) {
				System.out.println("Correct ! +1");
				myScan.close();
				return 1;
			}
			else {
				myScan.close();
				return 0;
			}
		}
		else if (choix.compareTo("Cash") == 0) {
			if (rep.compareTo(reponseV) == 0) {System.out.println("Correct ! +3");return 3;}
			else {return 0;}
		}
		else {
			System.out.println("Mauvais choix encodé");
			return -1;
		}
	}
}
