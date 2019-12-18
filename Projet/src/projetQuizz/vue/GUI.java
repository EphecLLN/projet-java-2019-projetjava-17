package projetQuizz.vue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import projetQuizz.Quizz;
import projetQuizz.modele.Partie;
import projetQuizz.modele.Partie.CarreCash;
import projetQuizz.modele.Partie.Difficulte;
import projetQuizz.modele.Partie.Joker;
import projetQuizz.modele.Question;
import projetQuizz.modele.Reponse;
import projetQuizz.modele.Resultat;
import projetQuizz.modele.Theme;

public class GUI extends InterfaceDeJeu {
	private JFrame frame = new JFrame("EpheQuizz");
	private Container contentPane = frame.getContentPane();
	private Box verticalBox = Box.createVerticalBox();
	private JTextArea textArea = new JTextArea();
	private Box horizontalBox = Box.createHorizontalBox();
	private Box horizontalBoxJoker;

	/**
	 * Initialisation de la gui
	 * @param quizz le quizz en cours
	 */
	public GUI(Quizz quizz) {
		super(quizz);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
		contentPane.add(verticalBox);
		verticalBox.add(textArea);
		verticalBox.add(horizontalBox);
		textArea.setEditable(false);
	}

	@Override
	/**
	 * Méthode exécutée en cas de problème
	 * @param e l'exception envoyée
	 */
	public void afficherErreur(Exception e) {
		// TODO afficher un popup? ou un message "fermable" dans la fenetre
		// System.out.println(e.getMessage());
	}

