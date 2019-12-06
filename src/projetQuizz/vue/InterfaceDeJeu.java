/**
 *
 */
package projetQuizz.vue;

import projetQuizz.Quizz;
import projetQuizz.modele.Question;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Theme;

/**
 * @author Flo
 *
 */
public abstract class InterfaceDeJeu {

	private Quizz quizz;

	public InterfaceDeJeu(Quizz quizz) {
		this.quizz = quizz;
	}

	public abstract void choisirTheme(Theme[] themesPossibles) throws Exception;
	public abstract void demanderNom() throws Exception;
	public abstract void demanderDifficulte(String[] difficulte) throws Exception;

	public Quizz getQuizz() {
		return quizz;
	}

	public abstract void afficherErreur(Exception e);
	public abstract void demanderCarreCash(Question questionActuelle);
	public abstract void demanderReponseCash(Question questionActuelle);
	public abstract void demanderReponseCarreJoker(Question questionActuelle, Reponse reponsesPossiblesActuelles);
	public abstract void demanderMoiteMoite(Question questionActuelle, Reponse reponsesPossiblesActuelles);
	public abstract void afficherScores();



}
