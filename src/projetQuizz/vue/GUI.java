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
public class GUI extends InterfaceDeJeu {

	/**
	 * @param quizz
	 */
	public GUI(Quizz quizz) {
		super(quizz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void choisirTheme(Theme[] themesPossibles) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderNom() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherErreur(Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderDifficulte(String[] difficulte) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderCarreCash(Question questionActuelle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderReponseCash(Question questionActuelle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderReponseCarreJoker(Question questionActuelle, Reponse reponsesPossiblesActuelles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderMoiteMoite(Question questionActuelle, Reponse reponsesPossiblesActuelles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherScores() {
		// TODO Auto-generated method stub

	}

}
