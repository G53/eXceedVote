/**
 * ProjectDescription contains the information for each project
 * 
 * @author Kamolporn Sanamlao
 * @version 2012.10.01
 *
 */
public class ProjectDescription {
	/** Name of project */
	private String name;
	/** Name of team who develops the project */
	private String teamName;
	/** Id of project */
	private long projectID;
	
	/**
	 * Initialize a new ProjectDescription
	 * @param name - name of project
	 * @param teamName - name of team who develops the project 
	 * @param projectID - id of project
	 */
	public ProjectDescription(String name, String teamName, long projectID){
		this.name = name;
		this.teamName = teamName;
		this.projectID = projectID;
	}
	
	/**
	 * Display the string to shoe the description of project
	 */
	public String toString(){
		return "Name: " + name + "Team name: " + teamName +	"Project ID: " + projectID;
	}
	
	/**
	 * To get id of project
	 * @return id of project
	 */
	public long getID(){
		return projectID;
	}
}
