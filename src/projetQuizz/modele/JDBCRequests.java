package projetQuizz.modele;

import projetQuizz.Quizz;
import projetQuizz.vue.InterfaceDeJeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author autome edwin
 *
 */
public class JDBCRequests {
    static String url = "jdbc:mysql://localhost/projetJava";
    static String login = "root";
    static String passwd = "";

    /**
     * Récupère en db tous les thèmes possibles du quizz et les ajoute dans
     * ArrayList<Theme> themes
     *
     * @return l'ArrayList contenant tous les objets Theme
     *
     * @author autome edwin
     */
    public static ArrayList<Theme> getThemeFromDB() {
        try {
            ArrayList<Theme> themes = new ArrayList<Theme>();
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM theme");

            while (resultSet.next()) {
                //themes.add(new Theme(resultSet.getInt("theme_id"), resultSet.getString("theme_nom")));
                // Il faut retirer l'attribut question au constructeur de l'objet Theme
                System.out.println(resultSet.getString("theme_id"));
            }

            statement.close();
            resultSet.close();

            return (themes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Récupère les question du thème choisi par l'utilisateur et le push dans le
     * tableau des Questions
     *
     * @param themeId : id du theme choisi par l'utilisateur
     * @return questions: ArrayList contenant toutes les questions du quizz
     *
     * @author autome edwin
     */
    public static ArrayList<Question> getQuestionFromDB(int themeId) {
        try {
            ArrayList<Question> questions = new ArrayList<Question>();
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM question WHERE theme_id =" + themeId);

            while (resultSet.next()) {
                questions.add(new Question(resultSet.getInt("question_id"), resultSet.getString("question"),
                        getReponsesFromDB(resultSet.getInt("question_id"))));
            }

            statement.close();
            resultSet.close();

            Collections.shuffle(questions);
            return (questions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Récupère les réponses spécifiques à une question depuis la base de données.
     * Fonction appelée par getQuestionFromDB() pour terminer la création d'un objet
     * Question
     *
     * @param questionId : id de la question pour laquelle on récupère les reponses
     *                   possibles en db
     * @return reponses: array contenant les objets reponses correspondant à la
     *         question dont l'id est reçu en paramètre de la fonction
     *
     * @author autome edwin
     */
    private static ArrayList<Reponse> getReponsesFromDB(int questionId) {
        ArrayList<Reponse> reponses = new ArrayList<Reponse>();
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reponse WHERE question_id =" + questionId);

            while (resultSet.next()) {
                reponses.add(new Reponse(resultSet.getInt("reponse_id"), resultSet.getString("reponse"),
                        resultSet.getBoolean("estBonneReponse")));
            }
            Collections.shuffle(reponses);
            return reponses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * Envoi les résultats de la partie terminée dans la table 'partie' de la db
     *
     * @param endedPartie : l'objet partie contenant les info de la partie venant de se
     *                terminer
     *
     * @author autome edwin
     */
    public static void insertPartieResult(Partie endedPartie) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            System.out.println(endedPartie.getUtilisateur().getId());
            System.out.println(endedPartie.getDifficulte());
            System.out.println(endedPartie.getTheme().getNom());
            System.out.println(endedPartie.calculScore()[2]);
            statement.executeUpdate(
                    "INSERT INTO partie (`utilisateur_id`, `partie_difficulte`, `theme_id`, `partie_score`) "
                            + "VALUES (" + endedPartie.getUtilisateur().getId() + ", '" + endedPartie.getNomDifficulte(endedPartie.getDifficulte()) + "', "
                            + endedPartie.getTheme().getId() + ", " + endedPartie.calculScore()[2] + ")");

            connection.close();
            statement.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère le nom d'un thème sur base de son id
     * @param id : id du thème dont on souhaite récupérer le nom
     */
    public static String getThemeNameById(int id) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT theme_nom FROM theme WHERE theme_id =" + id);

            while(resultSet.next()){
                return resultSet.getString("theme_nom");
            }

            connection.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Récupère le nom d'un utilisateur sur base de son id
     * @param id : id de l'utilisateur dont on veut récupérer le nom
     * @return
     */
    public static String getUserNameById(int id){
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT utilisateur_pseudo FROM utilisateur WHERE utilisateur_id =" + id);

            while(resultSet.next()){
                return resultSet.getString("utilisateur_pseudo");
            }

            connection.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Affiche les 10 meilleurs parties au même theme que la partie venant de se
     * terminer
     *
     * @param themeId : id du theme de la partie terminée
     * @param difficulte : nom de la difficulté de la partie venant de se finir
     *
     * @author autome edwin
     */
    public static ResultSet showTopTenTheme(int themeId, String difficulte) {

        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT ROW_NUMBER() OVER (ORDER BY partie_score DESC), utilisateur_id, dateEtHeure, partie_score FROM `partie` WHERE theme_id = "
                            + themeId + " and partie_difficulte = '" + difficulte.toLowerCase() +  "' ORDER BY partie_score DESC LIMIT 10");
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Détermine la place de la partie finie dans tout le palmarès du thème de la partie qui vient de se terminer
     * @param score : score final de la partie venant de se terminer
     * @param themeId : id du thème de la partie venant de se terminer
     * @return
     */
    public static ResultSet showCurrentRankTheme(int score, int themeId){
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ROW_NUMBER() OVER (ORDER BY partie_score desc), theme_id, partie_score, dateEtHeure from partie where theme_id = " + themeId);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Vérifie si le pseudo de l'utilisateur existe dans la base de données
     *
     * @param username : pseudo de l'utilisateur courant
     *
     * @return true si le pseudo existe dans la base de données sinon false
     *
     * @author autome edwin
     */
    public static boolean userExist(String username) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM utilisateur WHERE utilisateur_pseudo = '" + username + "'");

            return resultSet.next();// resultSet.next() revoie false s'il n'y a aucun valeur récupérée

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Créée un nouvel utilisateur dans la base de données
     * @param newUsername : pseudo de l'utilisateur nouvellement créée
     */
    public static void createNewUserInDB(String newUsername) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_pseudo`) VALUES ('" + newUsername + "')");

            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère le nom et l'id de l'utilisateur courant
     * @param username : nom de l'utilisateur courant
     * @return Objet Utilisateur
     */
    public static Utilisateur getUserInfos(String username){
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur WHERE utilisateur_pseudo ='" + username + "'");

            while(resultSet.next()){
                return new Utilisateur(resultSet.getString("utilisateur_pseudo"), resultSet.getInt("utilisateur_id"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Vérifie si l'utilisateur dont le nom vient d'être rentré
     * @param name : nom de l'utilisateur à vérifier l'identité
     * @return true si l'utilisateur existe ou false
     */
    public static boolean checkUserIdentity(String name){
        if(userExist(name)) { //Vérifie si l'utilisateur existe dans la base de données
            Utilisateur currentUser = getUserInfos(name);
            return true;
        }
        if (name != null && !name.isEmpty()) {
            createNewUserInDB(name);
            getUserInfos(name);
            return true;
        }
        return false;
    }

    /**
     * Retire tous les accent du string passer en paramètre
     * @param s : string à modifier
     * @return 
     */
    public static String simpleCase(String s) {
        String newString = "";
        for (String str : s.split("")) {
            String c = Partie.Case.get(str);
            if (c != null) {
                newString += c;
            } else {
                newString += str;
            }
        }
        return newString;
    }

    /**
     * Détermine si la string en paramètre est en fait un nombre
     * @param strNum : string à analyser
     * @return true si la string est un nombre, false en cas d'erreur
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
