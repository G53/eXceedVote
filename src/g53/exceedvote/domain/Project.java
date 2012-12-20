package g53.exceedvote.domain;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	// get BinaryStream of Image
	private InputStream birStream;
	/** Icon Object of Picture **/
	private ImageIcon pictureImage;
	/** Image Object of Picture **/
	private Image img;
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
		try {
			img = ImageIO.read(birStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pictureImage = new ImageIcon(img);
	}
	
	public ImageIcon getImage() {
		return pictureImage;
	}

	/**
	 * Display the string to shoe the description of project
	 */
	public String toString(){
		return projectName;
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
	public InputStream getBirStream() {
		return birStream;
	}
}
