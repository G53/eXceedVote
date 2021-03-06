package g53.exceedvote.domain;

/**
 * Class Question is criteria to vote which project should deserve score form voter
 */

/**
 * @author 	Kamolporn Sanamlao 5310545557
 * @Version 2012.November.15
 */
public class Question {
	/** The criteria to vote the project */
	private String question;
	/** The id of question */
	private int questionID;

	/**
	 * Initialize a new Question
	 * @param question - criteria to vote the project
	 */
	public Question(int questionID, String question) {
		this.question = question;
		this.questionID = questionID;
	}

	/**
	 * To setting the question to vote for project
	 * @param question is criteria to vote the project
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * To get the id of each question return if of question
	 */
	public int getQuestionID() {
		return questionID;
	}

	/**
	 * To get question for voting project
	 * @return question of voting project
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * Display the question
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return question;
	}
}