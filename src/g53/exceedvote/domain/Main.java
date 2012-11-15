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
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		
		LoginUI loginUI = new LoginUI();
		VoteUI voteUI = new VoteUI();
		loginUI.run();
		while (!loginUI.getSatus()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		voteUI.run();

	}

}
