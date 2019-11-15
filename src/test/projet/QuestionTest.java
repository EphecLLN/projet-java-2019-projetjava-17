/**
 * 
 */
package projet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Gauthier Verschraegen
 *
 */
class QuestionTest {

	/**
	 * Test method for {@link projet.Question#genererQuestion(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testGenererQuestion() {
		Question maQuest = new Question();
		assertEquals(maQuest.genererQuestion("Carre", "Moi"),1);
		assertEquals(maQuest.genererQuestion("Carre", "Lui"),0);
		assertEquals(maQuest.genererQuestion("Cash", "Moi"),3);
		assertEquals(maQuest.genererQuestion("Cash", "Lui"),0);
	}
}
