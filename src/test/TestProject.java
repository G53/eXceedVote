package test;

import domain.Project;
import junit.framework.TestCase;

/**
 * This class is about testing a Project Object
 * @author Wasupol Tungsakultong
 * @version 1.0
 */
public class TestProject extends TestCase {
	// relate attribute	to test
	private Project p  = new Project(1, "Something", "Someone");
	/**
	 *  Test object have to be created
	 */
	public void testProject() {
		Project tempp = p;
		assertSame(tempp, p);
		Project temp = null;
		assertNotSame(p, temp);
		assertNotNull(p);
	}

	/**
	 *  Test toString method of Project Object.
	 */
	public void testToString() {
		assertEquals("Name: "+ p.getProjectName() +" Team name: " + p.getTeamName() + " Project ID: "+ p.getID(), p.toString());
	}

	/**
	 *  Test getID method of Project Object.
	 */
	public void testGetID() {
		assertEquals(1, p.getID());
	}

	/**
	 * Test getProjectName method of Project Object.
	 */
	public void testGetProjectName() {
		assertEquals("Something", p.getProjectName());
	}

	/**
	 * Test gewtTeamName method of Project Object.
	 */
	public void testGetTeamName() {
		assertEquals("Someone", p.getTeamName());
	}

}
