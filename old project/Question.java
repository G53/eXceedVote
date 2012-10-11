/**
 * Question is criteria to vote which project should deserve score form voter
 * 
 * @author Kamolporn Sanamlao 
 * @version 2012.10.01
 *
 */
public class Question {

	/** The criteria to vote the project */
	private String question;
	
	/**
	 * Initialize a new Question   
	 * @param question - criteria to vote the project
	 */
	public Question (String question){
		this.question = question;
	}
	
	/**
	 * To setting the question to vote for project 
	 * @param question - criteria to vote the project 
	 */
	public void setQuestion(String question){
		this.question = question;
	}
	
	/**
	 * To get question for voting project
	 * @return question of voting project  
	 */
	public String getQuestion(){
		return question;
	}
	
	
}
