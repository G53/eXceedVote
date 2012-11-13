package g53.exceedvote.persistence;

import g53.exceedvote.persistence.jdbc.*;

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
