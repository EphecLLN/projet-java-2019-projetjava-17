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
	Scanner myScan = new Scanner(System.in);
	
	public Question() {
	this.question = "";
	String[] repF = {"", "", ""};
	this.reponsesF = repF;
	this.reponseV = "";
	}
	
	
	public Question(int numTheme, String numQuest) {
		
		Repertoire monRep = new Repertoire();
		this.question = monRep.getRepertoire().get(numTheme).get(numQuest)[0];
		this.reponsesF = Arrays.copyOfRange(monRep.getRepertoire().get(numTheme).get(numQuest), 2,monRep.getRepertoire().get(numTheme).get(numQuest).length);
		this.reponseV = monRep.getRepertoire().get(numTheme).get(numQuest)[1];
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
	public int genererQuestion() {
		System.out.println(this.question);
		String choix;
		int i = 0;
		int resultat = 0;
		while (i != 1) {
			System.out.println("Réponse Carré ou Cash ? --> Ecrivez \"carre\" ou \"cash\"");
			choix = (String) myScan.nextLine();
			if (choix.toLowerCase().compareTo("carre") == 0 || choix.toLowerCase().compareTo("cash") == 0) {
				resultat = this.genererReponse(choix);
				i = 1;
			}
			else {
				System.out.println("Mauvais choix encodé");
			}
		}
		return resultat;
	}
	
	/**
	 * 
	 * @param choix - La méthode de réponse ("Carre" ou "Cash")
	 * @param rep - La réponse choisie
	 * @return le nombre de points obtenus
	 */
	public int genererReponse(String choix) {
		String rep = "";
		Scanner maRep;
		boolean bonneRep = false;
		int boucle = 0;
		
			//Réponse CARRE choisie
			if (choix.toLowerCase().compareTo("carre") == 0) {
				//Enregistrement aléatoire des réponses
				ArrayList<String> reponses = new ArrayList<String>();
				int n = new Random().nextInt(4);
				for (int i = 0;reponses.size() != 4;i++) { 
					if (i == n && bonneRep == false) {
						reponses.add(this.reponseV);
						i--;
						bonneRep = true;
					}
					else {
						reponses.add(this.reponsesF[i]);
					}
				}
				
				//Affichage des réponses
				for (String k : reponses) {
					System.out.println((reponses.indexOf(k)+1) + ". " + k);
				}
				
				//Réponse client
				maRep = new Scanner(System.in);
				while (boucle != 1) {
					rep = maRep.nextLine();
					if (Question.isNumeric(rep) == false || Integer.parseInt(rep) > reponses.size() ) {
						System.out.println("Mauvais choix encodé. Notez bien un chiffre allant de 1 à 4");
					}
					else if (Integer.parseInt(rep) == (reponses.indexOf(this.reponseV)+1)) {
						System.out.println("Correct ! +1");
						boucle = 1;
						return 1;
					}
					else if (Question.isNumeric(rep) == true && Integer.parseInt(rep)<=4 && Integer.parseInt(rep) > 0){
						boucle = 1;
						System.out.println("Dommage ! Mauvais choix");
						return 0;
					}
				}
				
			}
			
			//Reponse CASH
			else if (choix.toLowerCase().compareTo("cash") == 0) {
				System.out.println("N'oubliez pas les majuscules !");
				maRep = new Scanner(System.in);
				rep = maRep.nextLine().toString();
				if (rep.compareTo(reponseV) == 0) {
					boucle = 1;
					System.out.println("Correct ! +3");
					return 3;
				}
				else {
					System.out.println("Dommage ! Mauvais choix");
					return 0;
				}
			}
			else {
				System.out.println("Mauvais choix encodé");
			}
		return -1;
	}
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
