/**
 *
 */
package projetQuizz.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Collator;
import java.util.*;

/**
 * @author Flo
 *
 */
public class Partie {
    public enum CarreCash {
        CARRE, CASH
    }

    ;

    public enum Difficulte {
        FACILE, MOYEN, DIFFICILE, EXPERT
    }

    ;

    public enum Etat {
        DEMANDER_LE_NOM, DEMANDER_LE_THEME, DEMANDER_LA_DIFFCULTE, DEMANDER_CARRE_CASH, DEMANDER_REPONSE_CASH,
        DEMANDER_REPONSE_CARRE_OU_JOKER, DEMANDER_REPONSE_MOITE_MOITE, AFFICHER_RESULTAT_QUESTION, JEU_FINI
    }

    ;

    public enum Joker {
        MOITE_MOITE, SWITCH, DONNE_MON_POINT
    }

    ;

    public enum ReponseCarreOuJoker {
        UN, DEUX, TROIS, QUATRE, JOKER
    }

    ;

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
    static String url = "jdbc:mysql://localhost/projetjava";
    static String login = "root";
    static String passwd = "";

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

    private void ajouterResultat(int pointsGagnes, boolean bonneReponse) {
        this.resultats.add(new Resultat(getQuestionActuelle(), pointsGagnes, bonneReponse));
        this.etat = Partie.Etat.AFFICHER_RESULTAT_QUESTION;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public Joker[] getJokersPossibles() {
        Joker[] jokers = new Joker[jokersPossibles.size()];
        jokersPossibles.toArray(jokers);
        return jokers;
    }

    public Question getQuestionActuelle() {
        return questionsPossibles.get(0);
    }

    public Reponse[] getReponsesPossiblesActuelles() {
        Reponse[] reponses = new Reponse[reponsesPossiblesActuelles.size()];
        reponsesPossiblesActuelles.toArray(reponses);
        return reponses;
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
        _passerQuestionSuivante(false);
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
        _passerQuestionSuivante(true);
    }

    public void recevoirNomUtilisateur(Utilisateur user) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_LE_NOM, "Aucun nom n'était attendu à ce moment.");

        this.etat = Partie.Etat.DEMANDER_LE_THEME;
    }

    public void recevoirReponseCarre(Reponse reponse) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER, "Aucune réponse carré n'était attendue à ce moment.");
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

    public void setTheme(Theme theme) {
        this.theme = theme;
        this.questionsPossibles = JDBCRequests.getQuestionFromDB(theme.getId());
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

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
                // TODO afficher un message propre au joker (afficher bonne reponse).
                ajouterResultat(1, true);
                break;
        }
        jokersPossibles.remove(joker);
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
}
