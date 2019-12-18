package projetQuizz.modele;


public class Reponse {
	private boolean estBonneReponse;
	private String reponse;
	private int id;

	/**
	 * Constructeur de la classe Reponse.
	 * @param id l'id de la réponse
	 * @param reponse la reponse
	 */
	Reponse(int id, String reponse) {
		this(id, reponse, false);
	}

	/**
	 * Autre constructeur de la classe Reponse.
	 * @param id l'id de la réponse
	 * @param reponse la reponse
	 * @param estBonneReponse true si la réponse est la correcte
	 */
	Reponse(int id, String reponse, boolean estBonneReponse) {
		this.id = id;
		this.reponse = reponse;
		this.estBonneReponse = estBonneReponse;
	}

	/**
	 * Getter de estBonneReponse.
	 * @return true si la réponse est correcte
	 */
	public boolean getEstBonneReponse() {
		return estBonneReponse;
	}

	/**
	 * Getter de reponse.
	 * @return la réponse
	 */
	public String getReponse() {
		return reponse;
	}

}
