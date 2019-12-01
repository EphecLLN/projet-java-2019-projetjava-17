/**
 * 
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Partie {
	public enum Etat {DEMANDER_LE_NOM, DEMANDER_LE_THEME, DEMANDER_LA_DIFFCULTE};
	private Theme theme;
	private Utilisateur utilisateur;
	private Etat etat;
	
	

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Theme getTheme() {
		return theme;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
}
