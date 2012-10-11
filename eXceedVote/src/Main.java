import java.sql.SQLException;

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
		VoterDB voterDB = new VoterDB();
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
