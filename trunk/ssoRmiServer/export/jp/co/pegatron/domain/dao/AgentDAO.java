package jp.co.pegatron.domain.dao;

import java.util.List;

import jp.co.pegatron.domain.model.Agent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for Agent
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see jp.co.pegatron.domain.model.Agent
 * @author MyEclipse Persistence Tools
 */

public class AgentDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AgentDAO.class);
	// property constants
	public static final String AGENTNAME = "agentname";
	public static final String AGENTPHONE = "agentphone";
	public static final String AGENTEMAIL = "agentemail";
	public static final String AGENTEXTEND = "agentextend";

	public void save(Agent transientInstance) {
		log.debug("saving Agent instance");
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

	public void update(Agent transientInstance) {
		log.debug("updating Agent instance");
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

	public void delete(Agent persistentInstance) {
		log.debug("deleting Agent instance");
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
		log.debug("finding Agent instance with hql: " + hql);
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
				+ itemNum + " Agent instances");
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

	public Agent findById(java.lang.Integer id) {
		log.debug("getting Agent instance with id: " + id);
		Agent agent = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			agent = (Agent) session
					.get("jp.co.pegatron.domain.model.Agent", id);
			transaction.commit();
		} catch (RuntimeException re) {
			transaction.rollback();
			log.error("get failed", re);
			throw re;
		} finally {
			session.close();
		}
		return agent;
	}

	public List findByExample(Agent instance) {
		log.debug("finding Agent instance by example");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			results = session.createCriteria(
					"jp.co.pegatron.domain.model.Agent").add(
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
		log.debug("finding Agent instance with property: " + propertyName
				+ ", value: " + value);
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Agent as model where model."
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

	public List findByAgentname(Object agentname) {
		return findByProperty(AGENTNAME, agentname);
	}

	public List findByAgentphone(Object agentphone) {
		return findByProperty(AGENTPHONE, agentphone);
	}

	public List findByAgentemail(Object agentemail) {
		return findByProperty(AGENTEMAIL, agentemail);
	}

	public List findByAgentextend(Object agentextend) {
		return findByProperty(AGENTEXTEND, agentextend);
	}

	public List findAll() {
		log.debug("finding all Agent instances");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Agent";
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

	public Agent merge(Agent detachedInstance) {
		log.debug("merging Agent instance");
		Agent result = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = (Agent) session.merge(detachedInstance);
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

	public void attachDirty(Agent instance) {
		log.debug("attaching dirty Agent instance");
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

	public void attachClean(Agent instance) {
		log.debug("attaching clean Agent instance");
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