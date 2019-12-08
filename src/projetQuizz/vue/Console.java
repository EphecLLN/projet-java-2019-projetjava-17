/**
 *
 */
package projetQuizz.vue;

import java.util.Scanner;

import projetQuizz.Quizz;
import projetQuizz.QuizzException;
import projetQuizz.modele.Partie;
import projetQuizz.modele.Partie.CarreCash;
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
public class Console extends InterfaceDeJeu {

	private Scanner in;

	public Console(Quizz quizz) {
		super(quizz);
		in = new Scanner(System.in);
	}

	@Override
	public void afficherErreur(Exception e) {
		System.out.println(e.getMessage());
	}

	@Override
	public void afficherResultat(Resultat resultat) throws Exception {
		if (resultat.getEstBonneReponse()) {
			System.out.println("Bravo ! Vous avez gagn� " + resultat.getScore() + " points.");
		} else {
			System.out.println("Rat�, la bonne r�ponse �tait: " + resultat.getBonneReponse().getReponse() + ".");
		}
		getQuizz().passerQuestionSuivante();
	}

	@Override
	public void afficherScores(Resultat[] resultats) {
		int score = 0;
		int nbBonnesReponses = 0;
		int nbMauvaisesReponses = 0;
		for (int i = 0; i < resultats.length; i++) {
			if (resultats[i].getEstBonneReponse()) {
				nbBonnesReponses++;
			} else {
				nbMauvaisesReponses++;
			}
			score += resultats[i].getScore();
		}
		System.out.println("Votre score final est de " + score + " point(s) avec " + nbBonnesReponses
				+ " bonne(s) r�ponse(s) et " + nbMauvaisesReponses + " mauvaise(s) r�ponse(s).");
	}

	@Override
	public void choisirTheme(Theme[] themesPossibles) throws Exception {
		System.out.println("Choisissez parmis ces th�mes:");
		for (int i = 0; i < themesPossibles.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + themesPossibles[i].getNom());
		}
		int index = demanderNombre(1, themesPossibles.length);
		getQuizz().recevoirTheme(themesPossibles[index - 1]);
	}

	@Override
	public void demanderCarreCash(Question questionActuelle) throws Exception {
		System.out.println(questionActuelle.getQuestion());
		System.out.println("Choisissez parmis:");
		System.out.println("1. carr�");
		System.out.println("2. cash");
		int carreCash = demanderNombre(1, 2);
		getQuizz().recevoirCarreCash(carreCash == 1 ? CarreCash.CARRE : CarreCash.CASH);
	}

	@Override
	public void demanderDifficulte(Difficulte[] difficultes) throws Exception {
		System.out.println("Choisissez parmis ces difficult�s:");
		for (int i = 0; i < difficultes.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + Partie.getNomDifficulte(difficultes[i]));
		}
		int index = demanderNombre(1, difficultes.length);
		getQuizz().recevoirDifficulte(difficultes[index - 1]);
	}

	@Override
	public void demanderMoiteMoite(Reponse[] reponsesPossiblesActuelles) throws Exception {
		System.out.println("Choisissez parmis ces possibilit�s:");
		for (int i = 0; i < reponsesPossiblesActuelles.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + reponsesPossiblesActuelles[i].getReponse());
		}
		int index = demanderNombre(1, reponsesPossiblesActuelles.length);
		getQuizz().recevoirReponseMoiteMoite(reponsesPossiblesActuelles[index - 1]);
	}

	@Override
	public void demanderNom() throws Exception {
		System.out.println("Quel est votre nom:");
		getQuizz().recevoirNomUtilisateur(in.nextLine());

	}

	/**
	 * nextInt() plante en boucle si on lui donne un string, gestion d'erreur en
	 * utilisant nextLine().
	 */
	private int demanderNombre(int min, int max) throws QuizzException {
		int n;
		try {
			n = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			throw new QuizzException("Veuillez entrer un nombre.");
		}
		if (n < min || n > max) {
			throw new QuizzException("Veuillez choisir parmis les num�ros propos�s.");
		}
		return n;
	}

	@Override
	public void demanderReponseCarreJoker(Reponse[] reponsesPossiblesActuelles, Joker[] jokersPossibles)
			throws Exception {
		System.out.println("Choisissez parmis ces possibilit�s:");
		for (int i = 0; i < reponsesPossiblesActuelles.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + reponsesPossiblesActuelles[i].getReponse());
		}
		for (int i = 0; i < jokersPossibles.length; i++) {
			System.out.println(Integer.toString(i + 1 + reponsesPossiblesActuelles.length) + ". "
					+ Partie.getNomJokers(jokersPossibles[i]));

		}
		int index = demanderNombre(1, reponsesPossiblesActuelles.length + jokersPossibles.length);
		if (index <= reponsesPossiblesActuelles.length) {
			getQuizz().recevoirReponseCarre(reponsesPossiblesActuelles[index - 1]);
		} else {
			getQuizz().recevoirJoker(jokersPossibles[index - 1 - reponsesPossiblesActuelles.length]);
		}

	}

	@Override
	public void demanderReponseCash() throws Exception {
		System.out.println("Inscrivez votre r�ponse");
		getQuizz().recevoirReponseCash(in.nextLine());
	}
}
