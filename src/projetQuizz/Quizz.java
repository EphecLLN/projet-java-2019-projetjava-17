/**
 *
 */
package projetQuizz;

import projetQuizz.modele.Partie;
import projetQuizz.modele.Partie.Difficulte;
import projetQuizz.modele.Partie.Joker;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Theme;
import projetQuizz.vue.Console;
import projetQuizz.vue.InterfaceDeJeu;

/**
 * @author Flo Il s'agit du controleur qui fait le lien entre l'interface de jeu
 *         et la logique de partie
 */
public class Quizz {
	enum TypeInterface {
		CONSOLE, GUI
	};

	/**
	 * Point d'entrée du programme, choix du type d'interface via les paramètre de
	 * la ligne de commande
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO définir typeInterface en fonction de args

		try { // Chargement du driver JDBC pour utilisation de la db
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		TypeInterface typeInterface = TypeInterface.CONSOLE;
		new Quizz(typeInterface);
	}

	private Partie partie;

	private InterfaceDeJeu interfaceDeJeu;

	/**
	 * Initialise l'état du jeu et de l'interface Lance le jeu
	 *
	 * @param typeInterface
	 * @throws Exception
	 */

	Quizz(TypeInterface typeInterface) throws Exception {
		if (typeInterface == TypeInterface.CONSOLE) {
			interfaceDeJeu = new Console(this);
		} else {
			// interfaceDeJeu = new GUI(this);
		}
		this.partie = new Partie();
		executeActionEtatActuel();
	}

	/**
	 * Passe d'un état du jeu à un autre fait appel à l'interface
	 *
	 * @throws Exception
	 */

	public void executeActionEtatActuel() throws Exception {
		try {
			switch (partie.getEtat()) {
			case DEMANDER_LE_NOM:
				interfaceDeJeu.demanderNom();
				break;
			case DEMANDER_LE_THEME:
				Theme[] themes = Theme.chargerThemes();
				interfaceDeJeu.choisirTheme(themes);
				break;
			case DEMANDER_LA_DIFFCULTE:
				Difficulte[] difficulte = { Difficulte.FACILE, Difficulte.MOYEN, Difficulte.DIFFICILE,
						Difficulte.EXPERT };
				interfaceDeJeu.demanderDifficulte(difficulte);
				break;
			case DEMANDER_CARRE_CASH:
				interfaceDeJeu.demanderCarreCash(this.partie.getQuestionActuelle());
				break;
			case DEMANDER_REPONSE_CASH:
				interfaceDeJeu.demanderReponseCash();
				break;
			case DEMANDER_REPONSE_CARRE_OU_JOKER:
				interfaceDeJeu.demanderReponseCarreJoker(this.partie.getReponsesPossiblesActuelles(),
						partie.getJokersPossibles());
				break;
			case DEMANDER_REPONSE_MOITE_MOITE:
				interfaceDeJeu.demanderMoiteMoite(this.partie.getReponsesPossiblesActuelles());
				break;
			case AFFICHER_RESULTAT_QUESTION:
				interfaceDeJeu.afficherResultat(this.partie.getResultat());
				break;
			case JEU_FINI:
				interfaceDeJeu.afficherScores(this.partie.getResultats());
				break;
			}
		} // Tant que l'utilisateur n'a pas encodé ce qu'il fallait répète l'action de
			// l'état actuel.
		catch (QuizzException e) {
			interfaceDeJeu.afficherErreur(e);
			executeActionEtatActuel();
		}
	}

	public void passerQuestionSuivante() throws Exception {
		partie.passerQuestionSuivante();
		executeActionEtatActuel();
	}

	public void recevoirCarreCash(Partie.CarreCash carreCash) throws Exception {
		this.partie.recevoirCarreCash(carreCash);
		executeActionEtatActuel();
	}

	public void recevoirDifficulte(Difficulte difficulte) throws Exception {
		this.partie.recevoirDifficulte(difficulte);
		executeActionEtatActuel();
	}

	public void recevoirJoker(Joker joker) throws Exception {
		this.partie.utiliserJoker(joker);
		executeActionEtatActuel();
	}

	public void recevoirNomUtilisateur(String nom) throws Exception {
		this.partie.recevoirNomUtilisateur(nom);
		executeActionEtatActuel();
	}

	public void recevoirReponseCarre(Reponse reponse) throws Exception {
		this.partie.recevoirReponseCarre(reponse);
		executeActionEtatActuel();
	}

	public void recevoirReponseCash(String reponseCash) throws Exception {
		this.partie.recevoirReponseCash(reponseCash);
		executeActionEtatActuel();
	}

	public void recevoirReponseMoiteMoite(Reponse reponse) throws Exception {
		this.partie.recevoirReponseMoiteMoite(reponse);
		executeActionEtatActuel();
	}

	public void recevoirTheme(Theme theme) throws Exception {
		this.partie.recevoirTheme(theme);
		executeActionEtatActuel();
	}

}
