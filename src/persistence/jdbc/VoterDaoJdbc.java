package persistence.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import persistence.VoterDao;

import domain.Project;
import domain.Question;
import domain.RecordLog;
import domain.Voter;



/**
 * VoterDB actually likes controller of a System by controlling between UI and Database.
 * @author Wasupol Tungsakultong
 * @version 1.0
 */
public class VoterDaoJdbc extends RecordLog implements VoterDao {

	private static Connection con;
	private PreparedStatement pstmt;
	private static Statement stmt;
	private String messageLog;
	private Voter voter;

	/* (non-Javadoc)
	 * @see persistence.VoterDao#LoadDriver()
	 */
	@Override
	public void LoadDriver() {

		// Load the JDBC-ODBC bridge driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			record("Can't Find Database Driver");
		}
	}

	/* (non-Javadoc)
	 * @see persistence.VoterDao#connect()
	 */
	@Override
	public void connect() {

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exceed_vote", "root", "Peepo");
			stmt = con.createStatement();
			// turn on autocommit
			con.setAutoCommit(true);
			record(messageLog = "Connected to database");
//			con.close();
//			record(messageLog = "Disconnected from database"); 
		} catch (SQLException e) {
			record(messageLog = "Can not connect to database!");
			
		}

	}

	/* (non-Javadoc)
	 * @see persistence.VoterDao#getQuestion()
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

	/* (non-Javadoc)
	 * @see persistence.VoterDao#getProject()
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
				arrProject.add(new Project(rs.getInt("ID"), rs
						.getNString("name"), rs.getNString("teamname")));
			}
			record("Access Project Database");
			return arrProject;
			// Loop through the rows retrieved from the query
		} catch (Exception e) {
			record("Can't access Project Database");
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see persistence.VoterDao#getVoter(java.lang.String, java.lang.String)
	 */
	@Override
	public Voter getVoter(String user, String pass) {
		String query; // SQL select string
		ResultSet rs; // SQL query results
		query = "SELECT * FROM Voter ";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (user.equalsIgnoreCase(rs.getString("username"))
						&& pass.equals(rs.getString("password"))) {
					return new Voter(rs.getInt("ID"), user, pass);
				}
			}
			// Loop through the rows retrieved from the query
		} catch (Exception e) {
			record("Can't get Voter from Database");
		}
		record("User: " + user + "- Login fail");
		return null;
	}

	/* (non-Javadoc)
	 * @see persistence.VoterDao#logIn(domain.Voter)
	 */
	@Override
	public boolean logIn(Voter voter) {
		if (voter == null) {
			messageLog = "Invalid Username or Password";
			return false;
		} else {
			this.voter = voter;
			messageLog = "Login success!";
			record("IDNo: " + voter.getId() + "| Username" + ":"
					+ voter.getName() + " - " + messageLog);
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see persistence.VoterDao#insertVoteDB(int, int, int, int, java.sql.Timestamp)
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

	/* (non-Javadoc)
	 * @see persistence.VoterDao#canInsert(int, int, int, int, java.sql.Timestamp)
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
	
	
	/* (non-Javadoc)
	 * @see persistence.VoterDao#voteResult(javax.swing.table.DefaultTableModel)
	 */
	@Override
	public DefaultTableModel voteResult(DefaultTableModel model)
			throws SQLException {
		ResultSet row = stmt
				.executeQuery("SELECT project.teamname,sum(vote.score) as total_vote_score, question.questions FROM vote,voter,question,project WHERE voter.ID = vote.user_id and project.ID = vote.project_id and question.ID = vote.question_id GROUP BY project.teamname ORDER BY  total_vote_score DESC");
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

	/* (non-Javadoc)
	 * @see persistence.VoterDao#getVoter()
	 */
	@Override
	public Voter getVoter() {
		return voter;
	}

	/* (non-Javadoc)
	 * @see persistence.VoterDao#getMessage()
	 */
	@Override
	public String getMessage() {
		return messageLog;
	}

	/* (non-Javadoc)
	 * @see persistence.VoterDao#close()
	 */
	@Override
	public void close() throws SQLException {
		if (stmt != null) {
			stmt.close();
			con.close();
			record(messageLog = "exits");
		}
	}

}
