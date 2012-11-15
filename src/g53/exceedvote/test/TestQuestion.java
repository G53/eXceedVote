package g53.exceedvote.test;

import g53.exceedvote.domain.Question;
import junit.framework.TestCase;

/**
 * This class is about testing a Question Object
 */
/**
 * @author 	Wasupol Tungsakulthong 5310547304 
 * @Version 2012.November.15
 */
public class TestQuestion extends TestCase {
	// relate attribute	to g53.exceedvote.test
	private Question q  = new Question(1,"What your Name?");
	private String newQuestion = "What is your name?";

	/**
	 *  Test object have to be created
	 */
	public void testQuestion() {
		Question tempq = q;
		assertSame(tempq, q);
		Question temp = null;
		assertNotSame(q, temp);
		assertNotNull(q);
	}

	/**
	 * Test setQuestion method of Question Object.
	 */
	public void testSetQuestion() {
		Question tempqq = q;
		tempqq.setQuestion(newQuestion);
		assertFalse(q.getQuestion() != tempqq.getQuestion());
		assertEquals("What is your name?", tempqq.getQuestion());
	}

	/**
	 *  Test GetQuestionID method of Question Object.
	 */
	public void testGetQuestionID() {
		assertEquals(1, q.getQuestionID());
	}

	/**
	 * Test GetQuestion method of Question Object.
	 */
	public void testGetQuestion() {
		assertEquals("What your Name?", q.getQuestion());
	}

	/**
	 *  Test toString method of Question Object.
	 */
	public void testToString() {
		assertEquals("What your Name?",q.toString());
	}

}
