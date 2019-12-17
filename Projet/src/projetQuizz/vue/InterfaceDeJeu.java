package projetQuizz.vue;

import java.util.ArrayList;

import projetQuizz.Quizz;
import projetQuizz.modele.Partie;
import projetQuizz.modele.Partie.Difficulte;
import projetQuizz.modele.Partie.Joker;
import projetQuizz.modele.Question;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Resultat;
import projetQuizz.modele.Theme;

public abstract class InterfaceDeJeu {

	private Quizz quizz;

	public InterfaceDeJeu(Quizz quizz) {
		this.quizz = quizz;
	}

	//---DECLARATION DES METHODES INTERFACES QUI SERONT IMPLEMENTE DANS LES CLASSES ENFANT CONSOLE ET GUI---
	public abstract void afficherErreur(Exception e);

	public abstract void afficherResultat(Resultat resultat) throws Exception;

	public abstract void afficherScores(Partie endedPartie);

	public abstract void choisirTheme(ArrayList<Theme> themesPossibles) throws Exception;

	public abstract void demanderCarreCash(Question questionActuelle) throws Exception;

	public abstract void demanderDifficulte(Difficulte[] difficultes) throws Exception;

	public abstract void demanderMoiteMoite(Question question, Reponse[] reponsesPossiblesActuelles) throws Exception;

	public abstract void demanderNom() throws Exception;

	public abstract void demanderReponseCarreJoker(Question question, Reponse[] reponsesPossiblesActuelles, Joker[] jokersPossibles)
			throws Exception;

	public abstract void demanderReponseCash(Question question) throws Exception;

	public Quizz getQuizz() {
		return quizz;
	}
}
