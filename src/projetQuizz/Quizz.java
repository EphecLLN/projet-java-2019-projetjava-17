/**
 * 
 */
package projetQuizz;

import projetQuizz.modele.Partie;
import projetQuizz.modele.Partie.Etat;
import projetQuizz.modele.Theme;
import projetQuizz.modele.Utilisateur;
import projetQuizz.vue.Console;
import projetQuizz.vue.InterfaceDeJeu;

/**
 * @author Flo
 *
 */
public class Quizz {
	enum TypeInterface{CONSOLE, GUI};
	private Partie partie;
	private InterfaceDeJeu interfaceDeJeu;
		
	Quizz(TypeInterface typeInterface) {
		if (typeInterface == TypeInterface.CONSOLE) {
			interfaceDeJeu = new Console(this);
		} else {
			// interfaceDeJeu = new GUI(this);
		}
		this.partie = new Partie();
		partie.setEtat(Partie.Etat.DEMANDER_LE_NOM);
		etapeSuivante();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO définir typeInterface en fonction de args
		TypeInterface typeInterface = TypeInterface.CONSOLE;
		new Quizz(typeInterface);
	}
	
	public void etapeSuivante() {
		try {
			switch (partie.getEtat()) {
			case DEMANDER_LE_NOM:
				interfaceDeJeu.demanderNom();
			
				break;
			case DEMANDER_LE_THEME:
				Theme[] themes = Theme.chargerThemes();
				interfaceDeJeu.choisirTheme(themes);

			default:
				break;
			}
		}
		catch(Exception e) {
			interfaceDeJeu.afficherErreur(e);
			etapeSuivante();
		}
	}
	
	public void nomChoisi(String nom) throws Exception {
		if(partie.getEtat() != Partie.Etat.DEMANDER_LE_NOM) {
			throw new Exception("Aucun nom n'était attendu à ce moment");
		}
		partie.setUtilisateur(Utilisateur.choisirOuCreer(nom));
		partie.setEtat(Partie.Etat.DEMANDER_LE_THEME);
		etapeSuivante();
	}
	
	public void themeChoisi(Theme theme) throws Exception {
		if(partie.getEtat() != Partie.Etat.DEMANDER_LE_THEME) {
			throw new Exception("Aucun thème n'était attendu à ce moment");
		}
		partie.setTheme(theme);
		partie.setEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE);
		etapeSuivante();
	}
	

}
