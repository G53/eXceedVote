package g53.exceedvote.persistence;

import g53.exceedvote.persistence.jdbc.*;

/**
 * Class DaoFactory
 */

/**
 * @author 	Metas Pongmetha 5310546529
 * @Version 2012.November.15
 */
public class DaoFactory {

	// singleton instance of this factory
	private static DaoFactory factory;

	private VoterDao dao;

	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		if (factory == null)
			factory = new DaoFactory();
		return factory;
	}

	public VoterDao getVoterDao() {
		if (dao == null)
			dao = new VoterDaoJdbc();
		return dao;
	}

}
