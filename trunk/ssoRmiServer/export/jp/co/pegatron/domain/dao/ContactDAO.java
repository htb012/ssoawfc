package jp.co.pegatron.domain.dao;

import java.util.List;

import jp.co.pegatron.domain.model.Contact;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contact entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see jp.co.pegatron.domain.model.Contact
 * @author MyEclipse Persistence Tools
 */

public class ContactDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ContactDAO.class);
	// property constants
	public static final String CONTACTVALIDATE = "contactvalidate";
	public static final String NOTE = "note";
	public static final String CUSTOMRESPONSE = "customresponse";
	public static final String CUSTOMREQUIRE = "customrequire";
	public static final String CONTACTEXTEND = "contactextend";

	public void save(Contact transientInstance) {
		log.debug("saving Contact instance");
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

	public void update(Contact transientInstance) {
		log.debug("updating Contact instance");
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

	public void delete(Contact persistentInstance) {
		log.debug("deleting Contact instance");
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
		log.debug("finding Contact instance with hql: " + hql);
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
				+ itemNum + " Contact instances");
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

	public Contact findById(Integer id) {
		log.debug("getting Contact instance with id: " + id);
		Contact instance = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			instance = (Contact) session.get(
					"jp.co.pegatron.domain.model.Contact", id);
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

	public List findByExample(Contact instance) {
		log.debug("finding Contact instance by example");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			results = session.createCriteria(
					"jp.co.pegatron.domain.model.Contact").add(
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
		log.debug("finding Contact instance with property: " + propertyName
				+ ", value: " + value);
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Contact as model where model."
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

	public List findByContactvalidate(Object contactvalidate) {
		return findByProperty(CONTACTVALIDATE, contactvalidate);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByCustomresponse(Object customresponse) {
		return findByProperty(CUSTOMRESPONSE, customresponse);
	}

	public List findByCustomrequire(Object customrequire) {
		return findByProperty(CUSTOMREQUIRE, customrequire);
	}

	public List findByContactextend(Object contactextend) {
		return findByProperty(CONTACTEXTEND, contactextend);
	}

	public List findAll() {
		log.debug("finding all Contact instances");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Contact";
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

	public Contact merge(Contact detachedInstance) {
		log.debug("merging Contact instance");
		Contact result = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = (Contact) session.merge(detachedInstance);
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

	public void attachDirty(Contact instance) {
		log.debug("attaching dirty Contact instance");
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

	public void attachClean(Contact instance) {
		log.debug("attaching clean Contact instance");
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