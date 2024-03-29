package projetQuizz.modele;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import projetQuizz.QuizzException;


/**
 *	Contient la logique de programmation du quiz.
 */
public class Partie {
	public enum CarreCash {
		CARRE, CASH
	}

	public enum Difficulte {
		FACILE, MOYEN, DIFFICILE, EXPERT
	}

	public enum Etat {
		DEMANDER_LE_NOM, DEMANDER_LE_THEME, DEMANDER_LA_DIFFCULTE, DEMANDER_CARRE_CASH, DEMANDER_REPONSE_CASH,
		DEMANDER_REPONSE_CARRE_OU_JOKER, DEMANDER_REPONSE_MOITE_MOITE, AFFICHER_RESULTAT_QUESTION, JEU_FINI
	}

	public enum Joker {
		MOITE_MOITE, SWITCH, DONNE_MON_POINT
	}

	public enum ReponseCarreOuJoker {
		UN, DEUX, TROIS, QUATRE, JOKER
	}

	public static Map<String, String> Case = Map.of(
			"à", "a",
			"é", "e",
			"è", "e",
			"â", "a",
			"ê", "e",
			"ç", "c",
			"ô", "o",
			"ö", "o",
			"ù", "u",
			"û", "u");

	/**
	 * Getter de nomDifficulte
	 * @param difficulte la difficulté en cours
	 * @return : String de la difficulté choisie
	 */
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

	/**
	 * Getter de jokers.
	 * @param joker le joker choisi
	 * @return : String du joker choisi
	 */
	public static String getNomJokers(Joker joker) {
		switch (joker) {
		case MOITE_MOITE:
			return "Moite/moite";
		case SWITCH:
			return "Switch";
		case DONNE_MON_POINT:
			return "Donne mon point";
		}
		return null;
	}

	private Theme theme;
	private Utilisateur utilisateur;
	private Etat etat = Etat.DEMANDER_LE_NOM;
	private Difficulte difficulte;
	private List<Reponse> reponsesPossiblesActuelles;
	private List<Question> questionsPossibles = new ArrayList<Question>();
	private List<Resultat> resultats = new ArrayList<Resultat>();
	private List<Joker> jokersPossibles = new ArrayList<Joker>();
	public static String url = "jdbc:mysql://localhost/projetjava";
	public static String login = "root";
	public static String passwd = "";

	/**
	 * Passe à l'état suivant en en fonction de l'avancement du quizz.
	 * @param premiereQuestion : boolean.
	 */
	private void _passerQuestionSuivante(boolean premiereQuestion) {
		if (!premiereQuestion) {
			questionsPossibles.remove(0);
		}
		if (this.resultats.size() == 10 || questionsPossibles.isEmpty()) {
			this.etat = Partie.Etat.JEU_FINI;
		} else {
			this.reponsesPossiblesActuelles = getQuestionActuelle().getReponses();
			this.etat = Partie.Etat.DEMANDER_CARRE_CASH;
		}
	}

	/**
	 * Vérifie si l'utilisateur a donné une bonne réponse, si c'est le cas enregistre le score marqué dans résultat et passe à l'état suivant.
	 * @param pointsGagnes: Le nombre de point que l'utilisateur a marqué.
	 * @param bonneReponse: boolean qui vérifie si la réponse est bonne.
	 */
	private void ajouterResultat(int pointsGagnes, boolean bonneReponse) {
		this.resultats.add(new Resultat(getQuestionActuelle(), pointsGagnes, bonneReponse));
		this.etat = Partie.Etat.AFFICHER_RESULTAT_QUESTION;
	}

	/**
	 * Getter de etat.
	 * @return l'état
	 */
	public Etat getEtat() {
		return this.etat;
	}

	/**
	 * Getter de jokersPossible.
	 * @return Array des jokers encore disponibles
	 */
	public Joker[] getJokersPossibles() {
		Joker[] jokers = new Joker[jokersPossibles.size()];
		jokersPossibles.toArray(jokers);
		return jokers;
	}

	/**
	 * Getter de questionActuelle.
	 * @return la question actuelle
	 */
	public Question getQuestionActuelle() {
		return questionsPossibles.get(0);
	}

