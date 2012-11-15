package g53.exceedvote.domain;

import java.sql.SQLException;

import g53.exceedvote.persistence.VoterDao;
import g53.exceedvote.persistence.jdbc.VoterDaoJdbc;
import g53.exceedvote.ui.LoginUI;
import g53.exceedvote.ui.VoteUI;

/**
 * 	Class Main create a GUI for program and call method run from LoginUI class
 *  for run this program.
 */

/**
 * @author Metas Pongmetha 5310546529
 * @Version November.15.2012
 */
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws InterruptedException,
			SQLException {
		
		LoginUI loginUI = new LoginUI();
		VoteUI voteUI = new VoteUI();
		loginUI.run();
		while (!loginUI.getSatus()) {
			Thread.sleep(500);
		}
		voteUI.run();

	}

}
