package g53.exceedvote.domain;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import g53.exceedvote.controller.Controller;
import g53.exceedvote.persistence.VoterDao;
import g53.exceedvote.persistence.jdbc.VoterDaoJdbc;
import g53.exceedvote.ui.LanguageUI;
import g53.exceedvote.ui.LoadingUI;
import g53.exceedvote.ui.LoginUI;
import g53.exceedvote.ui.VoteUI;

/**
 * 	Class Main create a GUI for program and call method run from LoginUI class
 *  for run this program.
 */

/**
 * @author	Metas Pongmetha 5310546529
 * @Version 2012.November.15
 */
public class Main {
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		ResourceBundle language = ResourceBundle.getBundle("Language");
		LanguageUI languageUI = new LanguageUI(language);
		languageUI.run();
		while (!languageUI.isChoose) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Controller control = new Controller();
		int i = 0;
		LoadingUI loadUI = new LoadingUI();
		while(!control.connect()){
			try {
				Thread.sleep(500);
				i++;
				if (i>20){
					JOptionPane.showMessageDialog(null, control.getCurMessage());
					return;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LoginUI loginUI = new LoginUI(control,language);
		loginUI.run();
		while (!loginUI.getSatus()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		VoteUI voteUI = new VoteUI(control);
		voteUI.run();
		

	}

}
