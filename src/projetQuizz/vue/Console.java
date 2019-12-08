/**
 *
 */
package projetQuizz.vue;

import java.util.Scanner;

import projetQuizz.Quizz;
import projetQuizz.modele.Partie;
import projetQuizz.modele.Question;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Theme;

/**
 * @author Flo
 *
 */
public class Console extends InterfaceDeJeu {

	private Scanner in;

	public Console(Quizz quizz) {
		super(quizz);
		in = new Scanner(System.in);
	}

	@Override
	public void choisirTheme(Theme[] themesPossibles) throws Exception {
		System.out.println("Choisissez parmis ces thèmes:");
		for (int i = 0; i < themesPossibles.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + themesPossibles[i].getNom());
		}
		int index = in.nextInt();
		//TODO vérifier que la valeur reçu soit dans le tableau
		getQuizz().recevoirTheme(themesPossibles[index - 1]);

	}

	@Override
	public void demanderNom() throws Exception {
		System.out.println("Quel est votre nom:");
		getQuizz().recevoirNomUtilisateur(in.nextLine());

	}

	@Override
	public void afficherErreur(Exception e) {
		System.out.println(e.getMessage());

	}

	@Override
	public void demanderDifficulte(String[] difficulte) throws Exception {
		System.out.println("Choisissez parmis ces difficultés:");
		for (int i = 0; i < difficulte.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + difficulte[i]);
		}
		int index = in.nextInt();
		getQuizz().recevoirDifficulte(difficulte[index-1]);
	}

	@Override
	public void demanderCarreCash(Question questionActuelle) {
		// TODO A FAIRE
		System.out.println("Choisissez parmis carré ou cash");
	}

	@Override
	public void demanderReponseCash(Question questionActuelle) {
		// TODO Auto-generated method stub
		System.out.println("Inscrivez votre réponse");

	}

	@Override
	public void demanderReponseCarreJoker(Question questionActuelle, Reponse reponsesPossiblesActuelles) {
		// TODO Auto-generated method stub
		System.out.println("Choisissez parmis ces possibilités:");

	}

	@Override
	public void demanderMoiteMoite(Question questionActuelle, Reponse reponsesPossiblesActuelles) {
		// TODO Auto-generated method stub
		System.out.println("Choisissez parmis ces possibilités:");
	}

	@Override
	public void afficherScores() {
		// TODO Auto-generated method stub

	}

}
