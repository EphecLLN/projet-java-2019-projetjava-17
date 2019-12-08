/**
 *
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Reponse {
	private boolean estBonneReponse;
	private String reponse;
	private int id;

	Reponse(int id, String reponse) {
		this(id, reponse, false);
	}

	Reponse(int id, String reponse, boolean estBonneReponse) {
		this.id = id;
		this.reponse = reponse;
		this.estBonneReponse = estBonneReponse;
	}

	public boolean getEstBonneReponse() {
		return estBonneReponse;
	}

	public String getReponse() {
		return reponse;
	}

}
