/**
 *
 */
package projetQuizz;

import projetQuizz.modele.Partie;
import projetQuizz.modele.Theme;
import projetQuizz.vue.Console;
import projetQuizz.vue.InterfaceDeJeu;

/**
 * @author Flo
 * Il s'agit du controleur qui fait le lien entre l'interface de jeu et la logique de partie
 */
public class Quizz {
	enum TypeInterface{CONSOLE, GUI};
	private Partie partie;
	private InterfaceDeJeu interfaceDeJeu;

	/**
	 * Point d'entrée du programme, choix du type d'interface via les paramètre de la ligne de commande
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO définir typeInterface en fonction de args
		TypeInterface typeInterface = TypeInterface.CONSOLE;
		new Quizz(typeInterface);
	}

	/**
	 * Initialise l'état du jeu et de l'interface
	 * Lance le jeu
	 * @param typeInterface
	 */

	Quizz(TypeInterface typeInterface) {
		if (typeInterface == TypeInterface.CONSOLE) {
			interfaceDeJeu = new Console(this);
		} else {
			// interfaceDeJeu = new GUI(this);
		}
		this.partie = new Partie();
		executeActionEtatActuel();
	}

	/**
	 * Passe d'un état du jeu à un autre
	 * fait appel à l'interface
	 */

	public void executeActionEtatActuel() {
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
				interfaceDeJeu.demanderDifficulte();
				break;
			case DEMANDER_CARRE_CASH:
				interfaceDeJeu.demanderCarreCash(this.partie.getQuestionActuelle());
				break;
			case DEMANDER_REPONSE_CASH:
				interfaceDeJeu.demanderReponseCash(this.partie.getQuestionActuelle());
				break;
			case DEMANDER_REPONSE_CARRE_OU_JOKER:
				interfaceDeJeu.demanderReponseCarreJoker(this.partie.getQuestionActuelle(), this.partie.getReponsesPossiblesActuelles());
				break;
			case DEMANDER_REPONSE_MOITE_MOITE:
				interfaceDeJeu.demanderMoiteMoite(this.partie.getQuestionActuelle(), this.partie.getReponsesPossiblesActuelles());
				break;
			case JEU_FINI:
				interfaceDeJeu.afficherScores();
				break;
			}
		} //Tant que l'utilisateur n'a pas encodé ce qu'il fallait répète l'action de l'état actuel.
		catch(Exception e) {
			interfaceDeJeu.afficherErreur(e);
			executeActionEtatActuel();
		}
	}

	public void recevoirNomUtilisateur(String nom) throws Exception {
		this.partie.recevoirNomUtilisateur(nom);
		executeActionEtatActuel();
	}

	public void recevoirTheme(Theme theme) throws Exception {
		this.partie.recevoirTheme(theme);
		executeActionEtatActuel();
	}

	public void recevoirDifficulte(Partie.Difficulte difficulte) throws Exception {
		this.partie.recevoirDifficulte(difficulte);
		executeActionEtatActuel();
	}

	public void recevoirCarreCash(Partie.CarreCash carreCash) throws Exception {
		this.partie.recevoirCarreCash(carreCash);
		executeActionEtatActuel();
	}

	public void recevoirReponeCash(String reponseCash) throws Exception {
		this.partie.recevoirReponeCash(reponseCash);
		executeActionEtatActuel();
	}

	public void recevoirReponseCarreOuJoker(Partie.ReponseCarreOuJoker reponseCarreOuJoker) throws Exception {
		this.partie.recevoirReponseCarreOuJoker(reponseCarreOuJoker);
		executeActionEtatActuel();
	}

	public void recevoirReponseMoiteMoite(Partie.ReponseCarreOuJoker reponseMoiteMoite) throws Exception {
		this.partie.recevoirReponseMoiteMoite(reponseMoiteMoite);
		executeActionEtatActuel();
	}
}
