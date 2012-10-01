/**
 * A class which separates each project has to be voted.     
 * 
 * @author Kamolporn Sanamlao 
 * @version 2012.10.01
 */
public class Project {

	
	/** ProjectDescription is a description of each project in camp */
	private ProjectDescription projectDescription;
	
	/**
	 * Initialize a new Project
	 * @param projectDescription - information for each project
	 */
	public Project(ProjectDescription projectDescription){
		this.projectDescription = projectDescription;
	}
	
	/**
	 * To get project's description for each project
	 * @return project's description
	 */
	public ProjectDescription getDescription(){
		return projectDescription;
	}
	
	/**
	 * To get the project by comparing with id
	 * @param id - id of project
	 * @return 
	 */
	public Project getProject(long id){
		if (id == projectDescription.getID())
			return project;
	}
}
