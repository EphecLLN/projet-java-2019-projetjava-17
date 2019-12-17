package projetQuizz.modele;

import java.util.ArrayList;

public class Question {

	private int id;
	private String question;
	private ArrayList<Reponse> reponses;

	/**
	 *	Constructeur de la classe Question.
	 */
	Question(int id, String question, ArrayList<Reponse> reponses) {
		this.id = id;
		this.question = question;
		this.reponses = reponses;
	}

	/**
	 * Getter de question.
	 */
	public String getQuestion() {
		return this.question;
	}

	/**
	 * Getter de reponses
	 */
	public ArrayList<Reponse> getReponses() {
		return reponses;
	}

	/**
	 * Getter de l'id de la question
	 */
	public int getId() {return this.id;}

}