	/**
	 * Getter de reponsesPossiblesActuelles.
	 * @return un array des Reponses possibles actuelles
	 */
	public Reponse[] getReponsesPossiblesActuelles() {
		Reponse[] reponses = new Reponse[reponsesPossiblesActuelles.size()];
		reponsesPossiblesActuelles.toArray(reponses);
		return reponses;
	}

	/**
	 * Getter de resultat.
	 * @return le dernier résultat disponible
	 */
	public Resultat getResultat() {
		return resultats.get(resultats.size() - 1);
	}

	/**
	 * Getter de resultats.
	 * @return un Array des résultat disponibles
	 */
	public Resultat[] getResultats() {
		Resultat[] resultats = new Resultat[this.resultats.size()];
		this.resultats.toArray(resultats);
		return resultats;
	}

	/**
	 * Getter de theme.
	 * @return le theme
	 */
	public Theme getTheme() {
		return theme;
	}

	/**
	 * Getter de difficulte.
	 * @return la difficulte
	 */
	public Difficulte getDifficulte(){
		return this.difficulte;
	}

	/**
	 * Getter de utilisateur.
	 * @return l'utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * Permet de passer à la question suivante.
	 * @throws Exception Si erreur dans l'etat.
	 */
	public void passerQuestionSuivante() throws Exception {
		verifierEtat(Partie.Etat.AFFICHER_RESULTAT_QUESTION,
				"Impossible de passer à la question suivante en ce moment.");
		_passerQuestionSuivante(false);
	}

