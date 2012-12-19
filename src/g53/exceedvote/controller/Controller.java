package g53.exceedvote.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import g53.exceedvote.domain.Project;
import g53.exceedvote.domain.Question;
import g53.exceedvote.domain.RecordLog;
import g53.exceedvote.domain.Voter;
import g53.exceedvote.persistence.DaoFactory;
import g53.exceedvote.persistence.VoterDao;
/**
 * controller of a System by controlling between UI and Database
*/

/**
 * @author Metas Pongmetha 5310546529
 * @version 2012.November.18
 */
public class Controller extends RecordLog{
	private VoterDao vote;

	public Controller() {
		vote = DaoFactory.getInstance().getVoterDao();
	}
	
	/**
	 * call VoterDao to get project form database
	 * @return List of Project Object
	 */
	public ArrayList<Project> getProject() {
		return vote.getProject();
	}
	
	/**
	 * call VoterDao to get question form database
	 * @return List of Question Object
	 */
	public ArrayList<Question> getQuestion() {
		return vote.getQuestion();
	}
	
	/**
	 * call VoterDao to load driver and connect to database
	 * @return true if connect success , false if fail
	 */
	public boolean connect() {
		if(vote.LoadDriver() && vote.connect())return true;
		else return false;
		
		
	}

	/**
	 * call VoterDao to get current message log 
	 * @return message log from VoterDao
	 */
	public String getCurMessage() {
		return vote.getMessage();
	}

	/**
	 * call VoterDao to check login of voter 
	 * @param userName
	 * @param password
	 * @return true if connect success , false if fail
	 */
	public boolean login(String userName, String password) {
		return vote.logIn(vote.getVoter(userName, password));
	}
	
	public void recordLoginFail(String userName) {
		try {
			record(" Username: "+ userName + " - " + getCurMessage()+ " |IP: "+InetAddress.getLocalHost().getHostAddress().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * call VoterDao to check login of Election Committee
	 * @param userName
	 * @param password 
	 * @return true if connect success , false if fail
	 */
	public boolean loginElectionCommittee (String userName, String password) {
		return vote.logIn(vote.getElectionCommittee(userName, password));
	}
	
	/**
	 * call VoterDao to check that this user can summit this vote to database or not
	 * @param user_id
	 * @param project_id
	 * @param question_id
	 * @param score
	 * @param votetime
	 * @return  true if it can summit , false if not
	 */
	public boolean canInsert(int user_id, int project_id, int question_id,
			int score, Timestamp votetime) {
		return vote.canInsert(user_id,project_id,question_id,score,votetime);
	}
	
	/**
	 * call VoterDao to get voter from database
	 * @return Voter Object
	 */
	public Voter getVoter(){
		return vote.getVoter();
	}
	
	/**
	 * call VoterDao to summit vote to database 
	 * @param user_id
	 * @param project_id
	 * @param question_id
	 * @param score
	 * @param voteTime
	 */
	public void insertVoteDB(int user_id, int project_id, int question_id,
			int score, Timestamp voteTime){
		vote.insertVoteDB(user_id, project_id, question_id, score, voteTime);
	}

	public DefaultTableModel voteResult(DefaultTableModel model, int id) throws SQLException {
		// TODO Auto-generated method stub
		return vote.voteResult(model, id);
	}
	}
