package jp.co.pegatron.domain.dao;


import org.hibernate.Session;

import com.ssoserver.hibernate.HibernateSessionFactory;

/**
 * Data access object (DAO) for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {

	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

}