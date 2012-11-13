package g53.exceedvote.persistence;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import g53.exceedvote.domain.Project;
import g53.exceedvote.domain.Question;
import g53.exceedvote.domain.Voter;


public interface VoterDao {

	public abstract void LoadDriver();

	/**
	 * connect to database server
	 */
	public abstract void connect();

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
	 * return Voter object (voter that current login)
	 * @return voter - Voter object
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

}