
/**
 * Question is criteria to vote which project should deserve score form voter
 * 
 * @author Kamolporn Sanamlao 
 * @version 2012.10.05
 *
 */
public class Question {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return question;
	}

	/** The criteria to vote the project */
	private String question;
	
	private long questionID;
	/**
	 * Initialize a new Question   
	 * @param question - criteria to vote the project
	 */
	public Question (long questionID,String question){
		this.question = question;
		this.questionID = questionID;
	}
	
	/**
	 * To setting the question to vote for project 
	 * @param question - criteria to vote the project 
	 */
	public void setQuestion(String question){
		this.question = question;
	}
	
	public long getQuestionID(){
		return questionID;
	}
	
	/**
	 * To get question for voting project
	 * @return question of voting project  
	 */
	public String getQuestion(){
		return question;
	}
	
	
}
