-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 14 déc. 2019 à 18:39
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
CREATE DATABASE IF NOT EXISTS `projetjava` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projetjava`;

-- --------------------------------------------------------

--
-- Structure de la table `partie`
--

CREATE TABLE `partie` (
  `partie_id` int(11) NOT NULL,
  `utilisateur_id` int(11) NOT NULL,
  `dateEtHeure` datetime NOT NULL DEFAULT current_timestamp(),
  `partie_difficulte` varchar(20) NOT NULL,
  `theme_id` int(11) NOT NULL,
  `partie_score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `partie`
--

INSERT INTO `partie` (`partie_id`, `utilisateur_id`, `dateEtHeure`, `partie_difficulte`, `theme_id`, `partie_score`) VALUES
(6, 3, '2019-12-11 20:34:16', 'facile', 1, 1),
(7, 3, '2019-12-11 20:34:21', 'facile', 1, 2),
(8, 3, '2019-12-11 20:34:26', 'facile', 1, 3),
(9, 3, '2019-12-11 20:34:31', 'facile', 1, 4),
(10, 3, '2019-12-11 20:34:37', 'facile', 1, 5),
(11, 3, '2019-12-11 20:34:41', 'facile', 1, 6),
(12, 3, '2019-12-11 20:34:44', 'facile', 1, 7),
(13, 3, '2019-12-11 20:34:48', 'facile', 1, 8),
(14, 3, '2019-12-11 20:34:52', 'facile', 1, 9),
(15, 3, '2019-12-11 20:34:55', 'facile', 1, 10),
(16, 3, '2019-12-11 20:34:59', 'facile', 1, 11);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `theme_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(13, 'Quel est le pays le plus peuplé au monde ?', 1),
(46, 'Dans quel pays se situe la ville d\'Edimbourg ?', 1),
(47, 'Sur quel continent trouve-t-on le désert de Gobi ?', 1),
(48, 'Dans quel pays trouve-t-on la région autonome du Tibet ?', 1),
(49, 'Quel phénomène catastrophique de mouvement d\'eau peut apparaître après un séisme ?', 1),
(50, 'Quel océan borde L\'île de la Réunion ?', 1),
(51, 'Quel sommet est le plus haut du monde ?', 1),
(52, 'Comment appelle-t-on l\'étendue d\'eau de mer gelée située au pôle nord ?', 1),
(53, 'Quel continent n\'est qu\'un désert ?', 1),
(54, 'La fusion de quel élément chimique se déroule actuellement au coeur du soleil ?', 2),
(55, 'Quel est le nom de l\'étoile la plus proche de la Terre ?', 2),
(56, 'Le fond diffus cosmologique est le résidu de quel évènement de notre univers ?', 2),
(57, 'Le centre de notre galaxie en a un supermassif. De quoi s\'agit-il ?', 2),
(58, 'Sur quel satellite de Saturne a-t-on pu observé des lacs de méthane liquide ?', 2),
(59, 'Combien de satellite(s) naturel(s) possède la Terre ?', 2),
(60, 'Quel planète est la plus proche du soleil ?', 2),
(61, 'Quel type d\'étoile deviendra notre soleil après son implosion en supernova ?', 2),
(62, 'Qui a mis en place la loi de la relativité général ?', 2),
(63, 'Quelle constellation est connue pour ses 3 étoiles formant une ceinture et sa nébuleuse ?', 2),
(64, 'Deux galaxies naines proche de la voie lactée ont été nommé en l\'honneur d un célèbre navigateur, lequel?', 2),
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
(89, 'De quel catégorie d\'instrument l\'harmonica fait-il partie ?', 5),
(90, 'De quel pays le violon est-il originaire ?', 5),
(91, 'Combien de cases contient un Puissance 4 ?', 6),
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
(102, 'Avec combien de carte joue-t-on à la belote ?', 6),
(103, 'Au loup-garou, qui lie 2 amoureux jusqu\'à la fin de la partie ?', 6),
(104, 'Quel dessin animé s\'est inspiré des toupies comme univers ?', 6),
(105, 'Quel \"jeu\" pouvant engendrer la mort se joue avec un revolver ?', 6),
(106, 'Quel événement marque le passage des Temps Modernes à l\'Epoque Contemporaine ?', 3),
(107, 'En quelle année débute la Seconde Guerre Mondiale ?', 3),
(108, 'En quelle année a-t-on marché pour la première fois sur la Lune ?', 3),
(109, 'En quelle année s\'est déroulée la Bataille de Waterloo ?', 3),
(110, 'Quel événement marque la fin de la Préhistoire ?', 3),
(111, 'Quelle est la date d\'indépendance de la Belgique ?', 3),
(112, 'Quel célèbre Italien a peint la Joconde (à la française) ?', 3),
(113, 'Comment surnommait-on les soldats de la Première Guerre Mondiale ?', 3),
(114, 'Quel roi a rendu l\'école obligatoire ?', 3),
(115, 'Quelle est la plus connue des reines de l\'Egypte antique notamment pour avoir épousée Jules César ?', 3),
(116, 'Quel célèbre ordre religieux et militaire est connu pour ses croisades ?', 3),
(117, 'En quelle année fut larguée la première bombe atomique ?', 3),
(118, 'Quel chef politique romain fut assassiné par de multiples coup de poignards ?', 3),
(119, 'En quelle année est tombé le mur de Berlin ?', 3),
(120, 'Qui a découvert l\'Amérique (nom de famille seulement) ?', 3),
(121, 'Quelle est la langue officielle du Mexique ?', 4),
(122, 'Quelle est la langue la plus parlé dans le monde ?', 4),
(123, 'De quel continent est originaire le japonais ?', 4),
(124, 'Dans quelle région parle-t-on le Maori ?', 4),
(125, 'Quelle est la 3e langue officielle de la Belgique (après le français et le néerlandais) ?', 4),
(126, 'Quelle langue est considérée comme langue internationale ?', 4),
(127, 'Quelle est cette langue: \"Hallo wie geht es dir ?\" ?', 4),
(128, 'Quelle est cette langue: \"No se como se llama\" ?', 4),
(129, 'Quelle est la langue officielle en France ?', 4),
(130, 'Dans la célèbre saga Harry Potter, comment s\'appelle la langue des serpents ?', 4),
(131, 'Quelle langue parle-t-on dans le pays de Putin ?', 4),
(132, 'Quelle langue parlent les extraterrestes dans le film Avatar ?', 4),
(133, 'Comment s\'écrit \"Bonjour\" en anglais ?', 4),
(134, 'Quelle langue est parlée par les petits bonshommes bleus de Peyo ?', 4),
(135, 'Quelle langue parlait le célèbre inventeur et peintre Léonard De Vinci ?', 4);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `reponse_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `estBonneReponse` tinyint(4) NOT NULL,
  `reponse` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(35, 13, 0, 'Etats-Unis/USA'),
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
(62, 52, 0, 'Glacier'),
(63, 52, 0, 'Iceberg'),
(64, 52, 0, 'Poudreuse'),
(65, 53, 1, 'Antarctique'),
(66, 53, 0, 'Océanie'),
(67, 53, 0, 'Afrique'),
(68, 53, 0, 'Asie'),
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
(136, 79, 0, 'On m appelle l\'OVNI'),
(137, 80, 1, 'Despacito'),
(138, 80, 0, 'Shape of you'),
(139, 80, 0, 'Gangnam style'),
(140, 80, 0, 'Baby shark'),
(141, 81, 1, '1975'),
(142, 81, 0, '1980'),
(143, 81, 0, '1969'),
(144, 81, 0, '1971'),
(145, 82, 1, 'Sexion d\'assaut'),
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
(181, 91, 1, '42'),
(182, 91, 0, '49'),
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
(221, 101, 1, 'Etats-Unis/USA'),
(222, 101, 0, 'Canada'),
(223, 101, 0, 'Angleterre'),
(224, 101, 0, 'Australie'),
(225, 102, 1, '32'),
(226, 102, 0, '52'),
(227, 102, 0, '26'),
(228, 102, 0, '40'),
(229, 103, 1, 'Cupidon'),
(230, 103, 0, 'Aphrodite'),
(231, 103, 0, 'La voyante'),
(232, 103, 0, 'La sorcière'),
(233, 104, 1, 'Beyblade'),
(234, 104, 0, 'Bakugan'),
(235, 104, 0, 'Pokemon'),
(236, 104, 0, 'Code Lyoko'),
(237, 105, 1, 'La roulette russe'),
(238, 105, 0, 'Le jeu de la bouteille'),
(239, 105, 0, 'Crapette'),
(240, 105, 0, 'La dame de fer'),
(492, 106, 1, 'La Révolution française'),
(493, 106, 0, 'L\'invention de l\'imprimerie'),
(494, 106, 0, 'La Chute du Mur de Berlin'),
(495, 106, 0, 'Le concert de Queen à Wembley'),
(496, 107, 1, '1939'),
(497, 107, 0, '1944'),
(498, 107, 0, '1961'),
(499, 107, 0, '1957'),
(500, 108, 1, '1969'),
(501, 108, 0, '1944'),
(502, 108, 0, '1961'),
(503, 108, 0, '1957'),
(504, 109, 1, '1815'),
(505, 109, 0, '1830'),
(506, 109, 0, '1802'),
(507, 109, 0, '1799'),
(508, 110, 1, 'L\'invention de l\'écriture'),
(509, 110, 0, 'L\'invention de la roue'),
(510, 110, 0, 'Le premier homme dans l\'espace'),
(511, 110, 0, 'La mort de Rasputin'),
(512, 111, 1, '1830'),
(513, 111, 0, '1815'),
(514, 111, 0, '1789'),
(515, 111, 0, '1800'),
(516, 112, 1, 'Léonard de Vinci'),
(517, 112, 0, 'Léonardo DiCaprio'),
(518, 112, 0, 'Vincent Van Gogh'),
(519, 112, 0, 'Salvador Dali'),
(520, 113, 1, 'Les poilus'),
(521, 113, 0, 'Les chairs à canons'),
(522, 113, 0, 'Les retranchés'),
(523, 113, 0, 'Les planqués'),
(524, 114, 1, 'Charlemagne'),
(525, 114, 0, 'Louis XIV'),
(526, 114, 0, 'Henri IV'),
(527, 114, 0, 'Emmanuel Macron'),
(528, 115, 1, 'Cleopatre'),
(529, 115, 0, 'Nefertiti'),
(530, 115, 0, 'Elisabeth 2'),
(531, 115, 0, 'Toutankhamon'),
(532, 116, 1, 'Les Templiers'),
(533, 116, 0, 'Les Franc-maçons'),
(534, 116, 0, 'Les Illuminati'),
(535, 116, 0, 'Le Ku Klux Klan'),
(536, 117, 1, '1945'),
(537, 117, 0, '1918'),
(538, 117, 0, '1961'),
(539, 117, 0, '1946'),
(540, 118, 1, 'Jules César'),
(541, 118, 0, 'Néron de Cicéron'),
(542, 118, 0, 'Alexandre le Grand'),
(543, 118, 0, 'Brutus'),
(544, 119, 1, '1991'),
(545, 119, 0, '1961'),
(546, 119, 0, '1969'),
(547, 119, 0, '1989'),
(548, 120, 1, 'Colomb'),
(549, 120, 0, 'Magellan'),
(550, 120, 0, 'Cartier'),
(551, 120, 0, 'Nimitz'),
(552, 121, 1, 'Espagnol'),
(553, 121, 0, 'Portugais'),
(554, 121, 0, 'Allemand'),
(555, 121, 0, 'Papou'),
(556, 122, 1, 'Chinois Mandarin'),
(557, 122, 0, 'Anglais'),
(558, 122, 0, 'Français'),
(559, 122, 0, 'Javanais'),
(560, 123, 1, 'Asie'),
(561, 123, 0, 'Europe'),
(562, 123, 0, 'Océanie'),
(563, 123, 0, 'Amérique du Sud'),
(564, 124, 1, 'Nouvelle-Zélande'),
(565, 124, 0, 'Hawai'),
(566, 124, 0, 'Papouasie-Nouvelle-Guinée'),
(567, 124, 0, 'Polynésie française'),
(568, 125, 1, 'Allemand'),
(569, 125, 0, 'Arabe'),
(570, 125, 0, 'Turque'),
(571, 125, 0, 'Anglais'),
(572, 126, 1, 'Anglais'),
(573, 126, 0, 'Chinois'),
(574, 126, 0, 'Japonais'),
(575, 126, 0, 'Allemand'),
(576, 127, 1, 'Allemand'),
(577, 127, 0, 'Néerlandais'),
(578, 127, 0, 'Russe'),
(579, 127, 0, 'Polonais'),
(580, 128, 1, 'Espagnol'),
(581, 128, 0, 'Portugais'),
(582, 128, 0, 'Italien'),
(583, 128, 0, 'Allemand'),
(584, 129, 1, 'Français'),
(585, 129, 0, 'Anglais'),
(586, 129, 0, 'Allemand'),
(587, 129, 0, 'Swahili'),
(588, 130, 1, 'Fourchelang'),
(589, 130, 0, 'Klingon'),
(590, 130, 0, 'Dothraki'),
(591, 130, 0, 'Quenya'),
(592, 131, 1, 'Russe'),
(593, 131, 0, 'Polonais'),
(594, 131, 0, 'Javanais'),
(595, 131, 0, 'Allemand'),
(596, 132, 1, 'Na\'vi'),
(597, 132, 0, 'Klingon'),
(598, 132, 0, 'Pandore'),
(599, 132, 0, 'Schtroumpf'),
(600, 133, 1, 'Hello'),
(601, 133, 0, 'Hallo'),
(602, 133, 0, 'Goeidag'),
(603, 133, 0, 'Ola'),
(604, 134, 1, 'Schtroumpf'),
(605, 134, 0, 'Fourchelang'),
(606, 134, 0, 'Klingon'),
(607, 134, 0, 'Allemand'),
(608, 135, 1, 'Italien'),
(609, 135, 0, 'Portuguais'),
(610, 135, 0, 'Espagnol'),
(611, 135, 0, 'Grec');

-- --------------------------------------------------------

--
-- Structure de la table `theme`
--

CREATE TABLE `theme` (
  `theme_id` int(11) NOT NULL,
  `theme_nom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`utilisateur_id`, `utilisateur_pseudo`) VALUES
(1, 'Edwin'),
(2, 'Florent'),
(3, 'Gauthier');

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
  ADD PRIMARY KEY (`question_id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`reponse_id`);

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
  MODIFY `partie_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `question`
--
ALTER TABLE `question`
  MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=136;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `reponse_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=612;

--
-- AUTO_INCREMENT pour la table `theme`
--
ALTER TABLE `theme`
  MODIFY `theme_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `utilisateur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
