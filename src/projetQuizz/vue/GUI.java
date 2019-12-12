/**
 *
 */
package projetQuizz.vue;

import java.util.ArrayList;

import projetQuizz.Quizz;
import projetQuizz.modele.Partie.Difficulte;
import projetQuizz.modele.Partie.Joker;
import projetQuizz.modele.Question;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Resultat;
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
	public void afficherErreur(Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherResultat(Resultat resultat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherScores(Resultat[] resultat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void choisirTheme(ArrayList<Theme> themesPossibles) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderCarreCash(Question questionActuelle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderDifficulte(Difficulte[] difficultes) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderMoiteMoite(Reponse[] reponsesPossiblesActuelles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderNom() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderReponseCarreJoker(Reponse[] reponsesPossiblesActuelles, Joker[] jokersPossibles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderReponseCash() {
		// TODO Auto-generated method stub

	}

}
