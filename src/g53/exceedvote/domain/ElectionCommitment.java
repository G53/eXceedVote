package g53.exceedvote.domain;

/**
 * Class ElectionCommitment contains the information of Election Commitment
 */

/**
 * @author Metas Pongmetha 5310546529
 * @Version 2012.November.16
 */
public class ElectionCommitment {

	private long ECID;
	private String userName;
	private String password;

	/**
	 * Initialize a new Election Commitment
	 * 
	 * @param id
	 *            - Id number of Election Commitment
	 * @param user
	 *            - username of Election Commitment
	 * @param pass
	 *            - password of Election Commitment
	 */
	public ElectionCommitment(long id, String user, String pass) {
		this.ECID = id;
		this.userName = user;
		this.password = pass;
	}

	/**
	 * return username of Election Commitment
	 * 
	 * @return userName - username of Election Commitment
	 */
	public String getName() {
		return userName;
	}

	/**
	 * return ID number of Election Commitment
	 * 
	 * @return ECID - ID number of Election Commitment
	 */
	public long getId() {
		return ECID;
	}

	/**
	 * return password of Election Commitment
	 * 
	 * @return password - password of Election Commitment
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set password of Election Commitment
	 * 
	 * @parm newPass - new password of Election Commitment
	 */
	public void setPassword(String newPass) {
		password = newPass;
	}

}
