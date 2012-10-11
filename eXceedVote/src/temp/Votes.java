package temp;
import RecordLog;
import Voter;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 * @author Metas Pongmetha
 * @date 2012/10/01
 * 
 */
public class Votes extends RecordLog {

	Voter[] userName;
	String messageLog;

	public Votes(Voter[] voter) {
		this.userName = voter;
	}

	public String getMessage() {
		return messageLog;
	}

//	@SuppressWarnings("unused")
//	public boolean logIn(String username, String password) {
//		if (username.length()<4 || password.length()<4) {
//			messageLog = "Invalid Username or Password";
//			record(messageLog);
//			return false;
//		} else {
//			for (int i = 0; i < userName.length; i++) {
//				if (!username.equalsIgnoreCase(userName[i].getId() + "")
//						|| !password.equals(userName[i].getPassword())) {
//					messageLog = "Invalid Username or Password";
//					record("User"+":"+username+" "+messageLog);
//					return false;
//				} else {
//					messageLog = "Login success!";
//					record("User"+":"+username+" "+messageLog);
//					return true;
//				}
//			}
//			return false;
//		}
		
		
		public boolean logIn(String username, String password) {
			if (username.length()<4 || password.length()<4) {
				messageLog = "Invalid Username or Password";
				record(messageLog);
				return false;
			} else {
				for (int i = 0; i < userName.length; i++) {
					if (!username.equalsIgnoreCase(userName[i].getId() + "")
							|| !password.equals(userName[i].getPassword())) {
						messageLog = "Invalid Username or Password";
						record("User"+":"+username+" "+messageLog);
						return false;
					} else {
						messageLog = "Login success!";
						record("User"+":"+username+" "+messageLog);
						return true;
					}
				}
				return false;
			}
	}

}
