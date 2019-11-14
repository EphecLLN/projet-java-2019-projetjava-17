/**
 * 
 */
package projet;

import java.util.Random;

/**
 * @author Florent Theys
 *
 */

public class Joker {

	private String[] jokers = {"le moite/moite", "le switch", "le j'aime pas ta question donne moi mes points"};
	
	/**
	 * @return getter de la variable jokers.
	 */
	public String[] getJokers() {
		return jokers;
	}

	/**
	 * @param setter de la variable jokers.
	 */
	public void setJokers(String[] jokers) {
		this.jokers = jokers;
	}

	/**
	 * Va prendre les mauvaises réponses de la classe Question et n'en retourner plus qu'une.
	 * @return retourne aléatoirement une mauvaise réponse.
	 */
	public String jokerMM() {
		Question mauvaiseReponse = new Question();
		String[] reponseFausse = mauvaiseReponse.getReponseF();
		Random rep = new Random();
		int num = rep.nextInt(reponseFausse.length);
		return reponseFausse[num];				
	}
	
	/**
	 * génère une autre question aléatoire.
	 */
	public void jokerSwitch() {
			Question.genererQuestion();		
	}
	
	/**
	 * Va incrémenter le compteur de question dans quizz,
	 * va ensuite augmenter le score de l'utilisateur,
	 * et enfin va générer la question suivante.
	 */
	public void jokerWin() {
		Quizz nbQuestion = new Quizz();
		int compteur = nbQuestion.getNumber();
		nbQuestion.setNumber(compteur++);
		Utilisateur resultat = new Utilisateur();
		int score = resultat.getScore() + 3;
		resultat.setNumber(score);
		Question.genererQuestion();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
