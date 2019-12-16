package projetQuizz.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import projetQuizz.Quizz;
import projetQuizz.modele.JDBCRequests;
import projetQuizz.modele.Partie;
import projetQuizz.modele.Partie.Difficulte;
import projetQuizz.modele.Partie.Joker;
import projetQuizz.modele.Question;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Resultat;
import projetQuizz.modele.Theme;

public class GUI extends InterfaceDeJeu implements ActionListener {
	private JFrame gui = new JFrame("EpheQuizz");
	private Box affichage = Box.createVerticalBox();
	private JTextArea displayQuestion;
	private GridLayout showReponses;
	private Box ecrireReponse;
	private JTextField reponse;
	private JButton envoyer;
	private String info;

	Container tableauPrincipal = gui.getContentPane();

	/**
	 * @param quizz
	 */
	public GUI(Quizz quizz) {
		super(quizz);
		gui.setSize(500,500);
		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gui.setVisible(true);
		tableauPrincipal.add(affichage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afficherErreur(Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherResultat(Resultat resultat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherScores(Partie endedPartie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void choisirTheme(ArrayList<Theme> themesPossibles) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderCarreCash(Question questionActuelle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderDifficulte(Difficulte[] difficultes) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderMoiteMoite(Reponse[] reponsesPossiblesActuelles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderNom() throws Exception {
		displayQuestion = new JTextArea("Quel est votre nom:");
		displayQuestion.setPreferredSize(new Dimension(300,500));
		displayQuestion.setEditable(false);
		affichage.add(displayQuestion);

		ecrireReponse = Box.createHorizontalBox();
		affichage.add(ecrireReponse);

		reponse = new JTextField();
		reponse.setSize(new Dimension(50,50));
		envoyer = new JButton("Valider");
		envoyer.setSize(new Dimension(50,50));
		ecrireReponse.add(reponse);
		ecrireReponse.add(envoyer);

		envoyer.addActionListener(this);
		int flag = 0;
		while (flag == 0) {
			TimeUnit.SECONDS.sleep(1);
			if (JDBCRequests.checkUserIdentity(this.info)) {
				flag = 1;
			}
		}
		getQuizz().recevoirNomUtilisateur(JDBCRequests.getUserInfos(this.info));
	}

	@Override
	public void demanderReponseCarreJoker(Reponse[] reponsesPossiblesActuelles, Joker[] jokersPossibles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void demanderReponseCash() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.info = reponse.getText();
	}
}