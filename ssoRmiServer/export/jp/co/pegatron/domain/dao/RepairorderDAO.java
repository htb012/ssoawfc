package jp.co.pegatron.domain.dao;

import java.util.List;

import jp.co.pegatron.domain.model.Repairorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Repairorder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see jp.co.pegatron.domain.model.Repairorder
 * @author MyEclipse Persistence Tools
 */

public class RepairorderDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(RepairorderDAO.class);
	// property constants
	public static final String RMANO = "rmano";
	public static final String LOCATION = "location";
	public static final String PARTS = "parts";
	public static final String STOCK = "stock";
	public static final String WAITPARTS = "waitparts";
	public static final String SN = "sn";
	public static final String PN = "pn";
	public static final String WARRANTY = "warranty";
	public static final String VALIDATE = "validate";
	public static final String PROBLEM = "problem";
	public static final String PHOTO = "photo";
	public static final String ISAGENTREPAIR = "isagentrepair";
	public static final String REPAIRORDERSTATE = "repairorderstate";
	public static final String REPAIRORDEREXPEND = "repairorderexpend";

	public void save(Repairorder transientInstance) {
		log.debug("saving Repairorder instance");
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

	public void update(Repairorder transientInstance) {
		log.debug("updating Repairorder instance");
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

	public void delete(Repairorder persistentInstance) {
		log.debug("deleting Repairorder instance");
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

	public Repairorder findById(java.lang.Integer id) {
		log.debug("getting Repairorder instance with id: " + id);
		Repairorder instance = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			instance = (Repairorder) session.get(
					"jp.co.pegatron.domain.model.Repairorder", id);
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

	public List findByExample(Repairorder instance) {
		log.debug("finding Repairorder instance by example");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			results = session.createCriteria(
					"jp.co.pegatron.domain.model.Repairorder").add(
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
		log.debug("finding Repairorder instance with property: " + propertyName
				+ ", value: " + value);
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Repairorder as model where model."
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

	public Repairorder findUniqueByProperty(String propertyName, Object value) {
		log.debug("finding Repairorder instance with property: " + propertyName
				+ ", value: " + value);
		Repairorder instance = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Repairorder as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			instance = (Repairorder) queryObject.uniqueResult();
			transaction.commit();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			transaction.rollback();
			throw re;
		} finally {
			session.close();
		}
		return instance;
	}

	public List findByHql(String hql) {
		log.debug("finding Repairorder instance with hql: " + hql);
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

	public List findByRmano(Object rmano) {
		return findByProperty(RMANO, rmano);
	}

	public List findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findByParts(Object parts) {
		return findByProperty(PARTS, parts);
	}

	public List findByStock(Object stock) {
		return findByProperty(STOCK, stock);
	}

	public List findByWaitparts(Object waitparts) {
		return findByProperty(WAITPARTS, waitparts);
	}

	public List findBySn(Object sn) {
		return findByProperty(SN, sn);
	}

	public List findByPn(Object pn) {
		return findByProperty(PN, pn);
	}

	public List findByWarranty(Object warranty) {
		return findByProperty(WARRANTY, warranty);
	}

	public List findByValidate(Object validate) {
		return findByProperty(VALIDATE, validate);
	}

	public List findByProblem(Object problem) {
		return findByProperty(PROBLEM, problem);
	}

	public List findByPhoto(Object photo) {
		return findByProperty(PHOTO, photo);
	}

	public List findByIsagentrepair(Object isagentrepair) {
		return findByProperty(ISAGENTREPAIR, isagentrepair);
	}

	public List findByRepairorderstate(Object repairorderstate) {
		return findByProperty(REPAIRORDERSTATE, repairorderstate);
	}

	public List findByRepairorderexpend(Object repairorderexpend) {
		return findByProperty(REPAIRORDEREXPEND, repairorderexpend);
	}

	public List findAll() {
		log.debug("finding all Repairorder instances");
		List results = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from Repairorder";
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

	public Repairorder merge(Repairorder detachedInstance) {
		log.debug("merging Repairorder instance");
		Repairorder result = null;
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = (Repairorder) session.merge(detachedInstance);
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

	public void attachDirty(Repairorder instance) {
		log.debug("attaching dirty Repairorder instance");
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

	public void attachClean(Repairorder instance) {
		log.debug("attaching clean Repairorder instance");
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

	public List query(String queryString, int startIndex, int itemNum) {
		log.debug("for:" + queryString + ",from " + startIndex + " finding "
				+ itemNum + " Repairorders instances");
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
}