	/**
	 * Passe à l'état suivant en fonction du choix de l'utilisateur.
	 * @param carreCash : choix de l'utilisateur entre carré et cash.
	 * @throws Exception Si erreur dans l'etat.
	 */
	public void recevoirCarreCash(Partie.CarreCash carreCash) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_CARRE_CASH, "Aucun carre ou cash n'était attendu à ce moment.");
		if (carreCash == Partie.CarreCash.CASH) {
			this.etat = Partie.Etat.DEMANDER_REPONSE_CASH;
		} else {
			this.etat = Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER;
		}
	}

	/**
	 * Récupère le choix de difficulté et lance la première question.
	 * @param difficulte choix de difficulté choisie par l'utilisateur
	 * @throws Exception Si erreur dans l'etat.
	 */
	public void recevoirDifficulte(Difficulte difficulte) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE, "Aucune difficulté n'était attendue à ce moment.");
		setDifficulte(difficulte);
		_passerQuestionSuivante(true);
	}

	/**
	 * Récupère le nom de l'utilisateur et passe à l'état suivant.
	 * @param nom le nom de l'utilisateur reçu
	 * @throws Exception Si erreur dans l'etat ou nom vide.
	 */
	public void recevoirNomUtilisateur(String nom) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LE_NOM, "Aucun nom n'était attendu à ce moment.");
		if (nom.isEmpty()) {
			throw new QuizzException("Un nom doit être donné.");
		}
		this.utilisateur = JDBCRequests.checkUserIdentity(nom);
		this.etat = Partie.Etat.DEMANDER_LE_THEME;
	}

	/**
	 * Récupère la réponse carré que l'utilisateur à encoder, vérifie si elle est bonne, le cas échéant ajoute 1 point au résultat.
	 * @param reponse : la réponse encodée par l'utilisateur.
	 * @throws Exception Si erreur dans l'etat.
	 */
	public void recevoirReponseCarre(Reponse reponse) throws Exception {
		try {
			verifierEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER, "Aucune réponse carré n'était attendue à ce moment.");
		} catch (Exception e) {
			verifierEtat(Partie.Etat.DEMANDER_REPONSE_MOITE_MOITE, "Aucun réponse Moite/Moite n'était attendue à ce moment.");
		}
		boolean bonneReponse = verifierReponseCarre(reponse);
		int pointsGagnes = bonneReponse ? 1 : 0;
		ajouterResultat(pointsGagnes, bonneReponse);
	}

	/**
	 * Récupère la réponse cash que l'utilisateur à encoder, vérifie si elle est bonne, le cas échéant ajoute 3 points au résultat.
	 * @param reponseCash : la réponse encodée par l'utilisateur.
	 * @throws Exception Si erreur dans l'etat.
	 */
	public void recevoirReponseCash(String reponseCash) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_CASH, "Aucune reponse cash n'était attendue à ce moment.");
		boolean bonneReponse = verifierReponseCash(reponseCash);
		int pointsGagnes = bonneReponse ? 3 : 0;
		ajouterResultat(pointsGagnes, bonneReponse);
	}

	/**
	 * Définit le thème en fonction du choix de l'utilisateur, passe ensuite à l'état suivant.
	 * @param theme : thème choisi par l'utilisateur.
	 * @throws Exception Si erreur dans l'etat.
	 */
	public void recevoirTheme(Theme theme) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_LE_THEME, "Aucun thème n'était attendu à ce moment.");
		setTheme(theme);
		this.etat = Partie.Etat.DEMANDER_LA_DIFFCULTE;
	}

	/**
	 * Setter de difficulte.
	 * @param difficulte la difficulte choisie
	 */
	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
		switch (this.difficulte) {
		case FACILE:
			jokersPossibles.add(Joker.DONNE_MON_POINT);
		case MOYEN:
			jokersPossibles.add(Joker.SWITCH);
		case DIFFICILE:
			jokersPossibles.add(Joker.MOITE_MOITE);
		default:
			break;
		}
	}

	/**
	 * Setter de theme.
	 *@param theme le thème choisi
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
		this.questionsPossibles = JDBCRequests.getQuestionFromDB(theme.getId());
	}

	/**
	 * Setter d'utilisateur.
	 * @param utilisateur l'utilisateur choisi
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * Transmet le message de fin
	 * @return le message de fin de partie
	 */
	public String getTexteDeFin() {
		String texte = "";

		Date today = Date.valueOf(LocalDate.now());
		int[] resultat = calculScore();
		texte += "Votre score final est de " + resultat[2] + " point(s) avec " + resultat[0]
				+ " bonne(s) réponse(s) et " + resultat[1] + " mauvaise(s) réponse(s).\n";

		try {
			ResultSet rank = JDBCRequests.showCurrentRankTheme(resultat[0], getTheme().getId());
			ResultSet top = JDBCRequests.showTopTenTheme(getTheme().getId(), Partie.getNomDifficulte(getDifficulte()));

			texte += "Top 10 du thème " + JDBCRequests.getThemeNameById(getTheme().getId()) + " en difficulte " + getDifficulte() + "\n\n";
			texte += "\tTOP\t|\tUser\t\t|\tDate\t\t|\tScore\t\n";
			while (top.next()) {
				texte += "\t"+top.getInt("ROW_NUMBER() OVER (ORDER BY partie_score DESC)")+
						"\t|\t"+JDBCRequests.getUserNameById(top.getInt("utilisateur_id"))+"\t|\t"+top.getDate("dateEtHeure")+"\t|\t"+top.getInt("partie_score")+"\n\n";
			};
			while(rank.next()) {
				if (rank.getInt("partie_score") == resultat[2] && rank.getDate("dateEtHeure").before(today)) {
					texte += "Votre partie a atteint le rang "
							+ rank.getInt("ROW_NUMBER() OVER (ORDER BY partie_score DESC)")
							+ " avec un score de " + rank.getInt("partie_score") + " points\n\n";
					break;
				}
			}
			Connection connection = DriverManager.getConnection(Partie.url, Partie.login, Partie.passwd);
			Statement statement = connection.createStatement();
			connection.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return texte;
	}

	/**
	 * action effectuée par le programme en fonction du joker choisi par l'utilisateur.
	 * @param joker : joker demandé par l'utilisateur
	 * @throws Exception Si erreur dans l'etat.
	 */
	public void utiliserJoker(Joker joker) throws Exception {
		verifierEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER, "Aucun joker n'était attendu à ce moment.");
		if (!jokersPossibles.contains(joker)) {
			throw new Exception("Ce joker n'est pas disponible.");
		}
		switch (joker) {
		case MOITE_MOITE:
			while (reponsesPossiblesActuelles.size() > 2) {
				int index = (int) (Math.random() * (reponsesPossiblesActuelles.size() - 1));
				if (!reponsesPossiblesActuelles.get(index).getEstBonneReponse()) {
					reponsesPossiblesActuelles.remove(index);
				}
			}
			etat = Partie.Etat.DEMANDER_REPONSE_MOITE_MOITE;
			break;
		case SWITCH:
			Collections.shuffle(questionsPossibles);
			_passerQuestionSuivante(true);
			break;
		case DONNE_MON_POINT:
			ajouterResultat(1, true);
			break;
		}
		jokersPossibles.remove(joker);
	}

	/**
	 * Vérification qu'on est bien dans l'état attendu.
	 * @param etat : etat actuel.
	 * @param messageErreur : message d'erreur.
	 * @throws Exception Si erreur dans l'etat.
	 */
	private void verifierEtat(Partie.Etat etat, String messageErreur) throws Exception {
		if (this.etat != etat) {
			throw new Exception(messageErreur);
		}
	}

	/**
	 * Vérifie si la réponse carré encodée est la bonne.
	 * @param reponse : réponse donnée par l'utilisateur.
	 * @return : boolean
	 */
	private boolean verifierReponseCarre(Reponse reponse) {
		return reponse.getEstBonneReponse();
	}

	/**
	 * Vérifie si la réponse cash encodée est la bonne.
	 * @param reponseCash : réponse donnée par l'utilisateur.
	 * @return : boolean
	 */
	private boolean verifierReponseCash(String reponseCash) {
		Reponse[] reponses = getReponsesPossiblesActuelles();
		for (int i = 0; i < reponses.length; i++) {
			if (JDBCRequests.isNum(reponseCash)) {
				if (reponses[i].getEstBonneReponse() && reponses[i].getReponse().compareTo(reponseCash) == 0) {
					return true;
				}
			}
			if (reponses[i].getEstBonneReponse() && JDBCRequests.simpleCase((reponses[i].getReponse()).toLowerCase()).contains(JDBCRequests.simpleCase(reponseCash).toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Permet de mettre en string sans case et sans accent un string reçu.
	 * @param s : String reçu
	 * @return newString : String sans accent
	 */
	public static String simpleCase (String s) {
		String newString = "";
		for (String str : s.split("")) {
			String c = Case.get(str);
			if (c != null) {
				newString += c;
			} else {
				newString += str;
			}
		}
		return newString;
	}

	/**
	 * Vérifie si le paramètre sous forme de string est un nombre.
	 * @param strNum : String dont on veut vérifier si c'est un nombre ou pas.
	 * @return boolean.
	 */
	public static boolean isNum (String strNum) {
		boolean ret = true;
		try {

			Double.parseDouble(strNum);

		}catch (NumberFormatException e) {
			ret = false;
		}
		return ret;
	}

	/**
	 * Calcule le score de la partie sur base du tableau des résultat
	 * @return le score de la partie
	 */
	public int[] calculScore() {
		Resultat[] resultats = this.getResultats();
		int[] score = {0,0,0};
		int nbBonnesReponses = 0;
		int nbMauvaisesReponses = 0;
		for (int i = 0; i < resultats.length; i++) {
			if (resultats[i].getEstBonneReponse()) {
				score[0]++;
			} else {
				score[1]++;
			}
			score[2] += resultats[i].getScore();
		}
		return score;
	}

	//Méthode utile pour les test

	/**
	 * Setter de l'état
	 * @param etat l'état dans lequel le programme doit rentrer
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	/**
	 * Setter des questions possibles
	 * @param questions les questions à insérer
	 */
	public void setQuestionsPossibles (ArrayList<Question> questions) {
		this.questionsPossibles = questions;
	}

	public void setReponsesPossiblesActuelles(ArrayList<Reponse> reponses) {this.reponsesPossiblesActuelles = reponses;}

	public void setResultats(ArrayList<Resultat> resultats) {this.resultats = resultats;}
}
