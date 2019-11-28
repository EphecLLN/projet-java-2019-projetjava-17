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
	private String[] themes = {"G�ographie","L'espace","l'Histoire","Les langues"};
	
	public Repertoire() {
		this.geo.put("1", new String[] {"Quelle est la capitale de la Mongolie ?","Oulan-Bator","Kaboul","Bagdad","Douchanb�"});
		this.geo.put("2", new String[] {"Quel tropique se trouve dans l'h�misph�re nord ?","Cancer","Capricorne","Equateur","Greenwich"});
		this.geo.put("3", new String[] {"Quelle mer de l'oc�an Arctique borde la Sib�rie ?","Mer des Tchouktches","Mer Noire","Golfe de Gascogne" ,"Mer des Sargasses"});
		
		
		this.espace.put("1", new String[] {"Quel est l'�ge approximatif de l'univers (en milliards d'ann�es) ?","14","25","2","60"});
		this.espace.put("2", new String[] {"Qui a observ� l'�loignement des galaxies grace au d�calage spectral (nom uniquement)?","Hubble","Einstein","Planck","Hawking"});
		this.espace.put("3", new String[] {"A combien de secondes lumi�res se situent-ont de la lune ?","1.28","6.49","19.87","13.33"});
		
		
		this.histoire.put("1", new String[] {"Quel �v�nement marque le passage des Temps Modernes � l'Epoque Contemporaine ?","La R�volution fran�aise",
				"L'invention de l'imprimerie", "La Chute du Mur de Berlin", "Le concert de Queen � Wembley"});
		this.histoire.put("2", new String[] {"En quelle ann�e d�bute la Seconde Guerre Mondiale ?","1939","1944", "1961", "1957"});
		this.histoire.put("3", new String[] {"En quelle ann�e a-t-on march� pour la premi�re fois sur la Lune ?","1969","1944", "1961", "1957"});
		
		this.langues.put("1", new String[] {"Quelle est la langue officielle du Mexique ?","Espagnol","Portuguais", "Allemand", "Papou"});
		this.langues.put("2", new String[] {"Quelle est la langue la plus parl� dans le monde ?","Chinois Mandarin","Anglais", "Fran�ais", "Javanais"});
		this.langues.put("3", new String[] {"Sur quel continent parle-t-on le japonais ?","Asie","Europe", "Oc�anie", "Am�rique du Sud"});
		
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
