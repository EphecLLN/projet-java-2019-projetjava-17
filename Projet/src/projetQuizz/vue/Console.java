package projetQuizz.vue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

public class Console extends InterfaceDeJeu {

	private Scanner in;

	public Console(Quizz quizz) {
		super(quizz);
		in = new Scanner(System.in);
	}

	/**
	 * Affichage de message d'erreur.
	 */
	@Override
	public void afficherErreur(Exception e) {
		System.out.println(e.getMessage());
	}

	/**
	 * Affiche le résultat de la question après que l'utilisateur ait répondu.
	 * @param resultat : résultat de l'utilisateur.
	 * @throws Exception : Si erreur dans l'etat.
	 */
	@Override
	public void afficherResultat(Resultat resultat) throws Exception {
		if (resultat.getEstBonneReponse()) {
			System.out.println("Bravo ! Vous avez gagné " + resultat.getScore() + " points.");
		} else {
			System.out.println("Raté, la bonne réponse était: " + resultat.getBonneReponse().getReponse() + ".");
		}
		getQuizz().passerQuestionSuivante();
	}

	/**
	 * Affiche le résultat final qu'à eu l'utilisateur à la fin du quizz.
	 * @param endedPartie : résultat du total des questions durant la partie.
	 */
	@Override
	public void afficherScores(Partie endedPartie) {
		System.out.println(endedPartie.getTexteDeFin());
	}

	/**
	 * L'utilisateur choisit parmi un choix de thèmes prédéfinis.
	 * @param themesPossibles : choix des thème disponible pour l'utilisateur.
	 * @throws Exception : Si erreur dans l'etat.
	 */
	@Override
	public void choisirTheme(ArrayList<Theme> themesPossibles) throws Exception {
		System.out.println("Choisissez parmis ces thèmes:");
		for (int i = 0; i < themesPossibles.size(); i++) {
			System.out.println(Integer.toString(i + 1) + ". " + themesPossibles.get(i).getNom());
		}
		int index = demanderNombre(1, themesPossibles.size());
		getQuizz().recevoirTheme(themesPossibles.get(index -1));
	}

	/**
	 * Affiche la question à l'utilisateur et lui demande s'il veut répondre en carré ou en cash.
	 * @param questionActuelle : la question posé à l'utilisateur.
	 * @throws Exception : Si erreur dans l'etat.
	 */
	@Override
	public void demanderCarreCash(Question questionActuelle) throws Exception {
		System.out.println(questionActuelle.getQuestion());
		System.out.println("Choisissez parmi:");
		System.out.println("1. carré");
		System.out.println("2. cash");
		int carreCash = demanderNombre(1, 2);
		getQuizz().recevoirCarreCash(carreCash == 1 ? CarreCash.CARRE : CarreCash.CASH);
	}

	/**
	 * Demande à l'utilisateur de choisir parmi les difficultés proposées.
	 * @param difficultes : choix des difficultés disponibles.
	 * @throws Exception : Si erreur dans l'etat.
	 */
	@Override
	public void demanderDifficulte(Difficulte[] difficultes) throws Exception {
		System.out.println("Choisissez parmis ces difficultés:");
		for (int i = 0; i < difficultes.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + Partie.getNomDifficulte(difficultes[i]));
		}
		int index = demanderNombre(1, difficultes.length);
		getQuizz().recevoirDifficulte(difficultes[index - 1]);
	}

	/**
	 * Demande la réponse à l'utilisateur parmi les deux qui lui seront proposées.
	 * @param reponsesPossiblesActuelles : les réponse carrés de la question posée.
	 * @throws Exception : Si erreur dans l'etat.
	 */
	@Override
	public void demanderMoiteMoite(Question question, Reponse[] reponsesPossiblesActuelles) throws Exception {
		poserQuestion(question, reponsesPossiblesActuelles);
		int index = demanderNombre(1, reponsesPossiblesActuelles.length);
		getQuizz().recevoirReponseCarre(reponsesPossiblesActuelles[index - 1]);
	}

	private void poserQuestion(Question question, Reponse[] reponsesPossiblesActuelles) {
		System.out.println("Choisissez parmis ces possibilités:");
		for (int i = 0; i < reponsesPossiblesActuelles.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + reponsesPossiblesActuelles[i].getReponse());
		}
	}

	/**
	 *	Demande son nom à l'utilisateur.
	 *	@throws Exception : Si erreur dans l'etat.
	 */
	@Override
	public void demanderNom() throws Exception {
		String content = new String(Files.readAllBytes(Paths.get("regles.txt")));
		System.out.println(content);

		System.out.println("Quel est votre nom:");
		String name = in.nextLine();
		getQuizz().recevoirNomUtilisateur(name);
	}

	/**
	 * Redemande à l'utilisateur de rentrer un nombre lorsqu'un nombre était attendu.
	 * Vérifie que le nombre entré soit bien dans les choix.
	 * @throws QuizzException : gestion d'erreur si l'utilisateur n'encode pas un nombre.
	 * @return n : le nombre entrer par l'utilisateur.
	 */
	private int demanderNombre(int min, int max) throws QuizzException {
		int n;
		try {
			n = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			throw new QuizzException("Veuillez entrer un nombre.");
		}
		if (n < min || n > max) {
			throw new QuizzException("Veuillez choisir parmis les numéros proposés.");
		}
		return n;
	}

	/**
	 * Demande à l'utilisateur s'il veut répondre à une des propositions carré ou s'il veut utiliser un joker.
	 * @param question la question à poser
	 * @param jokersPossibles : liste des jokers disponibles.
	 * @param reponsesPossiblesActuelles : les réponses carrées qui lui sont proposées pour cette question.
	 * @throws Exception : Si erreur dans l'etat.
	 */
	@Override
	public void demanderReponseCarreJoker(Question question, Reponse[] reponsesPossiblesActuelles, Joker[] jokersPossibles)
			throws Exception {
		poserQuestion(question, reponsesPossiblesActuelles);
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

	/**
	 * Demande à l'utilisateur d'inscrire en console sa réponse cash.
	 * @param question la question à poser
	 */
	@Override
	public void demanderReponseCash(Question question) throws Exception {
		System.out.println("Inscrivez votre réponse");
		getQuizz().recevoirReponseCash(in.nextLine());
	}
}