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
	private String question = "";
	private String[] reponsesF = {"","", ""};
	private String reponseV;
	
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
	 * 
	 */
	public void genererQuestion() {
		this.setQuestion("Comment je m'appelle?");
		System.out.println(getQuestion());
		Scanner scanner = new Scanner(System.in);
		String inputString = "";
		while ((inputString != "Carre") || (inputString != "Cash")) {
			System.out.println("Comment souhaitez-vous Répondre ?");
			inputString = scanner.nextLine();
		}
		scanner.close();
		genererReponse(inputString);
		
	}
	public void genererReponse(String choix) {
		int[] flags = {0,0,0,0};
		setReponseV("Moi");
		String[] rep = {"Lui", "Il", "Toi"};
		Random rand = new Random();
		int num;
		if (choix == "Carre") {
			for (int i = 0;i<(rep.length+1);i++) {
				num = rand.nextInt((rep.length+1));
				System.out.println(num);
			}
		}
	}
	public int reponseCash() {
		return 3;
	}
	public int reponseCarre() {
		return 1;
	}
	public boolean checkReponse(String rep) {
		return true;
	}
}
