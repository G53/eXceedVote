package g53.exceedvote.domain;

import java.awt.Menu;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import g53.exceedvote.controller.Controller;
import g53.exceedvote.ui.InterfaceUI;
import g53.exceedvote.ui.LanguageUI;
import g53.exceedvote.ui.LoadingUI;
import g53.exceedvote.ui.LoginUI;
import g53.exceedvote.ui.MenuUI;
import g53.exceedvote.ui.ResultUI;
import g53.exceedvote.ui.SetVotingUI;
import g53.exceedvote.ui.VoteUI;

/**
 * 	Class Main create a GUI for program and call method run from LoginUI class
 *  for run this program.
 */

/**
 * @author Metas Pongmetha 5310546529
 * @Version 2012.November.18
 */
public class Main {
	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		LanguageUI languageUI = new LanguageUI();
		languageUI.run();
		while (!languageUI.isChoose) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//set language
		ResourceBundle language = languageUI.getLanguage();
		Controller control = new Controller();
		//counter for check timeout
		int i = 0;
		InterfaceUI loadUI = new LoadingUI(language);
		do {
			try {
				Thread.sleep(50);
				i++;
				//timeout after wait 5 sec
				if (i > 10) {
					JOptionPane
							.showMessageDialog(null, control.getCurMessage());
					loadUI.close();
					return;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!control.connect());
		loadUI.close();
		//run loginUI
		LoginUI loginUI = new LoginUI(control, language);
		loginUI.run();
		while (!loginUI.getSatus()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//login success
		if(loginUI.getRole().equalsIgnoreCase("voter")){
			//create voteUI for vote
			
			
			MenuUI menuUI = new MenuUI(control, language);
			menuUI.run();
			while(menuUI.getMenuSelect()==null){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (menuUI.getMenuSelect().equalsIgnoreCase("vote")) {
				VoteUI voteUI = new VoteUI(control, language);
				menuUI.close();
				voteUI.run();
			}else if (menuUI.getMenuSelect().equalsIgnoreCase("result")) {
				 ResultUI resultUI = new ResultUI(control, language);
				 menuUI.close();
				 resultUI.run();
			}
		}
		else if(loginUI.getRole().equalsIgnoreCase("election")){
			SetVotingUI setVotingUI = new SetVotingUI(control, language);
			setVotingUI.run();
		}
		
		

	}

}
