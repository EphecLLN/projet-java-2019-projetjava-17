package projetQuizz.modele;

public class Resultat {

	private Question question;
	private int score;
	private boolean estBonneReponse;

	public Resultat(Question question, int score, boolean estBonneReponse) {
		this.question = question;
		this.score = score;
		this.estBonneReponse = estBonneReponse;
	}

	public Reponse getBonneReponse() {
		Reponse[] reponses = question.getReponses();
		for (int i = 0; i < reponses.length; i++) {
			if (reponses[i].getEstBonneReponse()) {
				return reponses[i];
			}
		}
		return null;
	}

	public boolean getEstBonneReponse() {
		return estBonneReponse;
	}

	public int getScore() {
		return score;
	}
}
