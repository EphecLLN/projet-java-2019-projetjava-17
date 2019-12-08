/**
 *
 */
package projetQuizz.modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Flo
 *
 */
public class Partie {
	public enum CarreCash {
		CARRE, CASH
	};

	public enum Difficulte {
		FACILE, MOYEN, DIFFICILE, EXPERT
	};

	public enum Etat {
		DEMANDER_LE_NOM, DEMANDER_LE_THEME, DEMANDER_LA_DIFFCULTE, DEMANDER_CARRE_CASH, DEMANDER_REPONSE_CASH,
		DEMANDER_REPONSE_CARRE_OU_JOKER, DEMANDER_REPONSE_MOITE_MOITE, AFFICHER_RESULTAT_QUESTION, JEU_FINI
	};

	public enum ReponseCarreOuJoker {
		UN, DEUX, TROIS, QUATRE, JOKER
	};

	public static String getNomDifficulte(Difficulte difficulte) {
		switch (difficulte) {
		case FACILE:
			return "Facile";
		case MOYEN:
			return "Moyen";
		case DIFFICILE:
			return "Difficile";
		case EXPERT:
			return "Expert";
		}
		return null;
	}

	private Theme theme;
	private Utilisateur utilisateur;
	private Etat etat = Etat.DEMANDER_LE_NOM;
	private Difficulte difficulte;
	private List<Question> questionsPossibles;
	private int compteurQuestionsPosees = 0;

	private List<Resultat> resultats = new ArrayList<Resultat>();

	private void ajouterResultat(int pointsGagnes, boolean bonneReponse) {
		resultats.add(new Resultat(getQuestionActuelle(), pointsGagnes, bonneReponse));
		this.etat = Partie.Etat.AFFICHER_RESULTAT_QUESTION;
	}

	public Etat getEtat() {
		return this.etat;
	}

	public Question getQuestionActuelle() {
		return questionsPossibles.get(0);
	}

	public Reponse[] getReponsesPossiblesActuelles() {
		return getQuestionActuelle().getReponses();
	}

	public Resultat getResultat() {
		return resultats.get(resultats.size() - 1);
	}

	public Resultat[] getResultats() {
		Resultat[] resultats = new Resultat[this.resultats.size()];
		this.resultats.toArray(resultats);
		return resultats;
	}

	public Theme getTheme() {
		return theme;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void passerQuestionSuivante() throws Exception {
		verifierEtat(Partie.Etat.AFFICHER_RESULTAT_QUESTION,
				"Impossible de passer à la question suivante en ce moment.");
		questionsPossibles.remove(0);
		compteurQuestionsPosees++;
		if (compteurQuestionsPosees == 10 || questionsPossibles.isEmpty()) {
			this.etat = Partie.Etat.JEU_FINI;
		} else {
			this.etat = Partie.Etat.DEMANDER_CARRE_CASH;

		}
	}

	public void recevoirCarreCash(Partie.CarreCash carreCash) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_CARRE_CASH, "Aucun carre ou cash n'était attendu à ce moment.");
		if (carreCash == Partie.CarreCash.CASH) {
			this.etat = Partie.Etat.DEMANDER_REPONSE_CASH;
		} else {
			this.etat = Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER;
		}
	}

	public void recevoirDifficulte(Difficulte difficulte) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE, "Aucune difficulté n'était attendue à ce moment.");
		setDifficulte(difficulte);
		this.etat = Partie.Etat.DEMANDER_CARRE_CASH;
	}

	public void recevoirNomUtilisateur(String nom) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LE_NOM, "Aucun nom n'était attendu à ce moment.");
		setUtilisateur(Utilisateur.choisirOuCreer(nom));
		this.etat = Partie.Etat.DEMANDER_LE_THEME;
	}

	public void recevoirReponseCarre(Reponse reponse) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER,
				"Aucune réponse carré ou joker n'était attendu à ce moment.");
		boolean bonneReponse = verifierReponseCarre(reponse);
		int pointsGagnes = bonneReponse ? 1 : 0;
		ajouterResultat(pointsGagnes, bonneReponse);
	}

	public void recevoirReponseCash(String reponseCash) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_CASH, "Aucune reponse cash n'était attendue à ce moment.");
		boolean bonneReponse = verifierReponseCash(reponseCash);
		int pointsGagnes = bonneReponse ? 3 : 0;
		ajouterResultat(pointsGagnes, bonneReponse);
	}

	public void recevoirReponseMoiteMoite(Reponse reponse) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_MOITE_MOITE,
				"Aucun réponse Moite/Moite n'était attendue à ce moment.");
		boolean bonneReponse = verifierReponseCarre(reponse);
		int pointsGagnes = bonneReponse ? 1 : 0;
		ajouterResultat(pointsGagnes, bonneReponse);

	}

	public void recevoirTheme(Theme theme) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LE_THEME, "Aucun thème n'était attendu à ce moment.");
		setTheme(theme);
		this.etat = Partie.Etat.DEMANDER_LA_DIFFCULTE;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
		questionsPossibles = new ArrayList<Question>(Arrays.asList(theme.getQuestions()));
		Collections.shuffle(questionsPossibles);
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	private void verifierEtat(Partie.Etat etat, String messageErreur) throws Exception {
		if (this.etat != etat) {
			throw new Exception(messageErreur);
		}
	}

	private boolean verifierReponseCarre(Reponse reponse) {
		return reponse.getEstBonneReponse();
	}

	private boolean verifierReponseCash(String reponseCash) {
		Reponse[] reponses = getReponsesPossiblesActuelles();
		for (int i = 0; i < reponses.length; i++) {
			// TODO améliorer la détection
			if (reponses[i].getEstBonneReponse() && reponseCash.contentEquals(reponses[i].getReponse())) {
				return true;
			}
		}
		return false;
	}
}
