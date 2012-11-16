package g53.exceedvote.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import g53.exceedvote.domain.Project;
import g53.exceedvote.domain.Question;
import g53.exceedvote.domain.Voter;
import g53.exceedvote.persistence.DaoFactory;
import g53.exceedvote.persistence.VoterDao;

public class Controller {
	private VoterDao vote;

	public Controller() {
		vote = DaoFactory.getInstance().getVoterDao();
	}

	public ArrayList<Project> getProject() {
		return vote.getProject();
	}

	public ArrayList<Question> getQuestion() {
		return vote.getQuestion();
	}

	public void connect() {
		vote.LoadDriver();
		vote.connect();
	}

	public String getCurMessage() {
		return vote.getMessage();
	}

	public boolean login(String userName, String inputField2) {
		return vote.logIn(vote.getVoter(userName, inputField2));
	}

	public boolean canInsert(int user_id, int project_id, int question_id,
			int score, Timestamp votetime) {
		return vote.canInsert(user_id,project_id,question_id,score,votetime);
	}
	
	public Voter getVoter(){
		return vote.getVoter();
	}
	
	public void insertVoteDB(int user_id, int project_id, int question_id,
			int score, Timestamp voteTime){
		vote.insertVoteDB(user_id, project_id, question_id, score, voteTime);
	}
}
