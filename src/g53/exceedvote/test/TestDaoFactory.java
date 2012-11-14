package g53.exceedvote.test;

import java.util.ArrayList;

import g53.exceedvote.domain.*;
import g53.exceedvote.persistence.*;

public class TestDaoFactory {

	private Voter voter;
	private ArrayList<Project> project;
	private ArrayList<Question> questions;
	private VoterDao dao;
	public void testDaoFactory() {
		dao = DaoFactory.getInstance().getVoterDao();
		voter = dao.getVoter();
		project = dao.getProject();
		questions = dao.getQuestion();
	}

	public void testVoter() {
		System.out.printf("%s %s/n",voter.getId(),voter.getName());
	}

	public void testProject() {
		for (Project p : project) {
			System.out.printf("%s %s %s/n",p.getID(),p.getProjectName(),p.getTeamName());
		}
		
	}

	public void testQuestion() {
		for (Question q : questions) {
			System.out.printf("%s %s /n",q.getQuestionID(),q.getQuestion());
		}
	}

	public static void main(String[] args) {
		TestDaoFactory test = new TestDaoFactory();
		test.testDaoFactory();
		test.testVoter();
		test.testProject();
		test.testQuestion();
	}

}
