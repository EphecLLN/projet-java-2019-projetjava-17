-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 09 déc. 2019 à 22:27
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `partie`
--

CREATE TABLE `partie` (
  `partie_id` int(11) NOT NULL,
  `utilisateur_id` int(11) NOT NULL,
  `dateEtHeure` datetime NOT NULL,
  `partie_difficulte` varchar(20) NOT NULL,
  `theme_id` int(11) NOT NULL,
  `partie_score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `theme_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `question`
--

INSERT INTO `question` (`question_id`, `question`, `theme_id`) VALUES
(1, 'Quelle est la capitale de la Mongolie ?', 1),
(2, 'Quel tropique se trouve dans l\'hémisphère nord ?', 1),
(3, 'Quel est l\'age approximatif de l\'univers (en milliards d\'années) ?', 2),
(4, 'Qui a observé l\'éloignement des galaxies grâce au décalage spectral (nom uniquement) ?', 2),
(9, 'Comment appelle-t-on un cours d\'eau qui se jette dans un fleuve ?', 1),
(10, 'En météo, comment appelle-t-on la zone de haute pression responsable du beau temps ?', 1),
(11, 'Quelle mer se situe au nord de la Turquie ?', 1),
(12, 'Quel volcan se trouve en Sicile ?', 1),
(13, 'Quel est le plus peuplé au monde ?', 1),
(46, 'Dans quel pays se situe la ville d\'Edimbourg ?', 1),
(47, 'Sur quel continent trouve-t-on le désert de Gobi ?', 1),
(48, 'Dans quel pays trouve-t-on la région autonome du Tibet ?', 1),
(49, 'Quel phénomène catastrophique de mouvement d\'eau peut apparaître après un séisme ?', 1),
(50, 'L\'île de la Réunion est bordée par quel océan ?', 1),
(51, 'Quel sommet est le plus haut du monde ?', 1),
(52, 'Comment appelle-t-on l\'étendue d\'eau de mer gelé située au pôle nord ?', 1),
(53, 'Quel continent n\'est qu\'un désert ?', 1),
(54, 'La fusion de quel élément chimique se déroule actuellement au coeur du soleil ?', 2),
(55, 'Quel est le nom de l\'étoile la plus proche de la Terre ?', 2),
(56, 'Le fond diffus cosmologique est le résidu de quel évènement de notre univers ?', 2),
(57, 'Le centre de notre galaxie en a un supermassif. De quoi s\'agit-il ?', 2),
(58, 'Sur quel satellite de Saturne a-t-on pu observé des lacs de méthane liquide ?', 2),
(59, 'Combien de satellite naturel possède la Terre ?', 2),
(60, 'Quel planète est la plus proche du soleil ?', 2),
(61, 'Quel type d\'étoile deviendra notre soleil après son implosion en supernova ?', 2),
(62, 'Qui a mis en place la loi de la relativité général ?', 2),
(63, 'Quelle constellation est connue pour ses 3 étoiles formant une ceinture et sa nébuleuse ?', 2),
(64, 'Deux galaxies naines proche de la voie lactée ont été nommé en l honneur d un célèbre navigateur, lequel?', 2),
(65, 'Sur quelle planète trouve-t-on la montagne ayant la plus grande altitude ?', 2),
(66, 'Quelle satellite de Jupiter a une activité volcanique très importante à cause des effets de marée ?', 2),
(67, 'A quel genre musical Tchaïkovski appartient-il ?', 5),
(77, 'Les instruments typiques du hard rock sont la guitare électrique, la batterie et ... ?', 5),
(78, 'Le piano est un instrument dit à cordes ... ?', 5),
(79, 'En 1985, quelle chanson créé et chantée par le groupe \"USA for Africa\" deviendra un succès international ?', 5),
(80, 'Quelle chanson est la plus vue sur Youtube ?', 5),
(81, 'En quelle année Queen interpète pour la première fois \"Bohemian Rhapsody\" ?', 5),
(82, 'De quel groupe GIMS faisait-il partie avant de passer en solo ?', 5),
(83, 'En quelle année Eminem a-t-il sorti un album après 7ans sans nouvelle musique ?', 5),
(84, 'Avec qui Orelsan s\'est uni pour former le groupe \"Casseurs Flowters\" ?', 5),
(85, 'Quel groupe est auteur du titre \"Barbie Girl\"?', 5),
(86, 'Comment s\'appelle le chanteur des Maroon 5 ?', 5),
(87, 'Selon Patrick Sébastien, que faut-il faire tourner ?', 5),
(88, 'Combien de temps contient la valse chantée par Jacques Brel ?', 5),
(89, 'De quel type d\'instrument l\'harmonica fait-il partie ?', 5),
(90, 'De quel pays le violon est-il originaire ?', 5),
(91, 'Combien de case contient un Puissance 4 ?', 6),
(92, 'Quel jeu vidéo fut le premiers grand succès des salles d arcades ?', 6),
(93, 'Combien de numéros y a-t-il à la marelle ?', 6),
(94, 'Au début de chaque round au poker, on parle de petite et de grande ...?', 6),
(95, 'De quel autre jeu le shōgi se rapproche-t-il le plus ?', 6),
(96, 'Quel jeu TV était,jusqu en 2019, présenté par Jean-Pierre Foucault ?', 6),
(97, 'Combien de points vaut un F au Scrabble ?', 6),
(98, 'Quel jeu de carte était fourni comme jeu sur les anciennes versions de Windows ?', 6),
(99, 'Quelle société a créé le jeu de carte à collectionner \"Yu-Gi-Oh!\"', 6),
(100, 'Quelle version du jeu vidéo \"Pokémon\" est sortie en Europe en 2000 ?', 6),
(101, 'Ou le bowling a-t-il été créé ?', 6),
(102, 'Quel jeu de carte se joue avec seulement 32 cartes ?', 6),
(103, 'Au loup-garou, qui lie 2 amoureux jusqu\'à la fin de la partie ?', 6),
(104, 'Quel dessin animé s\'est inspiré des toupies comme univers ?', 6),
(105, 'Quel \"jeu\" pouvant engendrer la mort se joue avec un revolver ?', 6);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `reponse_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `estBonneReponse` tinyint(4) NOT NULL,
  `reponse` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`reponse_id`, `question_id`, `estBonneReponse`, `reponse`) VALUES
(1, 1, 1, 'Oulan-Bator'),
(2, 1, 0, 'Kaboul'),
(3, 1, 0, 'Bagdad'),
(4, 1, 0, 'Douchanbé'),
(5, 2, 1, 'Cancer'),
(6, 2, 0, 'Capricorne'),
(7, 2, 0, 'Equateur'),
(8, 2, 0, 'Greenwich'),
(9, 3, 1, '13,8'),
(10, 3, 0, '25,2'),
(11, 3, 0, '02,1'),
(12, 3, 0, '66,6'),
(13, 4, 1, 'Hubble'),
(14, 4, 0, 'Einstein'),
(15, 4, 0, 'Planck'),
(16, 4, 0, 'Hawking'),
(17, 9, 1, 'affluent'),
(18, 9, 0, 'confluent'),
(19, 9, 0, 'canal'),
(20, 9, 0, 'détroit'),
(21, 10, 1, 'Anticyclone'),
(22, 10, 0, 'Dépression'),
(23, 10, 0, 'Vent favorable'),
(24, 10, 0, 'Stratus'),
(25, 11, 1, 'Noire'),
(26, 11, 0, 'Méditerranée'),
(27, 11, 0, 'Caspienne'),
(28, 11, 0, 'Rouge'),
(29, 12, 1, 'Etna'),
(30, 12, 0, 'Vésuve'),
(31, 12, 0, 'Yellowstone'),
(32, 12, 0, 'Mauna Kea'),
(33, 13, 1, 'Chine'),
(34, 13, 0, 'Inde'),
(35, 13, 0, 'Etats-Unis'),
(36, 13, 0, 'Russie'),
(37, 46, 1, 'Royaume-Uni'),
(38, 46, 0, 'Norvège'),
(39, 46, 0, 'Danemark'),
(40, 46, 0, 'Irlande'),
(41, 47, 1, 'Asie'),
(42, 47, 0, 'Europe'),
(43, 47, 0, 'Afrique'),
(44, 47, 0, 'Océanie'),
(45, 48, 1, 'Chine'),
(46, 48, 0, 'Japon'),
(47, 48, 0, 'Népal'),
(48, 48, 0, 'Inde'),
(49, 49, 1, 'Tsunami'),
(50, 49, 0, 'Tourbillon'),
(51, 49, 0, 'Marée'),
(52, 49, 0, 'Courant marin'),
(53, 50, 1, 'Indien'),
(54, 50, 0, 'Pacifique'),
(55, 50, 0, 'Atlantique'),
(56, 50, 0, 'Arctique'),
(57, 51, 1, 'Everest'),
(58, 51, 0, 'Mont Blanc'),
(59, 51, 0, 'Olympe'),
(60, 51, 0, 'Kilimanjaro'),
(61, 52, 1, 'Banquise'),
(62, 52, 1, 'Glacier'),
(63, 52, 1, 'Iceberg'),
(64, 52, 1, 'Poudreuse'),
(65, 53, 1, 'Antarctique'),
(66, 53, 1, 'Océanie'),
(67, 53, 1, 'Afrique'),
(68, 53, 1, 'Asie'),
(69, 54, 1, 'Hydrogène'),
(70, 54, 0, 'Hélium'),
(71, 54, 0, 'Oxygène'),
(72, 54, 0, 'Uranium'),
(73, 55, 1, 'Soleil'),
(74, 55, 0, 'Proxima du Centaure'),
(75, 55, 0, 'Sirius'),
(76, 55, 0, 'Betelgeuse'),
(77, 56, 1, 'Big Bang'),
(78, 56, 0, 'Supernova'),
(79, 56, 0, 'Fusion galactique'),
(80, 56, 0, 'Évaporation d un trou noir'),
(81, 57, 1, 'Trou noir'),
(82, 57, 0, 'Amas globulaire'),
(83, 57, 0, 'Disque de débris'),
(84, 57, 0, 'Nuage moléculaire'),
(85, 58, 1, 'Titan'),
(86, 58, 0, 'Encelade'),
(87, 58, 0, 'Japet'),
(88, 58, 0, 'Mimas'),
(89, 59, 1, '1'),
(90, 59, 0, '2'),
(91, 59, 0, '3'),
(92, 59, 0, '4'),
(93, 60, 1, 'Mercure'),
(94, 60, 0, 'Vénus'),
(95, 60, 0, 'Terre'),
(96, 60, 0, 'Mars'),
(97, 61, 1, 'Naine blanche'),
(98, 61, 0, 'Naine brune'),
(99, 61, 0, 'Géante rouge'),
(100, 61, 0, 'Naine rouge'),
(101, 62, 1, 'Einstein'),
(102, 62, 0, 'Lemaître'),
(103, 62, 0, 'Newton'),
(104, 62, 0, 'Fermi'),
(105, 63, 1, 'Orion'),
(106, 63, 0, 'Cassiopée'),
(107, 63, 0, 'Hercule'),
(108, 63, 0, 'Andromède'),
(109, 64, 1, 'Magellan'),
(110, 64, 0, 'Colomb'),
(111, 64, 0, 'Cartier'),
(112, 64, 0, 'Cook'),
(113, 65, 1, 'Mars'),
(114, 65, 0, 'Terre'),
(115, 65, 0, 'Vénus'),
(116, 65, 0, 'Jupiter'),
(117, 66, 1, 'Io'),
(118, 66, 0, 'Europe'),
(119, 66, 0, 'Callisto'),
(120, 66, 0, 'Ganymède'),
(121, 67, 1, 'Classique'),
(122, 67, 0, 'Rap'),
(123, 67, 0, 'Jazz'),
(124, 67, 0, 'Hip-Hop'),
(125, 77, 1, 'Basse'),
(126, 77, 0, 'Flûte'),
(127, 77, 0, 'Piano'),
(128, 77, 0, 'Violon'),
(129, 78, 1, 'Frappées'),
(130, 78, 0, 'Pincées'),
(131, 78, 0, 'Frottées'),
(132, 78, 0, 'Martelées'),
(133, 79, 1, 'We are the world'),
(134, 79, 0, 'We are the champions'),
(135, 79, 0, 'Dancing Queens'),
(136, 79, 0, 'On m appelle l OVNI'),
(137, 80, 1, 'Despacito'),
(138, 80, 0, 'Shape of you'),
(139, 80, 0, 'Gangnam style'),
(140, 80, 0, 'Baby shark'),
(141, 81, 1, '1975'),
(142, 81, 0, '1980'),
(143, 81, 0, '1969'),
(144, 81, 0, '1971'),
(145, 82, 1, 'Sexion d assaut'),
(146, 82, 0, 'Psy4 de la Rime'),
(147, 82, 0, 'Kyo'),
(148, 82, 0, 'Team BS'),
(149, 83, 1, '2017'),
(150, 83, 0, '2013'),
(151, 83, 0, '2015'),
(152, 83, 0, '2019'),
(153, 84, 1, 'Gringe'),
(154, 84, 0, 'Booba'),
(155, 84, 0, 'Lacrim'),
(156, 84, 0, 'Disiz La Peste'),
(157, 85, 1, 'Aqua'),
(158, 85, 0, 'Abba'),
(159, 85, 0, 'Earth, Wind & Fire'),
(160, 85, 0, 'Avalanche'),
(161, 86, 1, 'Adam Levine'),
(162, 86, 0, 'Mickey Madden'),
(163, 86, 0, 'Matt Flynn'),
(164, 86, 0, 'James Valentine'),
(165, 87, 1, 'Les serviettes'),
(166, 87, 0, 'Les torchons'),
(167, 87, 0, 'Les girouettes'),
(168, 87, 0, 'Les demoiselles'),
(169, 88, 1, '1000'),
(170, 88, 0, '100'),
(171, 88, 0, '1'),
(172, 88, 0, '10'),
(173, 89, 1, 'Instrument à vent'),
(174, 89, 0, 'Instrument à corde'),
(175, 89, 0, 'Cuivres'),
(176, 89, 0, 'Instrument à percussions'),
(177, 90, 1, 'Italie'),
(178, 90, 0, 'Allemagne'),
(179, 90, 0, 'Espagne'),
(180, 90, 0, 'Chine'),
(181, 91, 1, '49'),
(182, 91, 0, '42'),
(183, 91, 0, '35'),
(184, 91, 0, '56'),
(185, 92, 1, 'Pong'),
(186, 92, 0, 'Space Invaders'),
(187, 92, 0, 'Pacman'),
(188, 92, 0, 'Super Mario'),
(189, 93, 1, '8'),
(190, 93, 0, '10'),
(191, 93, 0, '12'),
(192, 93, 0, '6'),
(193, 94, 1, 'Blinde'),
(194, 94, 0, 'Misère'),
(195, 94, 0, 'Mise'),
(196, 94, 0, 'Enchère'),
(197, 95, 1, 'Les échecs'),
(198, 95, 0, 'Les dames'),
(199, 95, 0, 'Othello'),
(200, 95, 0, 'Le jeu de l oie'),
(201, 96, 1, 'Qui veut gagner des millions ?'),
(202, 96, 0, 'Tout le monde veut prendre sa place'),
(203, 96, 0, 'La roue de la fortune'),
(204, 96, 0, 'Motus'),
(205, 97, 1, '4'),
(206, 97, 0, '2'),
(207, 97, 0, '3'),
(208, 97, 0, '5'),
(209, 98, 1, 'Solitaire'),
(210, 98, 0, 'Poker'),
(211, 98, 0, 'BlackJack'),
(212, 98, 0, 'Bataille'),
(213, 99, 1, 'Konami'),
(214, 99, 0, 'Hasbro'),
(215, 99, 0, 'Nintendo'),
(216, 99, 0, 'Wizards of the Coast'),
(217, 100, 1, 'Jaune'),
(218, 100, 0, 'Rouge'),
(219, 100, 0, 'Or'),
(220, 100, 0, 'Saphir'),
(221, 101, 1, 'Etats-Unis'),
(222, 101, 0, 'Canada'),
(223, 101, 0, 'Angleterre'),
(224, 101, 0, 'Australie'),
(225, 102, 1, 'La belote'),
(226, 102, 0, 'Le poker'),
(227, 102, 0, 'Le bridge'),
(228, 102, 0, 'La bataille Corse'),
(229, 103, 1, 'Cupidon'),
(230, 103, 0, 'Aphrodite'),
(231, 103, 0, 'La voyante'),
(232, 103, 0, 'La sorcière'),
(233, 104, 1, 'Beyblade'),
(234, 104, 0, 'Byakugan'),
(235, 104, 0, 'Pokemon'),
(236, 104, 0, 'Code Lyoko'),
(237, 105, 1, 'La roulette russe'),
(238, 105, 0, 'Le jeu de la bouteille'),
(239, 105, 0, 'Crapette'),
(240, 105, 0, 'La dame de fer');

-- --------------------------------------------------------

--
-- Structure de la table `theme`
--

CREATE TABLE `theme` (
  `theme_id` int(11) NOT NULL,
  `theme_nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `theme`
--

INSERT INTO `theme` (`theme_id`, `theme_nom`) VALUES
(1, 'Geographie'),
(2, 'Espace'),
(3, 'Histoire'),
(4, 'Langues'),
(5, 'Musique'),
(6, 'Jeux');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `utilisateur_id` int(11) NOT NULL,
  `utilisateur_pseudo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `partie`
--
ALTER TABLE `partie`
  ADD PRIMARY KEY (`partie_id`);

--
-- Index pour la table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`question_id`),
  ADD KEY `fk_Question_Theme` (`theme_id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`reponse_id`),
  ADD KEY `fk_Reponse_Question` (`question_id`);

--
-- Index pour la table `theme`
--
ALTER TABLE `theme`
  ADD PRIMARY KEY (`theme_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`utilisateur_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `partie`
--
ALTER TABLE `partie`
  MODIFY `partie_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `reponse_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=241;

--
-- AUTO_INCREMENT pour la table `theme`
--
ALTER TABLE `theme`
  MODIFY `theme_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `utilisateur_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fk_Reponse_Question` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
