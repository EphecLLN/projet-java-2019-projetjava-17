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
		Scanner myScan;
		String choix;
		int i = 0;
		while (i != 1) {
			System.out.println("Réponse Carrée ou Cash ?");
			myScan = new Scanner(System.in);
			choix = (String) myScan.nextLine();
			if (choix.toLowerCase().compareTo("carre") == 0 || choix.toLowerCase().compareTo("cash") == 0) {
				myScan.close();
				maQuest.genererReponse(choix);
				i = 1;
			}
			else {
				System.out.println("Mauvais choix encodé");
			}
		}
	}
	
	/**
	 * 
	 * @param choix - La méthode de réponse ("Carre" ou "Cash")
	 * @param rep - La réponse choisie
	 * @return le nombre de points obtenus
	 */
	public int genererReponse(String choix) {
		String rep = "";
		Scanner myScan;
		
		if (choix.toLowerCase().compareTo("carre") == 0) {
			ArrayList<String> reponses = new ArrayList<String>();
			int n = new Random().nextInt(3);
			for (int i = 0;i<this.reponsesF.length;i++) { 
				if (i == n) {
					reponses.add(this.reponseV);
					i--;
					n = -1;
				}
				else {
					reponses.add(this.reponsesF[i]);
				}
			}
			for (String k : reponses) {
				System.out.println(k);
			}
			
			myScan = new Scanner(System.in);
			rep = myScan.nextLine();
			myScan.close();
			for (int j = 0;j < reponses.size();j++) {
				if (reponses.get(j).compareTo(this.reponseV) == 0) {
					System.out.println("Correct ! +1");
					return 1;
				}
				for (int k = 0;k < this.reponsesF.length;k++) {
					if (reponses.get(j).compareTo(this.reponsesF[k]) == 0) {
						System.out.println("Dommage ! Mauvais choix");
						return 0;
					}
				}
			}
		}
		else if (choix.toLowerCase().compareTo("cash") == 0) {
			if (rep.compareTo(reponseV) == 0) {System.out.println("Correct ! +3");return 3;}
			else {return 0;}
		}
		else {
			System.out.println("Mauvais choix encodé");
			return -1;
		}
	}
}
