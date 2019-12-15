/**
 *
 */
package projetQuizz.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

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
               /* questions.add(new Question(resultSet.getInt("question_id"), resultSet.getString("question"),
                        getReponsesFromDB(resultSet.getInt("question_id"))));*/

            }

            statement.close();
            resultSet.close();

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
    private static Reponse[] getReponsesFromDB(int questionId) {
        Reponse[] reponses = new Reponse[4];
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reponse WHERE question_id =" + questionId);

            int i = 0;

            while (resultSet.next()) {
                reponses[i] = new Reponse(resultSet.getInt("reponse_id"), resultSet.getString("reponse"),
                        resultSet.getBoolean("estBonneReponse"));
                i++;
            }

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
     * @param partie: l'objet partie contenant les info de la partie venant de se
     *                terminer
     *
     * @author autome edwin
     */
    public static void insertPartieResult(Partie endedPartie) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO partie (`utilisateur_id`, `partie_difficulte`, `theme_id`, `partie_score`) "
                            + "VALUES (" + endedPartie.getUtilisateur().getId() + ", "+ endedPartie.getDifficulte()+" ,"
                            + endedPartie.getTheme().getNom() + ", " + endedPartie.getResultat().getScore() + "");

            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche les 10 meilleurs parties au même theme que la partie venant de se
     * terminer
     *
     * @param themeId : id du theme de la partie terminée
     *
     * @author autome edwin
     */
    public static void showTopTenTheme(int themeId) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM `partie` WHERE theme_id = " + themeId + " ORDER BY partie_score DESC LIMIT 10");

            while (resultSet.next()) {
                System.out.print("partieId: " + resultSet.getInt("partie_id") + "; userId: "
                        + resultSet.getInt("utilisateur_id") + "; dateheure: " + resultSet.getDate("DateEtHeure")
                        + "; difficulte: " + resultSet.getString("partie_difficulte") + "; themeId: "
                        + resultSet.getInt("theme_id") + "; score: " + resultSet.getByte("partie_score") + "\n");
            }

            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vérifie si le pseudo de l'utilisateur existe dans la base de données
     *
     * @param username: pseudo de l'utilisateur courant
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
     * 
     * @param newUsername: pseudo de l'utilisateur nouvellement créée
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
     * Récupère en DB les infos de l'utilisateur dont le nom est passer en paramètre et en fait un objet Utilisateur
     * 
     * @param username: pseudo de l'utilisateur pour lequelle nous avons besoins des informations
     * 
     * @return Utilisateur: utilisateur dont le nom correponds au paramètre 
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
     * Vérifie si le nom encodé par l'utilisateur existe ou s'il faut créer un nouvel utilisateur
     */
    public static void checkUserIdentity(){
        System.out.println("Quelle est votre nom:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        if(userExist(name)){ //Vérifie si l'utilisateur existe dans la base de données
            Utilisateur currentUser = getUserInfos(name);
            System.out.println("Bienvenue " + currentUser.getNom() + "[id: " + currentUser.getId() + "]");

        } else {
            System.out.println("Nom d'utilisateur inconnu ! \nVoulez-vous créér un nouvel utilisateur ? O/N \nSi vous choisssez 'non'(N), le programme s'arretera");
            String str = sc.nextLine();
            char choix = str.charAt(0);

            if(Character.toUpperCase(choix) == 'O'){ //Création du nouvel utilisateur
                System.out.println("Quel est le nom du nouvel utilisateur ?");
                String newUser = sc.nextLine();

                while(userExist(newUser)){ // Vérifie si le nom du nouvel utilisateur n'est pas déjà pris
                    System.out.println("Pseudo déjà utilisé ! Veuillez en choisir un autre !");
                    newUser = sc.nextLine();
                }

                System.out.println("Nom valide ! Création d'un nouvel utilisateur !");
                createNewUserInDB(newUser);
                System.out.println("Nouvel utilisateur créé !\n");

            } else {
                System.exit(0);
            }
        }
        sc.close();
    }

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

    private static boolean verifierReponseCash(String reponseCash) {
        Reponse[] reponses = {new Reponse(1,"15",true),new Reponse(2,"14"), new Reponse(3,"13")};
        for (int i = 0; i < reponses.length; i++) {
            if (Partie.isNum(reponseCash)) {
                if (reponses[i].getEstBonneReponse() && reponses[i].getReponse().compareTo(reponseCash) == 0) {
                    return true;
                }
                return false;
            }
            if (reponses[i].getEstBonneReponse() && simpleCase((reponses[i].getReponse()).toLowerCase()).contains(simpleCase(reponseCash).toLowerCase())) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        
    }

}
