package g53.exceedvote.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author 	Kamolporn Sanamlao 5310545557
 * @Version 2012.November.15
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
	/** Picture of Project Team **/
	private File file;
	// get BinaryStream of Image
	private InputStream birStream;
	private BufferedImage buffimg = null;
	
	/**
	 * Initialize a new ProjectDescription
	 * @param name is name of project
	 * @param teamName is name of team who develops the project 
	 * @param projectID is id of project
	 */
		public Project(long projectID, String name, String teamName, InputStream birStream){
		this.projectName = name;
		this.teamName = teamName;
		this.projectID = projectID;
		this.birStream = birStream;
	}
	
	public BufferedImage getImage() {
		try {
			buffimg = ImageIO.read(birStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffimg;
	}

	/**
	 * Display the string to shoe the description of project
	 */
	public String toString(){
		return "Name: " + projectName + " Team name: " + teamName +	" Project ID: " + projectID;
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