	@Override
	/**
	 * Affiche le résultat de la question
	 * @param resultat le resultat obtenu
	 */
	public void afficherResultat(Resultat resultat) {
		JButton envoyer = new JButton("Question suivante");

		if (resultat.getEstBonneReponse()) {
			textArea.setText("Bravo ! Vous avez gagné " + resultat.getScore() + " points.");
		} else {
			textArea.setText("Raté, la bonne réponse était: " + resultat.getBonneReponse().getReponse() + ".");
		}
		horizontalBox.add(envoyer);
		envoyer.requestFocus();
		envoyer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					horizontalBox.removeAll();
					getQuizz().passerQuestionSuivante();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	/**
	 * affiche le score final
	 * @param endedPartie la partie en cours
	 */
	public void afficherScores(Partie endedPartie) {
		textArea.setText(endedPartie.getTexteDeFin());
	}

	@Override
	/**
	 * Interroge l'utilisateur sur le thème voulu
	 * @param themesPossibles les thèmes disponibles
	 */
	public void choisirTheme(ArrayList<Theme> themesPossibles) throws Exception {
		textArea.setText("Choisissez parmis ces thèmes:");
		for (int i = 0; i < themesPossibles.size(); i++) {
			final Theme theme = themesPossibles.get(i);
			JButton button = new JButton(theme.getNom());
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						horizontalBox.removeAll();
						getQuizz().recevoirTheme(theme);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			horizontalBox.add(button);
		}
	}

	@Override
	/**
	 * Demande le mode de réponse voulu par l'utilisateur pour la question en cours
	 * @param questionActuelle la question en cours
	 */
	public void demanderCarreCash(Question questionActuelle) {
		textArea.setText(questionActuelle.getQuestion() + "\n\nChoisissez parmi:");

		JButton buttonCarre = new JButton("carré");
		buttonCarre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					horizontalBox.removeAll();
					getQuizz().recevoirCarreCash(CarreCash.CARRE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		JButton buttonCash = new JButton("cash");
		buttonCash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					horizontalBox.removeAll();
					getQuizz().recevoirCarreCash(CarreCash.CASH);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		horizontalBox.add(buttonCarre);
		horizontalBox.add(buttonCash);
	}

	@Override
	/**
	 * Interoge l'utilisateur quant à la difficullté désirée
	 * @param difficultes les difficultés disponibles
	 */
	public void demanderDifficulte(Difficulte[] difficultes) throws Exception {
		textArea.setText("Choisissez parmis ces difficultés:");
		for (int i = 0; i < difficultes.length; i++) {
			final Difficulte difficulte = difficultes[i];
			JButton button = new JButton(Partie.getNomDifficulte(difficulte));
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						horizontalBox.removeAll();
						getQuizz().recevoirDifficulte(difficulte);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			horizontalBox.add(button);
		}
	}

	@Override
	/**
	 * Repose la question en cours avec 2 réponses au lieu de 4
	 * @param questino la question en cours
	 * @param reponsesPossiblesActuelles les réponses possibles actuelles
	 */
	public void demanderMoiteMoite(Question question, Reponse[] reponsesPossiblesActuelles) {
		poserQuestion(question, reponsesPossiblesActuelles);
	}

	/**
	 * pose la question et lance une méthode particulière en fonction de l'action utilisateur
	 * @param question la question en cours
	 * @param reponsesPossiblesActuelles les reponses possibles actuelles
	 */
	private void poserQuestion(Question question, Reponse[] reponsesPossiblesActuelles) {
		textArea.setText(question.getQuestion() + "\n\nChoisissez parmi ces possibilités:");

		for (int i = 0; i < reponsesPossiblesActuelles.length; i++) {
			final Reponse reponse = reponsesPossiblesActuelles[i];
			JButton button = new JButton(reponse.getReponse());
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						horizontalBox.removeAll();
						if (horizontalBoxJoker != null) {
							verticalBox.remove(horizontalBoxJoker);
						}
						getQuizz().recevoirReponseCarre(reponse);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			horizontalBox.add(button);
		}
	}

	@Override
	/**
	 * Demande le nom del'utilisateur
	 */
	public void demanderNom() throws IOException {
		JButton envoyer = new JButton("Envoyer");
		JTextField textInput = new JTextField();
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = textInput.getText();
				try {
					horizontalBox.removeAll();
					getQuizz().recevoirNomUtilisateur(nom);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
		String content = new String(Files.readAllBytes(Paths.get("regles.txt")));
		textArea.setText(content + "\n\nQuel est votre nom:");
		horizontalBox.add(textInput);
		horizontalBox.add(envoyer);
		textInput.requestFocus();
		textInput.addActionListener(action);
		envoyer.addActionListener(action);
	}

	@Override
	/**
	 * Affiche les réponses et les jokers disponibles
	 * @param question la question en cours
	 * @param reponsesPossiblesActuelles les réponses possibles actuelles
	 * @param jokersPossibles les jokers disponibles
	 *
	 */
	public void demanderReponseCarreJoker(Question question, Reponse[] reponsesPossiblesActuelles, Joker[] jokersPossibles) {
		poserQuestion(question, reponsesPossiblesActuelles);

		horizontalBoxJoker = Box.createHorizontalBox();
		verticalBox.add(horizontalBoxJoker);

		for (int i = 0; i < jokersPossibles.length; i++) {
			final Joker joker = jokersPossibles[i];
			JButton button = new JButton(Partie.getNomJokers(joker));
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						horizontalBox.removeAll();
						verticalBox.remove(horizontalBoxJoker);
						getQuizz().recevoirJoker(joker);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			horizontalBoxJoker.add(button);
		}
	}

	@Override
	/**
	 * Permet à l'utilisateur d'encoder lui-même une réponse
	 * @param questino al question en cours
	 */
	public void demanderReponseCash(Question question) {
		JButton envoyer = new JButton("Envoyer");
		JTextField textInput = new JTextField();

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String reponseCash = textInput.getText();
				try {
					horizontalBox.removeAll();
					getQuizz().recevoirReponseCash(reponseCash);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
		textArea.setText(question.getQuestion() + "\n\nInscrivez votre réponse");
		horizontalBox.add(textInput);
		horizontalBox.add(envoyer);
		textInput.requestFocus();
		textInput.addActionListener(action);
		envoyer.addActionListener(action);
	}
}