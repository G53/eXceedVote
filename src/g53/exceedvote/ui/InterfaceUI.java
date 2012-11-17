package g53.exceedvote.ui;

/**
 * Class InterfaceUI is main model of all UI
 */

/**
 * @author 	Metas Pongmetha 5310546529
 * @Version 2012.November.18
 */
public interface InterfaceUI {

	public abstract void initComponents();

	public abstract void close();
	
	/**
	 * convert sting to UTF-8 format
	 * @param key is String that want to encode to UTF-8 (for Thai language)
	 * @return String that already convert to UTF-8
	 */
	public abstract String encode(String key);

}