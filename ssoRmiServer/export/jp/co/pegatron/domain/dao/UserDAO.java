package jp.co.pegatron.domain.dao;

import java.util.List;

import jp.co.pegatron.domain.model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see jp.co.pegatron.domain.model.User
 * @author MyEclipse Persistence Tools
 */

public class UserDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(UserDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String REALNAME = "realname";
	public static final String PASSWORD = "password";
	public static final String SEX = "sex";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String EMAIL = "email";
	public static final String STATE = "state";
	public static final String SECURITYKEY = "securitykey";
	public static final String USEREXTEND = "userextend";
	public static final String USERDISCRIPTION = "userdiscription";

	public void save(User transientInstance) {
		log.debug("saving User instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("save failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	public void update(User transientInstance) {
		log.debug("updating User instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(transientInstance);
			transaction.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("update failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("delete failed", re);
			throw re;
		} finally {
			session.close();
		}
	}

	public List findByHql(String hql) {
		log.debug("finding User instance with hql: " + hql);
		List list = null;
		Query query = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			query = session.createQuery(hql);
			list = query.list();
			transaction.commit();
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("find by hql failed", re);
			throw re;
		} finally {
			session.close();
		}
		return list;
	}

	public List query(String queryString, int startIndex, int itemNum) {
		log.debug("for:" + queryString + ",from " + startIndex + " finding "
				+ itemNum + " User instances");
		List queryList = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query queryObject = session.createQuery(queryString);
			queryObject.setFirstResult(startIndex);
			queryObject.setMaxResults(itemNum);
			queryList = queryObject.list();
			transaction.commit();
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("find all failed", re);
			throw re;
		} finally {
			session.close();
		}
		return queryList;
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		User instance = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			instance = (User) session.get("jp.co.pegatron.domain.model.User",
					id);
			transaction.commit();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.rollback();
			throw re;
		} finally {
			session.close();
		}
		return instance;
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			results = session
					.createCriteria("jp.co.pegatron.domain.model.User").add(
							Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			transaction.commit();
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("find by example failed", re);
			throw re;
		} finally {
			session.close();
		}
		return results;
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			results = queryObject.list();
			transaction.commit();
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("find by property name failed", re);
			throw re;
		} finally {
			session.close();
		}
		return results;
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByRealname(Object realname) {
		return findByProperty(REALNAME, realname);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findBySecuritykey(Object securitykey) {
		return findByProperty(SECURITYKEY, securitykey);
	}

	public List findByUserextend(Object userextend) {
		return findByProperty(USEREXTEND, userextend);
	}

	public List findByUserdiscription(Object userdiscription) {
		return findByProperty(USERDISCRIPTION, userdiscription);
	}

	public List findAll() {
		log.debug("finding all User instances");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from User";
			Query queryObject = session.createQuery(queryString);
			results = queryObject.list();
			transaction.commit();
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("find all failed", re);
			throw re;
		} finally {
			session.close();
		}
		return results;
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		User result = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = (User) session.merge(detachedInstance);
			transaction.commit();
			log.debug("merge successful");
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("merge failed", re);
			throw re;
		} finally {
			session.close();
		}
		return result;
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();
			throw re;
		} finally {
			session.close();
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.lock(instance, LockMode.NONE);
			transaction.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();
			throw re;
		} finally {
			session.close();
		}
	}
}