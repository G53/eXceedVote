package domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class VoterDB extends RecordLog {

	private static Connection con;
	private PreparedStatement pstmt;
	private static Statement stmt;
	private String messageLog;
	private Voter voter;

	public void LoadDriver() {

		// Load the JDBC-ODBC bridge driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void connect() {

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exceed_vote", "root", "Peepo");
			stmt = con.createStatement();
			// turn on autocommit
			con.setAutoCommit(true);
			record(messageLog = "Connected to database");
		} catch (SQLException e) {
			record(messageLog = "Can not connect to database!");
		}

	}

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
			e.printStackTrace();
		}
		return null;
	}

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
			e.printStackTrace();
		}
		return null;
	}

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
			e.printStackTrace();
		}
		record("User: " + user + "- Login fail");
		return null;
	}

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

	public void insertVoteDB(int user_id, int project_id, int question_id,
			int score, Timestamp votetime) {
		try {
			String queryin = "INSERT INTO Vote (user_id, project_id, question_id, score, datetime) VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(queryin);
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, project_id);
			pstmt.setInt(3, question_id);
			pstmt.setInt(4, score);
			pstmt.setTimestamp(5, votetime);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			e.printStackTrace();
		}
		return unique;

	}

	DefaultTableModel voteResult(DefaultTableModel model)
			throws SQLException {
		ResultSet row = stmt
				.executeQuery("SELECT project.teamname,sum(vote.score) as total_vote_score, question.questions FROM vote,voter,question,project WHERE voter.ID = vote.user_id and project.ID = vote.project_id and question.ID = vote.question_id group by question.questions");
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

	public Voter getVoter() {
		return voter;
	}

	public String getMessage() {
		return messageLog;
	}

	public void close() throws SQLException {
		if (stmt != null) {
			stmt.close();
			con.close();
			record(messageLog = "exits");
		}
	}

}
