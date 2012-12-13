package g53.exceedvote.persistence.jdbc;


import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


import g53.exceedvote.domain.ElectionCommittee;
import g53.exceedvote.domain.Project;
import g53.exceedvote.domain.Question;
import g53.exceedvote.domain.RecordLog;
import g53.exceedvote.domain.Voter;
import g53.exceedvote.persistence.VoterDao;



/**
 * Class VoterDB is class that connect and get data from database 
 */

/**
 * @author 	Wasupol Tungsakulthong 5310547304 
 * @Version 2012.November.18
 */
public class VoterDaoJdbc extends RecordLog implements VoterDao {

	private static Connection con;
	private PreparedStatement pstmt;
	private static Statement stmt;
	private String messageLog;
	private Voter voter;
	private ElectionCommittee electionCommittee;
	private FileInputStream fis;

	/**
	 * Load the driver that is SQL or not
	 */
	@Override
	public boolean LoadDriver() {

		// Load the JDBC-ODBC bridge driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return true;
		} catch (ClassNotFoundException e) {
			record("Can't Find Database Driver");
			return false;
		}
	}

	/**
	 * connect to database server
	 */
	@Override
	public boolean connect() {

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exceed_vote", System.getProperty("user.name"), "");
			stmt = con.createStatement();
			// turn on autocommit
			con.setAutoCommit(true);
			record(messageLog = "Connected to database");
//			con.close();
//			record(messageLog = "Disconnected from database"); 
			return true;
		} catch (SQLException e) {
			record(messageLog = "Can not connect to database!");
			return false;
		}

	}

	/**
	 * access and get question from database server 
	 * @return arraylist of question
	 */
	@Override
	public ArrayList<Question> getQuestion() {
		String query; // SQL select string
		ResultSet rs; // SQL query results
		ArrayList<Question> arrQuestion = new ArrayList<Question>();
		query = "SELECT * FROM Question ";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				arrQuestion.add(new Question(rs.getInt("ID"), rs
						.getNString("questions")));
			}
			record("Access Question Database");
			return arrQuestion;
			// Loop through the rows retrieved from the query
		} catch (Exception e) {
			record("Can't Access Question Database");
		}
		return null;
	}

	/**
	 * access and get project from database server 
	 * @return arraylist of project
	 */
	@Override
	public ArrayList<Project> getProject() {
		String query; // SQL select string
		ResultSet rs; // SQL query results
		ArrayList<Project> arrProject = new ArrayList<Project>();
		query = "SELECT * FROM Project ";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				arrProject.add(new Project(rs.getInt("ID"), rs.getNString("name"), rs.getNString("teamname"),rs.getBinaryStream("Pictures")));
			}
			record("Access Project Database");
			return arrProject;
			// Loop through the rows retrieved from the query
		} catch (Exception e) {
			record("Can't access Project Database");
		}
		return null;
	}

	/**
	 * check username and password of voter from database server that exist in database or not
	 * @param user - username of voter
	 * @param pass - password of voter 
	 * @return Voter object of voter that contain all information of voter from database
	 * Voter object = null - if username and password not exist in database
	 */
	@Override
	public Voter getVoter(String user, String pass) {
		String query; // SQL select string
		ResultSet rs; // SQL query results
		query = "SELECT * FROM User WHERE role_id = 1 or role_id = 2";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (user.equalsIgnoreCase(rs.getString("username"))
						&& pass.equals(rs.getString("password"))) {
					return new Voter(rs.getInt("ID"), user, pass,rs.getInt("role_id"));
				}
			}
			// Loop through the rows retrieved from the query
		} catch (Exception e) {
			record("Can't get User from Database");
		}
		
		return null;
	}

	/**
	 * check login with Voter object that login success or fail
	 * @param voter - Voter object
	 * @return false - if Voter object = null
	 * true - otherwise 
	 */
	@Override
	public boolean logIn(Voter voter) {
		if (voter == null) {
			messageLog = "Invalid Username or Password";
			return false;
		} else {
			this.voter = voter;
			messageLog = "Login success!";
			try {
				record("IDNo: " + voter.getId() + "| Username: "
						+ voter.getName() + " - " + messageLog + " |IP: "+InetAddress.getLocalHost().getHostAddress().toString());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
	}

	/**
	 * submit result that voter select (username , project Id, question Id ,nuymber of score , time of vote)
	 * @param user_id - user Id of voter
	 * @param project_id - project Id that user vote
	 * @param question_id - question Id that user select
	 * @param score - number of score that voter vote
	 * @param voteTime - time that submit to server
	 */
	@Override
	public void insertVoteDB(int user_id, int project_id, int question_id,
			int score, Timestamp voteTime) {
		try {
			String queryin = "INSERT INTO Vote (user_id, project_id, question_id, score, datetime) VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(queryin);
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, project_id);
			pstmt.setInt(3, question_id);
			pstmt.setInt(4, score);
			pstmt.setTimestamp(5, voteTime);
			pstmt.executeUpdate();
		} catch (Exception e) {
			record("Can't insert vote record");
		}
	}

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
	@Override
	public boolean canInsert(int user_id, int project_id, int question_id,
			int score, Timestamp votetime) {
		boolean unique = true;
		try {
			ResultSet resultset = stmt.executeQuery("SELECT * FROM Vote");
			while (resultset.next()) {
				if (resultset.getInt("user_id") == user_id
						&& resultset.getInt("project_id") == project_id
						&& resultset.getInt("question_id") == question_id) {
					unique = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			record("Having duplicated record in Database");
		}
		return unique;

	}
		
	/**
	 * create a table model of database to show as a result to user in form of JTable.
	 * @param model - TableModel object which create from instance of database of voting system.
	 * @return model of containing data of which project have been voted.
	 * @throws SQLException
	 */
	@Override
	public DefaultTableModel voteResult(DefaultTableModel model, int id) throws SQLException {
		ResultSet row = stmt
				.executeQuery("SELECT project.teamname,sum(vote.score) as total_vote_score FROM vote,user,question,project WHERE user.ID = vote.user_id and project.ID = vote.project_id and question.ID = vote.question_id and question.ID = "+ id + " GROUP BY project.teamname ORDER BY  total_vote_score DESC");
		ResultSetMetaData meta = row.getMetaData();
		if (model == null)
			model = new DefaultTableModel();
		String cols[] = new String[meta.getColumnCount()];
		for (int i = 0; i < cols.length; ++i) {
			cols[i] = meta.getColumnLabel(i + 1);
		}
		model.setColumnIdentifiers(cols);
		while (row.next()) {
			Object data[] = new Object[cols.length];
			for (int i = 0; i < data.length; ++i) {
				data[i] = row.getObject(i + 1);
			}
			model.addRow(data);
		}
		return model;
	}

	/**
	 * return All Voter object (which in database)
	 * @return Array of voters - Voter object
	 */
	@Override
	public ArrayList<Voter> getAllVoter() {
		String query; // SQL select string
		ResultSet rs; // SQL query results
		ArrayList<Voter> voters = new ArrayList<Voter>();
		query = "SELECT User.username,Roles.role FROM User,Roles WHERE User.role_id = Roles.ID";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				voters.add(new Voter(rs.getInt("ID"), rs
						.getNString("username"), rs.getNString("password"),rs.getInt("role_id")));
			}
			record("Access User Database");
			return voters;
			// Loop through the rows retrieved from the query
		} catch (Exception e) {
			record("Can't access User Database");
		}
		return null;
	}

	/**
	 * return voter which currently log on
	 * @return voter object
	 */
	@Override
	public String getMessage() {
		return messageLog;
	}

	/**
	 * return message log for display in UI
	 * @return messageLog
	 */
	@Override
	public void close() throws SQLException {
		if (stmt != null) {
			stmt.close();
			con.close();
			record(messageLog = "exits");
		}
	}

	/**
	 * disconnect from database sever
	 */
	@Override
	public Voter getVoter() {
		// TODO Auto-generated method stub
		return voter;
	}
	/* (non-Javadoc)
	 * @see g53.exceedvote.persistence.VoterDao#insertVoter(g53.exceedvote.domain.Voter)
	 */
	@Override
	public void insertVoter(Voter voter) {
		// TODO Auto-generated method stub
		long ID = voter.getId();
		String username = voter.getName();
		String password = voter.getPassword();
		try {
			String queryin = "INSERT INTO user (ID, username, password) VALUES (?,?,?)";
			pstmt = con.prepareStatement(queryin);
			pstmt.setInt(1, (int)ID);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
		} catch (Exception e) {
			record("Can't insert user record");
		}
	}

	/* (non-Javadoc)
	 * @see g53.exceedvote.persistence.VoterDao#canInsertVoter(long, java.lang.String)
	 */
	@Override
	public boolean canInsertVoter(long id, String name) {
		// TODO Auto-generated method stub
		boolean unique = true;
		try {
			ResultSet resultset = stmt.executeQuery("SELECT * FROM User");
			while (resultset.next()) {
				if (resultset.getInt("ID") == id
						&& resultset.getString("username").equalsIgnoreCase(name)) {
					unique = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			record("Having duplicated record in Database");
		}
		return unique;
	}

	@Override
	public ElectionCommittee getElectionCommittee(String user,String pass) {
		// TODO Auto-generated method stub
		String query; // SQL select string
		ResultSet rs; // SQL query results
		query = "SELECT * FROM User WHERE role_id = 3";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (user.equalsIgnoreCase(rs.getString("username"))
						&& pass.equals(rs.getString("password"))) {
					return new ElectionCommittee(rs.getInt("ID"), user, pass);
				}
			}
			// Loop through the rows retrieved from the query
		} catch (Exception e) {
			record("Can't get Election Committee from Database");
		}
		return null;
	}

	@Override
	public boolean logIn(ElectionCommittee electionCommittee) {
		// TODO Auto-generated method stub
		if (electionCommittee == null) {
			messageLog = "Invalid Username or Password";
			return false;
		} else {
			this.electionCommittee = electionCommittee;
			messageLog = "Login success!";
			try {
				record("IDNo: " + this.electionCommittee.getId() + "| Username" + ":"
						+ this.electionCommittee.getName() + " - " + messageLog + " |IP: "+InetAddress.getLocalHost().getHostAddress().toString());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
	}
//	public boolean isVoted(int user_id) {
//	boolean voted = true;
//	try {
//		ResultSet resultset = stmt.executeQuery("SELECT User.username, Project.name,Question.questions FROM Vote,Project,User,Question WHERE User.ID = vote.user_id AND Project.ID = vote.project_id AND Question.ID = vote.question_id AND vote.user_id = " + );
//		while (resultset.next()) {
//			if (resultset.getInt("user_id") == user_id
//					&& resultset.getInt("project_id") == project_id
//					&& resultset.getInt("question_id") == question_id) {
//				voted = false;
//			}
//		}
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		record("Having duplicated record in Database");
//	}
//	return voted;
//}

	@Override
	public void addProject(Project p) {
		// TODO Auto-generated method stub
		long ID = p.getID()
		String tname = p.getTeamName();
		String pname = p.getProjectName();
		ImageIcon imc = p.getImage();
		try {
			String queryin = "INSERT INTO user (ID, username, password,Pictures) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(queryin);
			pstmt.setInt(1, (int)ID);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			fis = new FileInputStream(imc.getImage());
			pstmt.setBinaryStream(4, (InputStream)fis, imc.getImage().l)
			pstmt.executeUpdate();
		} catch (Exception e) {
			record("Can't insert user record");
		}
	}
}
