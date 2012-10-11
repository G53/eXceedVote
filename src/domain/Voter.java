package domain;
/**
 * 
 */

/**
 * @author Metas
 *
 */
public class Voter {

	private long voteID;
	
	private String userName = "username";
	
	private String password = "password";
	
	/**
	 * @param args
	 */
	public Voter(long id,String user,String pass){
		this.voteID = id;
		this.userName = user;
		this.password = pass;
	}
	
	public String getName() {
		return userName;
	}
	
	public long getId() {
		return voteID;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String newString){
		password = newString;
	}
	
	

}
