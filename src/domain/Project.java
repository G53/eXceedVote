package domain;
/**
 * Project contains the information and vote score for each project
 * 
 * @author Kamolporn Sanamlao
 * @version 2012.10.05
 *
 */
public class Project {
	/** Name of project */
	private String projectName;
	/** Name of team who develops the project */
	private String teamName;
	/** Id of project */
	private long projectID;
	/** number of vote score */
	private int score;
	
	/**
	 * Initialize a new ProjectDescription
	 * @param name is name of project
	 * @param teamName is name of team who develops the project 
	 * @param projectID is id of project
	 */
	public Project(long projectID, String name, String teamName ){
		this.projectName = name;
		this.teamName = teamName;
		this.projectID = projectID;
	}
	
	/**
	 * Display the string to shoe the description of project
	 */
	public String toString(){
		return "Name: " + projectName + "Team name: " + teamName +	"Project ID: " + projectID;
	}
	
	/**
	 * To get id of project
	 * @return id of project
	 */
	public long getID(){
		return projectID;
	}
	
	/**
	 * To get name of project
	 * @return name of project
	 */
	public String getProjectName(){
		return projectName;
	}
	
	/**
	 * To get team name of project
	 * @return team name of project
	 */
	public String getTeamName(){
		return teamName;
	}
	
	/**
	 * To get number of vote score
	 * @return number of vote score
	 */
	public int getScore(){
		return score;
	}
}
