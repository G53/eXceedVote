package g53.exceedvote.domain;
import org.apache.log4j.Logger;

/**
 * RecordLog is abstract class for every class that need to use record message to log file 
 * 
 * @author Metas Pongmetha
 * @version 2012.10.23
 *
 */
public abstract class RecordLog{
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * Initialize the java.util.logging.Logger. Don't do this if using Apache
	 * Log4J.
	 */
	public static void initJavaLogger() {
		java.util.logging.Handler handler = null;
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
		// get System property named "logfile", default to "example.log"
		// user can set this using command line switch -dlogfile=somefile.txt
		String logfile = System.getProperty("logfile", "votes.log");

		try {
			if (logfile != null)
				handler = new java.util.logging.FileHandler(logfile);
			else
				handler = new java.util.logging.ConsoleHandler(); // output to
																	// System.err
			// use plain text output instead of XML
			handler.setFormatter(new java.util.logging.SimpleFormatter());
			logger.addHandler(handler);
			logger.setUseParentHandlers(false); // don't send log msgs to parent

		} catch (java.io.IOException e) {
		}
		// minimum message level to log
		logger.setLevel(java.util.logging.Level.ALL);
	}

	
	/**
	 * record message to log file
	 * @param message - message for record to log file
	 */
	public void record(String message){
		logger.info(message);
	}
	
	
}
