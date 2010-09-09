package jp.co.pegatron.domain.dao;

import java.util.List;

import jp.co.pegatron.domain.model.Authority;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Authority entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see jp.co.pegatron.domain.model.Authority
 * @author MyEclipse Persistence Tools
 */

public class AuthorityDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AuthorityDAO.class);
	// property constants
	public static final String AUTHEXTEND = "authextend";
	public static final String AUTHDISCRIPTION = "authdiscription";

	public void save(Authority transientInstance) {
		log.debug("saving Authority instance");
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

	public void update(Authority transientInstance) {
		log.debug("updating Authority instance");
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

	public void delete(Authority persistentInstance) {
		log.debug("deleting Authority instance");
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
		log.debug("finding Authority instance with hql: " + hql);
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
				+ itemNum + " Authority instances");
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

	public Authority findById(java.lang.Integer id) {
		log.debug("getting Authority instance with id: " + id);
		Authority instance = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			instance = (Authority) session.get(
					"jp.co.pegatron.domain.model.Authority", id);
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

	public List findByExample(Authority instance) {
		log.debug("finding Authority instance by example");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			results = session.createCriteria(
					"jp.co.pegatron.domain.model.Authority").add(
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
		log.debug("finding Authority instance with property: " + propertyName
				+ ", value: " + value);
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Authority as model where model."
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

	public List findByAuthextend(Object authextend) {
		return findByProperty(AUTHEXTEND, authextend);
	}

	public List findByAuthdiscription(Object authdiscription) {
		return findByProperty(AUTHDISCRIPTION, authdiscription);
	}

	public List findAll() {
		log.debug("finding all Authority instances");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Authority";
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

	public Authority merge(Authority detachedInstance) {
		log.debug("merging Authority instance");
		Authority result = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = (Authority) session.merge(detachedInstance);
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

	public void attachDirty(Authority instance) {
		log.debug("attaching dirty Authority instance");
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

	public void attachClean(Authority instance) {
		log.debug("attaching clean Authority instance");
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