/**
 * 
 */
package projetQuizz.modele;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

/**
 * @author autome edwin
 *
 */
class JDBCRequestsTest {

	/**
	 * Test method for
	 * {@link projetQuizz.modele.JDBCRequests#getThemeNameById(int)}.
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	void testGetThemeNameById() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		assertEquals("Geographie", JDBCRequests.getThemeNameById(1));
		assertEquals("Espace", JDBCRequests.getThemeNameById(2));
		assertEquals("Histoire", JDBCRequests.getThemeNameById(3));
	}

	/**
	 * Test method for {@link projetQuizz.modele.JDBCRequests#getUserNameById(int)}.
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	void testGetUserNameById() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		assertEquals("Edwin", JDBCRequests.getUserNameById(1));
		assertEquals("Florent", JDBCRequests.getUserNameById(2));
		assertEquals("Gauthier", JDBCRequests.getUserNameById(3));
	}

	/**
	 * Test method for
	 * {@link projetQuizz.modele.JDBCRequests#userExist(java.lang.String)}.
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	void testUserExist() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		assertTrue(JDBCRequests.userExist("Edwin"));
		assertTrue(JDBCRequests.userExist("Florent"));
		assertTrue(JDBCRequests.userExist("Gauthier"));
	}

	/**
	 * Test method for
	 * {@link projetQuizz.modele.JDBCRequests#getUserInfos(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 */
	@Test
	void testGetUserInfos() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Utilisateur user = new Utilisateur("Edwin", 1);
		assertEquals(user, JDBCRequests.getUserInfos("Edwin"));
	}

}
