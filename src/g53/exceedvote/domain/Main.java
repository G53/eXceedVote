package g53.exceedvote.domain;

import java.sql.SQLException;

import g53.exceedvote.persistence.VoterDao;
import g53.exceedvote.persistence.jdbc.VoterDaoJdbc;
import g53.exceedvote.ui.LoginUI;
import g53.exceedvote.ui.VoteUI;





/**
 * 
 */

/**
 * @author Metas
 * 
 */
public class Main{

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws InterruptedException,
			SQLException {
		VoterDao voterDB = new VoterDaoJdbc();
		voterDB.LoadDriver();
		voterDB.connect();
		LoginUI loginUI = new LoginUI(voterDB);
		VoteUI voteUI = new VoteUI(voterDB);
		loginUI.run();
		while (!loginUI.getSatus()) {
			Thread.sleep(500);
		}
		voteUI.run();

	}
	

}
