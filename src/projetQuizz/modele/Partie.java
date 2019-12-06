/**
 *
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Partie {
	public enum CarreCash {CARRE, CASH};
	public enum Etat {DEMANDER_LE_NOM, DEMANDER_LE_THEME, DEMANDER_LA_DIFFCULTE, DEMANDER_CARRE_CASH, DEMANDER_REPONSE_CASH, DEMANDER_REPONSE_CARRE_OU_JOKER, DEMANDER_REPONSE_MOITE_MOITE, JEU_FINI};
	private String difficulteChoisie;
	public enum ReponseCarreOuJoker {UN, DEUX, TROIS, QUATRE, JOKER};
	private Theme theme;
	private Utilisateur utilisateur;
	private Etat etat = Etat.DEMANDER_LE_NOM;

	public Etat getEtat() {
		return this.etat;
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

	public Question getQuestionActuelle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Reponse getReponsesPossiblesActuelles() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDifficulteChoisie(String difficulte) {
		// TODO Auto-generated method stub
		this.difficulteChoisie = difficulte;
	}

	public boolean verifierReponseCash(String reponseCash) {
		// TODO A remplir comparer la reponse donné par l'utilisateur à la bonne réponse de la questionActuelle();

		return true;
	}

	public boolean estCeQueLeJeuEstFiniApresEtatActuel() {

		return true;
	}

	public boolean verifierReponseCarre(ReponseCarreOuJoker reponseCarreOuJoker) {
		// TODO Auto-generated method stub
		return true;
	}
	public void recevoirNomUtilisateur(String nom) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LE_NOM, "Aucun nom n'était attendu à ce moment.");
		setUtilisateur(Utilisateur.choisirOuCreer(nom));
		this.etat = Partie.Etat.DEMANDER_LE_THEME;
	}

	public void recevoirTheme(Theme theme) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LE_THEME, "Aucun thème n'était attendu à ce moment.");
		setTheme(theme);
		this.etat = Partie.Etat.DEMANDER_LA_DIFFCULTE;
	}

	public void recevoirDifficulte(String difficulte) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE, "Aucune difficulté n'était attendue à ce moment.");
		setDifficulteChoisie(difficulte);
		//TODO Traitement Jokers
		for (int i = 0;i<2;i++) {
			getQuestionActuelle();
			this.etat = Partie.Etat.DEMANDER_CARRE_CASH;
			
		}
	}

	public void recevoirCarreCash(Partie.CarreCash carreCash) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_CARRE_CASH, "Aucun carre ou cash n'était attendu à ce moment.");
		if (carreCash == Partie.CarreCash.CASH) {
			this.etat = Partie.Etat.DEMANDER_REPONSE_CASH;
		}
		else {
			this.etat = Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER;
		}
	}

	public void recevoirReponeCash(String reponseCash) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_CASH, "Aucune reponse cash n'était attendue à ce moment.");
		verifierReponseCash(reponseCash);
		//TODO a faire
		this.etat = Partie.Etat.DEMANDER_LA_DIFFCULTE;
	}

	public void recevoirReponseCarreOuJoker(Partie.ReponseCarreOuJoker reponseCarreOuJoker) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER, "Aucune réponse carré ou joker n'était attendu à ce moment.");
		//TODO A FAIRE
		verifierReponseCarre(reponseCarreOuJoker);
		this.etat = Partie.Etat.DEMANDER_LA_DIFFCULTE;
	}

	public void recevoirReponseMoiteMoite(Partie.ReponseCarreOuJoker reponseMoiteMoite) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_MOITE_MOITE, "Aucun réponse Moite/Moite n'était attendue à ce moment.");
		//TODO A FAIRE
		this.etat = Partie.Etat.DEMANDER_LA_DIFFCULTE;
	}
	private void verifierEtat(Partie.Etat etat, String messageErreur) throws Exception {
		if(this.etat != etat) {
			throw new Exception(messageErreur);
		}
	}


}
