package projetQuizz.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Collator;
import java.util.*;

/**
 *	Contient la logique de programmation du quiz.
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
    
    /**
     * Getter de nomDifficulte
     * @param difficulte
     * @return
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
     * @param joker.
     * @return
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
    static String url = "jdbc:mysql://localhost/projetjava";
    static String login = "root";
    static String passwd = "";
    
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
     */
    public Etat getEtat() {
        return this.etat;
    }

    /**
     * Getter de jokersPossible.
     */
    public Joker[] getJokersPossibles() {
        Joker[] jokers = new Joker[jokersPossibles.size()];
        jokersPossibles.toArray(jokers);
        return jokers;
    }
    
    /**
     * Getter de questionActuelle.
     */
    public Question getQuestionActuelle() {
        return questionsPossibles.get(0);
    }
    
    /**
     * Getter de reponsesPossiblesActuelles.
     */
    public Reponse[] getReponsesPossiblesActuelles() {
        Reponse[] reponses = new Reponse[reponsesPossiblesActuelles.size()];
        reponsesPossiblesActuelles.toArray(reponses);
        return reponses;
    }
    
    /**
     * Getter de resultat.
     */
    public Resultat getResultat() {
        return resultats.get(resultats.size() - 1);
    }
    
    /**
     * Getter de resultats.
     */
    public Resultat[] getResultats() {
        Resultat[] resultats = new Resultat[this.resultats.size()];
        this.resultats.toArray(resultats);
        return resultats;
    }
    
    /**
     * Getter de theme.
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Getter de difficulte.
     */
    public Difficulte getDifficulte(){
        return this.difficulte;
    }

   /**
    * Getter de utilisateur.
    */

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    /**
     * Permet de passer à la question suivante.
     * @throws Exception
     */
    public void passerQuestionSuivante() throws Exception {
        verifierEtat(Partie.Etat.AFFICHER_RESULTAT_QUESTION,
                "Impossible de passer à la question suivante en ce moment.");
        _passerQuestionSuivante(false);
    }
    
	/**
	 * Passe à l'état suivant en fonction du choix de l'utilisateur.
	 * @param carreCash : choix de l'utilisateur entre carré et cash.
	 * @throws Exception
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
     * @throws Exception
     */
    public void recevoirDifficulte(Difficulte difficulte) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE, "Aucune difficulté n'était attendue à ce moment.");
        setDifficulte(difficulte);
        _passerQuestionSuivante(true);
    }
    
    /**
     * Récupère le nom de l'utilisateur et passe à l'état suivant.
     * @param nom
     * @throws Exception
     */
    public void recevoirNomUtilisateur(Utilisateur user) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_LE_NOM, "Aucun nom n'était attendu à ce moment.");

        this.etat = Partie.Etat.DEMANDER_LE_THEME;
    }

    /**
     * Récupère la réponse carré que l'utilisateur à encoder, vérifie si elle est bonne, le cas échéant ajoute 1 point au résultat.
     * @param reponse : la réponse encodée par l'utilisateur.
     * @throws Exception
     */
    public void recevoirReponseCarre(Reponse reponse) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER, "Aucune réponse carré n'était attendue à ce moment.");
        boolean bonneReponse = verifierReponseCarre(reponse);
        int pointsGagnes = bonneReponse ? 1 : 0;
        ajouterResultat(pointsGagnes, bonneReponse);
    }

    /**
     * Récupère la réponse cash que l'utilisateur à encoder, vérifie si elle est bonne, le cas échéant ajoute 3 points au résultat.
     * @param reponseCash : la réponse encodée par l'utilisateur.
     * @throws Exception
     */
    public void recevoirReponseCash(String reponseCash) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_REPONSE_CASH, "Aucune reponse cash n'était attendue à ce moment.");
        boolean bonneReponse = verifierReponseCash(reponseCash);
        int pointsGagnes = bonneReponse ? 3 : 0;
        ajouterResultat(pointsGagnes, bonneReponse);
    }

    /**
     * Récupère la réponse quand l'utilisateur utilise le joker Moite/Moite, vérifie si elle est bonne, le cas échéant ajoute 1 points au résultat.
     * @param reponse : la réponse encodée par l'utilisateur
     * @throws Exception
     */
    public void recevoirReponseMoiteMoite(Reponse reponse) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_REPONSE_MOITE_MOITE,
                "Aucun réponse Moite/Moite n'était attendue à ce moment.");
        boolean bonneReponse = verifierReponseCarre(reponse);
        int pointsGagnes = bonneReponse ? 1 : 0;
        ajouterResultat(pointsGagnes, bonneReponse);

    }
    
    /**
     * Définit le thème en fonction du choix de l'utilisateur, passe ensuite à l'état suivant.
     * @param theme : thème choisi par l'utilisateur.
     * @throws Exception
     */
    public void recevoirTheme(Theme theme) throws Exception {
        verifierEtat(Partie.Etat.DEMANDER_LE_THEME, "Aucun thème n'était attendu à ce moment.");
        setTheme(theme);
        this.etat = Partie.Etat.DEMANDER_LA_DIFFCULTE;
    }
    
    /**
     * Setter de difficulte.
     * @param difficulte
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
     * @param theme
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
        this.questionsPossibles = JDBCRequests.getQuestionFromDB(theme.getId());
    }
    
    /**
     * Setter d'utilisateur.
     * @param utilisateur
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    /**
     * action effectuée par le programme en fonction du joker choisi par l'utilisateur.
     * @param joker : joker demandé par l'utilisateur
     * @throws Exception
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
                // TODO afficher un message propre au joker (afficher bonne reponse).
                ajouterResultat(1, true);
                break;
        }
        jokersPossibles.remove(joker);
    }

    /**
     * Vérification qu'on est bien dans l'état attendu.
     * @param etat : etat actuel.
     * @param messageErreur : message d'erreur.
     * @throws Exception
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
}
