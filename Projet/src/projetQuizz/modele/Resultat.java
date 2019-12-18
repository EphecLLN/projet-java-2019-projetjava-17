package projetQuizz.modele;

import java.util.ArrayList;

public class Resultat {

	private Question question;
	private int score;
	private boolean estBonneReponse;

	/**
	 * Constructeur de la classe Resultat.
	 * @param question la question
	 * @param score le nomrbe de point obtenus par cette question
	 * @param estBonneReponse true si la réponse est correcte
	 */
	public Resultat(Question question, int score, boolean estBonneReponse) {
		this.question = question;
		this.score = score;
		this.estBonneReponse = estBonneReponse;
	}

	/**
	 * Getter de bonneReponse.
	 * @return la bonne réponse
	 */
	public Reponse getBonneReponse() {
		ArrayList<Reponse> reponses = question.getReponses();
		for (int i=0; i<reponses.size(); i++) {
			if (reponses.get(i).getEstBonneReponse()) {
				return reponses.get(i);
			}
		}
		return null;
	}

	/**
	 * Getter de score.
	 * @return le score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Getter de estBonneReponse.
	 * @return true si la question a été correctement répondue
	 */
	public boolean getEstBonneReponse() {
		return estBonneReponse;
	}
}
