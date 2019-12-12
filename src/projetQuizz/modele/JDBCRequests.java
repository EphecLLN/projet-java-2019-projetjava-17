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

/**
 * @author autom
 *
 */
public class JDBCRequests {
	static String url = "jdbc:mysql://localhost/projetJava";
	static String login = "root";
	static String passwd = "";

	/**
	 * @author autome edwin
	 * 
	 *         Récupère en db tous les thèmes possibles du quizz et les ajoute dans
	 *         ArrayList<Theme> themes
	 * 
	 * @return l'ArrayList contenant tous les objets Theme
	 */
	public static ArrayList<Theme> getThemeFromDB() {
		try {
			ArrayList<Theme> themes = new ArrayList<Theme>();
			Connection connection = DriverManager.getConnection(url, login, passwd);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM theme");

			while (resultSet.next()) {
				themes.add(new Theme(resultSet.getInt("theme_id"), resultSet.getString("theme_nom")));
				// Il faut retirer l'attribut question au constructeur de l'objet Theme
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
	 * @author autome edwin
	 * 
	 *         Récupère les question du thème choisi par l'utilisateur et le push
	 *         dans le tableau des Questions
	 * 
	 * @param themeId : id du theme choisi par l'utilisateur
	 * @return questions: ArrayList contenant toutes les questions du quizz
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

			return (questions);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author autome edwin
	 * 
	 *         Récupère les réponses spécifiques à une question depuis la base de
	 *         données
	 * 
	 * @param questionId : id de la question pour laquelle on récupère les reponses
	 *                   possibles en db
	 * @return reponses: array contenant les objets reponses correspondant à la
	 *         question dont l'id est reçu en paramètre de la fonction
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
	 * @author autome edwin
	 * 
	 *         Envoi les résultats de la partie terminée dans la table 'partie' de
	 *         la db
	 * 
	 * @param partie: l'objet partie
	 */
	public static void insertPartieResult(Partie endedpartie) {
		try {
			Connection connection = DriverManager.getConnection(url, login, passwd);
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO partie (`utilisateur_id`, `partie_difficulte`, `theme_id`, `partie_score`) VALUES ()");
			// TODO paramètrer valeurs de l'insert

			connection.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author autome edwin
	 * 
	 *         Affiche les 10 meilleurs parties au même theme que la partie venant
	 *         de se terminer
	 * @param themeId : id du theme de la partie terminée
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
	 * @author autome edwin
	 * 
	 *         Vérifie si le pseudo de l'utilisateur existe dans la base de données
	 * 
	 * @param username: pseudo de l'utilisateur courant
	 * @return true si le pseudo existe dans la base de données sinon false
	 */
	public static boolean userExist(String username) {
		try {
			Connection connection = DriverManager.getConnection(url, login, passwd);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM utilisateur WHERE utilisateur_pseudo = '" + username + "'");

			return resultSet.next();//resultSet.next() revoie false s'il n'y a aucun valeur récupérée

		} catch (SQLException e) {
			e.printStackTrace();
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

		// getThemeFromDB();
		// getQuestionFromDB(2);
		// insertPartieResult();
		// showTopTenTheme(1);
		// getQuestionFromDB(1);
		//System.out.println(userExist("Edwin"));

	}

}
