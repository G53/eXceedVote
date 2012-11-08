package domain;
import java.sql.SQLException;

import persistence.VoterDaoJdbc;


import ui.LoginUI;
import ui.VoteUI;

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
		VoterDaoJdbc voterDB = new VoterDaoJdbc();
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
