package g53.exceedvote.persistence;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import g53.exceedvote.domain.ElectionCommittee;
import g53.exceedvote.domain.Project;
import g53.exceedvote.domain.Question;
import g53.exceedvote.domain.Voter;

/**
 * Class VoterDao
 */

/**
 * @author	 Metas Pongmetha 5310546529
 * @Version	 2012.November.15
 */

public interface VoterDao {

	/**
	 *  loading Driver of MySQL database
	 * @return 
	 */
	public abstract boolean LoadDriver();

	/**
	 * connect to database server
	 * @return 
	 */
	public abstract boolean connect();

	/**
	 * access and get question from database server 
	 * @return arraylist of question
	 */
	public abstract ArrayList<Question> getQuestion();

	/**
	 * access and get project from database server 
	 * @return arraylist of project
	 */
	public abstract ArrayList<Project> getProject();

	/**
	 * check username and password of voter from database server that exist in database or not
	 * @param user - username of voter
	 * @param pass - password of voter 
	 * @return Voter object of voter that contain all information of voter from database
	 * Voter object = null - if username and password not exist in database
	 */
	public abstract Voter getVoter(String user, String pass);

	/**
	 * check login with Voter object that login success or fail
	 * @param voter - Voter object
	 * @return false - if Voter object = null
	 * true - otherwise 
	 */
	public abstract boolean logIn(Voter voter);

	/**
	 * submit result that voter select (username , project Id, question Id ,nuymber of score , time of vote)
	 * @param user_id - user Id of voter
	 * @param project_id - project Id that user vote
	 * @param question_id - question Id that user select
	 * @param score - number of score that voter vote
	 * @param voteTime - time that submit to server
	 */
	public abstract void insertVoteDB(int user_id, int project_id,
			int question_id, int score, Timestamp voteTime);

	/**
	 * check that voter had voted the same thing(same project and question) before or not
	 * @param user_id - user Id of voter
	 * @param project_id - project Id that user vote
	 * @param question_id - question Id that user select
	 * @param score - number of score that voter vote
	 * @param voteTime - time that submit to server
	 * @return false - if voter had voted this thing(same project and question) before
	 * true - otherwise
	 */
	public abstract boolean canInsert(int user_id, int project_id,
			int question_id, int score, Timestamp votetime);

	/**
	 * create a table model of database to show as a result to user in form of JTable.
	 * @param model - TableModel object which create from instance of database of voting system.
	 * @return model of containing data of which project have been voted.
	 * @throws SQLException
	 */
	public abstract DefaultTableModel voteResult(DefaultTableModel model)
			throws SQLException;

	/**
	 * return All Voter object (which in database)
	 * @return Array of voters - Voter object
	 */
	public abstract ArrayList<Voter> getAllVoter();

	/**
	 * return voter which currently log on
	 * @return voter object
	 */
	public abstract Voter getVoter();
	/**
	 * return message log for display in UI
	 * @return messageLog
	 */
	public abstract String getMessage();

	/**
	 * disconnect from database sever
	 */
	public abstract void close() throws SQLException;

	/**
	 * Insert Voter or new Voter to the database
	 * @param vote - voter object
	 */
	public abstract void insertVoter( Voter vote );

	/**
	 * Check that User or ID is contained in database which can insert or not
	 * @param id - ID of Voter
	 * @param name - Username of Voter
	 * @return true if can insert or false if not
	 */
	public abstract boolean canInsertVoter(long id, String name);
	
	public abstract ElectionCommittee getElectionCommittee(String user,String pass);

	public abstract boolean logIn(ElectionCommittee electionCommittee);
}