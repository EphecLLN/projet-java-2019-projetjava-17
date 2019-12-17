package projetQuizz;

import java.util.ArrayList;

import projetQuizz.modele.JDBCRequests;
import projetQuizz.modele.Partie;
import projetQuizz.modele.Partie.Difficulte;
import projetQuizz.modele.Partie.Joker;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Theme;
import projetQuizz.vue.Console;
import projetQuizz.vue.GUI;
import projetQuizz.vue.InterfaceDeJeu;

/**
 *	Il s'agit du controleur qui fait le lien entre l'interface de jeu et la logique de partie
 */
public class Quizz {
	enum TypeInterface {
		CONSOLE, GUI
	};

	/**
	 * Point d'entrée du programme, choix du type d'interface via les paramètres de la ligne de commande.
	 * @param args va définir le type d'interface.
	 * @throws Exception Si erreur dans l'etat.
	 */
	public static void main(String[] args) throws Exception {
		// Chargement du driver JDBC pour utilisation de la db
		Class.forName("com.mysql.jdbc.Driver");
		TypeInterface typeInterface = TypeInterface.CONSOLE;
		if (args.length > 0 && args[0].contains("gui")) {
			typeInterface = TypeInterface.GUI;
		}
		new Quizz(typeInterface);
	}

	private Partie partie;

	private InterfaceDeJeu interfaceDeJeu;

	/**
	 * Initialise l'état du jeu et de l'interface Lance le jeu
	 * @param typeInterface type d'interface choisie
	 * @throws Exception Si erreur dans l'etat.
	 */

	Quizz(TypeInterface typeInterface) throws Exception {
		if (typeInterface == TypeInterface.CONSOLE) {
			interfaceDeJeu = new Console(this);
		} else {
			interfaceDeJeu = new GUI(this);
		}
		this.partie = new Partie();
		executeActionEtatActuel();
	}

	/**
	 * Passe d'un état du jeu à un autre fait appel à l'interface.
	 * @throws Exception Si erreur dans l'etat.
	 */

	public void executeActionEtatActuel() throws Exception {
		try {
			switch (partie.getEtat()) {
			case DEMANDER_LE_NOM:
				interfaceDeJeu.demanderNom();
				break;
			case DEMANDER_LE_THEME:
				ArrayList<Theme> themes = Theme.chargerThemes();
				interfaceDeJeu.choisirTheme(themes);
				break;
			case DEMANDER_LA_DIFFCULTE:
				Difficulte[] difficulte = { Difficulte.FACILE, Difficulte.MOYEN, Difficulte.DIFFICILE,
						Difficulte.EXPERT };
				interfaceDeJeu.demanderDifficulte(difficulte);
				break;
			case DEMANDER_CARRE_CASH:
				interfaceDeJeu.demanderCarreCash(partie.getQuestionActuelle());
				break;
			case DEMANDER_REPONSE_CASH:
				interfaceDeJeu.demanderReponseCash(partie.getQuestionActuelle());
				break;
			case DEMANDER_REPONSE_CARRE_OU_JOKER:
				interfaceDeJeu.demanderReponseCarreJoker(partie.getQuestionActuelle(),
						partie.getReponsesPossiblesActuelles(), partie.getJokersPossibles());
				break;
			case DEMANDER_REPONSE_MOITE_MOITE:
				interfaceDeJeu.demanderMoiteMoite(partie.getQuestionActuelle(), partie.getReponsesPossiblesActuelles());
				break;
			case AFFICHER_RESULTAT_QUESTION:
				interfaceDeJeu.afficherResultat(partie.getResultat());
				break;
			case JEU_FINI:
				JDBCRequests.insertPartieResult(partie);
				interfaceDeJeu.afficherScores(partie);
				break;
			}
		}
		// Tant que l'utilisateur n'a pas encodé ce qu'il fallait répète l'action de l'état actuel.
		catch (QuizzException e) {
			interfaceDeJeu.afficherErreur(e);
			executeActionEtatActuel();
		}
	}

	// ---METHODES GERANT LE DEROULEMENT DE LA PARTIE EN FAISANT LE LIEN ENTRE LES METHODES DU MODELE ET LES METHODES DE LA VUE---

	public void passerQuestionSuivante() throws Exception {
		partie.passerQuestionSuivante();
		executeActionEtatActuel();
	}

	public void recevoirCarreCash(Partie.CarreCash carreCash) throws Exception {
		partie.recevoirCarreCash(carreCash);
		executeActionEtatActuel();
	}

	public void recevoirDifficulte(Difficulte difficulte) throws Exception {
		partie.recevoirDifficulte(difficulte);
		executeActionEtatActuel();
	}

	public void recevoirJoker(Joker joker) throws Exception {
		partie.utiliserJoker(joker);
		executeActionEtatActuel();
	}

	public void recevoirNomUtilisateur(String nom) throws Exception {
		partie.recevoirNomUtilisateur(nom);
		executeActionEtatActuel();
	}

	public void recevoirReponseCarre(Reponse reponse) throws Exception {
		partie.recevoirReponseCarre(reponse);
		executeActionEtatActuel();
	}

	public void recevoirReponseCash(String reponseCash) throws Exception {
		partie.recevoirReponseCash(reponseCash);
		executeActionEtatActuel();
	}

	public void recevoirTheme(Theme theme) throws Exception {
		partie.recevoirTheme(theme);
		executeActionEtatActuel();
	}
}
