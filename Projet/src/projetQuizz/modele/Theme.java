package projetQuizz.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Theme {
	private String nom;
	private int id;

	/**
	 * Récupère dans la base de données tous les thèmes, en fait un objet de chacun d'entre eux (id et nom) et les insèrent dans un ArrayList
	 * @return les themes importés
	 */
	public static ArrayList<Theme> chargerThemes() {
		try {
			ArrayList<Theme> themes = new ArrayList<Theme>();
			Connection connection = DriverManager.getConnection(Partie.url, Partie.login, Partie.passwd);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM theme");

			while (resultSet.next()) {
				themes.add(new Theme(resultSet.getInt("theme_id"),resultSet.getString("theme_nom")));
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
	 * Constructeur de l'objet Theme
	 * @param id l'id du thème
	 * @param nom le nonm du thème
	 */
	Theme(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	/**
	 * getter du nom du thème
	 * @return String nom du thème
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * getter de l'id du thème
	 * @return int id du thème
	 */
	public int getId() {
		return id;
	}
}

