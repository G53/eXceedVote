package g53.exceedvote.domain;

/**
 * Class Voter contains the information of voter, voter ID, username, password
 */

/**
 * @author 	Metas Pongmetha 5310546529
 * @Version 2012.November.15
 */
public class Voter {

	private long voteID; 
	private String userName;
	private String password;
	private long role_id;
	/**
	 * Initialize a new Voter
	 * @param id - Id number of voter
	 * @param user - username of voter
	 * @param pass - password of voter
	 */
	public Voter(long id,String user,String pass, long rote_id){
		this.voteID = id;
		this.userName = user;
		this.password = pass;
		this.role_id = rote_id;
	}
	
	public long getRole_id() {
		return role_id;
	}

	/**
	 * return username of voter
	 * @return userName - username of project
	 */
	public String getName() {
		return userName;
	}
	
	/**
	 * return ID number of voter
	 * @return voteID - ID number of voter
	 */
	public long getId() {
		return voteID;
	}
	
	/**
	 * return password of voter
	 * @return password - password of voter
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * set password of voter
	 * @parm newPass - new password of voter
	 */
	public void setPassword(String newPass){
		password = newPass;
	}
}
