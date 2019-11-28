/**
 * 
 */
package projet;
import java.util.*;
/**
 * @author Gauthier Verschraegen
 *
 */
public class Repertoire {
	
	private Map<String, String[]> geo  = new HashMap<String, String[]>();
	private Map<String, String[]> espace  = new HashMap<String, String[]>();
	private Map<String, String[]> tech  = new HashMap<String, String[]>();
	private Map<String, String[]> musique  = new HashMap<String, String[]>();
	private Map<String, String[]> histoire  = new HashMap<String, String[]>();
	private Map<String, String[]> langues  = new HashMap<String, String[]>();
	
	private List<Map<String, String[]>> repertoire = new ArrayList<Map<String, String[]>>();
	private String[] themes = {"Géographie","L'espace","l'Histoire","Les langues"};
	
	public Repertoire() {
		this.geo.put("1", new String[] {"Quelle est la capitale de la Mongolie ?","Oulan-Bator","Kaboul","Bagdad","Douchanbé"});
		this.geo.put("2", new String[] {"Quel tropique se trouve dans l'hémisphère nord ?","Cancer","Capricorne","Equateur","Greenwich"});
		this.geo.put("3", new String[] {"Quelle mer de l'océan Arctique borde la Sibérie ?","Mer des Tchouktches","Mer Noire","Golfe de Gascogne" ,"Mer des Sargasses"});
		
		
		this.espace.put("1", new String[] {"Quel est l'âge approximatif de l'univers (en milliards d'années) ?","14","25","2","60"});
		this.espace.put("2", new String[] {"Qui a observé l'éloignement des galaxies grace au décalage spectral (nom uniquement)?","Hubble","Einstein","Planck","Hawking"});
		this.espace.put("3", new String[] {"A combien de secondes lumières se situent-ont de la lune ?","1.28","6.49","19.87","13.33"});
		
		
		this.histoire.put("1", new String[] {"Quel événement marque le passage des Temps Modernes à l'Epoque Contemporaine ?","La Révolution française",
				"L'invention de l'imprimerie", "La Chute du Mur de Berlin", "Le concert de Queen à Wembley"});
		this.histoire.put("2", new String[] {"En quelle année débute la Seconde Guerre Mondiale ?","1939","1944", "1961", "1957"});
		this.histoire.put("3", new String[] {"En quelle année a-t-on marché pour la première fois sur la Lune ?","1969","1944", "1961", "1957"});
		
		this.langues.put("1", new String[] {"Quelle est la langue officielle du Mexique ?","Espagnol","Portuguais", "Allemand", "Papou"});
		this.langues.put("2", new String[] {"Quelle est la langue la plus parlé dans le monde ?","Chinois Mandarin","Anglais", "Français", "Javanais"});
		this.langues.put("3", new String[] {"Sur quel continent parle-t-on le japonais ?","Asie","Europe", "Océanie", "Amérique du Sud"});
		
		this.repertoire.add(geo);this.repertoire.add(espace);this.repertoire.add(histoire);this.repertoire.add(langues);
	}

	/**
	 * @return the repertoire
	 */
	public List<Map<String, String[]>> getRepertoire() {
		return repertoire;
	}

	/**
	 * @return the themes
	 */
	public String[] getThemes() {
		return themes;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
